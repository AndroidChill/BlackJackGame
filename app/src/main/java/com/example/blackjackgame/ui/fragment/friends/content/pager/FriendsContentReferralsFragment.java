package com.example.blackjackgame.ui.fragment.friends.content.pager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentFriendsContentReferralsBinding;
import com.example.blackjackgame.databinding.FragmentFriendsReferralsBinding;
import com.example.blackjackgame.model.friend.Friend;
import com.example.blackjackgame.model.friend.referrals.ReferralsBody;
import com.example.blackjackgame.network.responce.friend.FriendReferalsRequest;
import com.example.blackjackgame.rModel.friends.referrals.Friends;
import com.example.blackjackgame.rModel.friends.referrals.FriendsReferralsBody;
import com.example.blackjackgame.rModel.profileAny.ProfileAnyBody;
import com.example.blackjackgame.rNetwork.request.friends.referals.FriendsReferralsRequest;
import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;
import com.example.blackjackgame.rViewModel.friendsReferrals.FriendsReferralsFactory;
import com.example.blackjackgame.rViewModel.friendsReferrals.FriendsReferralsViewModel;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyFactory;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.friend.FriendsReferralsAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.viewmodel.friend.FriendsFactory;
import com.example.blackjackgame.viewmodel.friend.FriendsViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendsContentReferralsFragment extends Fragment implements MyFriendOnClick {

    private FragmentFriendsReferralsBinding binding;
    private FriendsReferralsAdapter adapter;
    private FriendsReferralsRequest request;
    private FriendsReferralsViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private ProfileAnyViewModel profileAnyViewModel;

    public static FriendsContentReferralsFragment newInstance() {

        Bundle args = new Bundle();

        FriendsContentReferralsFragment fragment = new FriendsContentReferralsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_referrals, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        request = initRequest();
        viewModel = new ViewModelProvider(this, new FriendsReferralsFactory(request)).get(FriendsReferralsViewModel.class);

        binding.setViewModel(viewModel);

        refresh();
        updateUI(binding.editText4.getText().toString());

        return binding.getRoot();
    }

    private void refresh(){

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRequest();
                viewModel.update(request);
                updateUI(binding.editText4.getText().toString());
                binding.refresh.setRefreshing(false);
            }
        });

    }

    private void updateUI(String search){
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<FriendsReferralsBody>() {
            @Override
            public void onChanged(FriendsReferralsBody friendsReferralsBody) {
                if(friendsReferralsBody.getToken() != null){
                    if(friendsReferralsBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(friendsReferralsBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(friendsReferralsBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(friendsReferralsBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(friendsReferralsBody.getPopup() != null){
                        if(friendsReferralsBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    if(friendsReferralsBody.getFriends() != null) {
                        List<Friends> lst = friendsReferralsBody.getFriends();
                        Collections.sort(lst);
                        binding.noRefferals.setVisibility(View.GONE);
                        adapter = new FriendsReferralsAdapter(getFriends(search, lst), FriendsContentReferralsFragment.this::onClick);
                    } else {
                        binding.noRefferals.setVisibility(View.VISIBLE);
                    }

                    binding.rvReferralsFriends.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.rvReferralsFriends.setAdapter(adapter);

            }
        }});

    }

    private List<Friends> getFriends(String search, List<Friends> friendsList){
        List<Friends> friends = new ArrayList<>();

        for(Friends f : friendsList){
            if(f.getNick().contains(search)){
                friends.add(f);
            }
        }
        return friends;
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

    private FriendsReferralsRequest initRequest(){
        return new FriendsReferralsRequest(
                "referals",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
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
                sharedPreferences.getString("token", ""),
                userId
        );
    }

    private void failedToken(){
        //обнуляем вход
        sharedPreferences.edit().putBoolean(Constant.isSign, false).apply();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        ((NavigationActivity)getActivity()).finish();
    }

}
