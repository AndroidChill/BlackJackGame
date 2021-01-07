package com.example.blackjackgame.ui.fragment.friends.content.pager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentFriendsAllBinding;
import com.example.blackjackgame.databinding.FragmentFriendsContentAllBinding;
import com.example.blackjackgame.model.friend.Friend;
import com.example.blackjackgame.model.friend.Global.FriendGlobalBody;
import com.example.blackjackgame.network.responce.friend.FriendRequest;
import com.example.blackjackgame.network.responce.friend.FriendSearchRequest;
import com.example.blackjackgame.rModel.friends.all.Friends;
import com.example.blackjackgame.rModel.friends.all.FriendsAllBody;
import com.example.blackjackgame.rModel.friends.all.FriendsSearchBody;
import com.example.blackjackgame.rModel.profileAny.ProfileAnyBody;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsSearchRequest;
import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;
import com.example.blackjackgame.rViewModel.friendsAll.FriendsAllFactory;
import com.example.blackjackgame.rViewModel.friendsAll.FriendsAllViewModel;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyFactory;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.friend.FriendsAllAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.viewmodel.friend.FriendsFactory;
import com.example.blackjackgame.viewmodel.friend.FriendsViewModel;
import com.example.blackjackgame.viewmodel.profile.ProfileFactory;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendsContentAllFragment extends Fragment implements MyFriendOnClick {

    private FragmentFriendsAllBinding binding;
    private FriendsAllAdapter adapter;
    private FriendsAllViewModel viewModel;
    private SharedPreferences shared;
    private FriendsAllAdapter friendsAllAdapter;
    private FriendsAllRequest request;
    private ProfileAnyViewModel profileAnyViewModel;

    private List<Friend> friends = new ArrayList<>();

    private boolean activeMyFriends = true;
    private boolean activeGlobal = false;

    public static FriendsContentAllFragment newInstance() {

        Bundle args = new Bundle();

        FriendsContentAllFragment fragment = new FriendsContentAllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_all, container, false);

        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        initRequest();
        viewModel =  new ViewModelProvider(this, new FriendsAllFactory(request)).get(FriendsAllViewModel.class);

        binding.setViewModel(viewModel);

        binding.rvGlobal.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvMyFriends.setLayoutManager(new LinearLayoutManager(getContext()));

        refresh();
        updateUI();

        activeRv();

        binding.btnSearch.setOnClickListener(v -> {
            if(binding.editText4 != null || !binding.editText4.getText().toString().isEmpty()){
                search(binding.editText4.getText().toString());
            } else {
                Toast.makeText(getContext(), "Поисковая строка пустая", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private void activeRv(){

        binding.myFriendsActive.setOnClickListener(v -> {
            if(activeMyFriends){
                binding.rvMyFriends.setVisibility(View.GONE);
                binding.ivMyFriends.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
            } else {
                binding.rvMyFriends.setVisibility(View.VISIBLE);
                binding.ivMyFriends.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
            }
            activeMyFriends = !activeMyFriends;
        });

        binding.globalFriendsActive.setOnClickListener(v -> {
            if(activeGlobal){
                binding.globalHint.setVisibility(View.VISIBLE);
                binding.rvGlobal.setVisibility(View.GONE);
                binding.ivGlobalFriends.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
            } else {
                binding.globalHint.setVisibility(View.GONE);
                binding.rvGlobal.setVisibility(View.VISIBLE);
                binding.ivGlobalFriends.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
            }
            activeGlobal = !activeGlobal;
        });

    }

    private void refresh(){
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRequest();
                viewModel.update(request);
                updateUI();
                binding.refresh.setRefreshing(false);
            }
        });
    }

    private void updateUI(){
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<FriendsAllBody>() {
            @Override
            public void onChanged(FriendsAllBody friendsAllBody) {
                if(friendsAllBody.getToken() != null){
                    if(friendsAllBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(friendsAllBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(friendsAllBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(friendsAllBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(friendsAllBody.getPopup() != null){
                        if(friendsAllBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    List<Friends> lst = (friendsAllBody.getFriends());
                    if(lst != null){
                        Collections.sort(lst);
                    }

                    adapter = new FriendsAllAdapter(lst, FriendsContentAllFragment.this::onClick);
                    binding.rvMyFriends.setAdapter(adapter);
            } else {
                    Toast.makeText(getContext(), friendsAllBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
        }});

    }

    private void  search(String search){
        viewModel.getSearch(initRequestSearch(search)).observe(getViewLifecycleOwner(), new Observer<FriendsSearchBody>() {
            @Override
            public void onChanged(FriendsSearchBody friendsSearchBody) {
                if(friendsSearchBody.getToken() != null){
                    if(friendsSearchBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(friendsSearchBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(friendsSearchBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(friendsSearchBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(friendsSearchBody.getPopup() != null){
                        if(friendsSearchBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    if(friendsSearchBody.getFriends() != null){
                        friendsAllAdapter = new FriendsAllAdapter(containsSearch(search, friendsSearchBody.getFriends()), FriendsContentAllFragment.this::onClick);
                        binding.rvGlobal.setAdapter(friendsAllAdapter);
                    }

                } else {
                    Toast.makeText(getContext(), friendsSearchBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }});
    }

    private List<Friends> containsSearch(String search, List<Friends> friends){
        List<Friends> currentFriends = new ArrayList<>();

        for(Friends friend : friends){
            if(friend.getNick().contains(search)){
                currentFriends.add(friend);
            }
        }

        if(currentFriends.size() > 0){
            binding.globalHint.setVisibility(View.GONE);
        }

        return currentFriends;
    }

    private FriendsSearchRequest initRequestSearch(String search){
        return new FriendsSearchRequest(
                "friend_search",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", ""),
                search
        );
    }

    private void startBaseActivity(){
        startActivity(new Intent(getContext(), MainActivity.class));
        ((NavigationActivity)getActivity()).finish();
    }

    //создание диалога с капчей
    private void createCaptchaDialog(String url){
        CaptchaDialog.createCaptchaDialog(
                getContext(),
                getLayoutInflater(),
                url,
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

    //создание диалога с отзывами
    private void createReviewDialog(){
        ReviewDialogHelper.buildReview(
                getContext(),
                getLayoutInflater(),
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

    private void initRequest(){
        request = new FriendsAllRequest(
                "friends",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "")
        );
    }


    @Override
    public void onClick(int id) {

        profileAnyViewModel = new ViewModelProvider(getViewModelStore(), new ProfileAnyFactory(request(id))).get(ProfileAnyViewModel.class);
        profileAnyViewModel.update(request(id));
        profileAnyViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<ProfileAnyBody>() {
            @Override
            public void onChanged(ProfileAnyBody profileAnyBody) {
                if(profileAnyBody.getToken() != null){
                    if(profileAnyBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(profileAnyBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(profileAnyBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(profileAnyBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(profileAnyBody.getPopup() != null){
                        if(profileAnyBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                   if(profileAnyBody.getProfileAny() != null){
                       String json = new Gson().toJson(profileAnyBody.getProfileAny());
                       UserInfoDialogFragment dialog = new UserInfoDialogFragment();
                       Bundle bundle = new Bundle();
                       dialog.setArguments(bundle);
                       bundle.putString("model", json);
                       dialog.show(getFragmentManager(), "dialog");
                   }

                } else {
                    Toast.makeText(getContext(), profileAnyBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private ProfileAnyRequest request(int userId){
        return new ProfileAnyRequest(
                "profile_any",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", ""),
                userId
        );
    }

}
