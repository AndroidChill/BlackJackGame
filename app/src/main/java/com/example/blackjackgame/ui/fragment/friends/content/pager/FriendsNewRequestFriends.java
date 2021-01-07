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

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentFriendsRequestBinding;
import com.example.blackjackgame.rModel.friends.request.Friends;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestAddBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestCancelBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestDelBody;
import com.example.blackjackgame.rModel.profileAny.ProfileAnyBody;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestAddRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestCancelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestDelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestRequest;
import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;
import com.example.blackjackgame.rViewModel.friendsRequest.FriendsRequestFactory;
import com.example.blackjackgame.rViewModel.friendsRequest.FriendsRequestViewModel;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyFactory;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.friend.FriendsRequestAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.friend.FriendRequestOnClick;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FriendsNewRequestFriends extends Fragment implements FriendRequestOnClick {

    private FragmentFriendsRequestBinding binding;
    private FriendsRequestViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private FriendsRequestAdapter adapter;
    private ProfileAnyViewModel profileAnyViewModel;

    // 0 - входящие, 1 - исходящие, 2 - все
    private int typeStatus = 0;
    private List<Friends> friendsList = new ArrayList<>();


    public static FriendsNewRequestFriends newInstance() {

        Bundle args = new Bundle();

        FriendsNewRequestFriends fragment = new FriendsNewRequestFriends();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_request, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        adapter = new FriendsRequestAdapter(this, getFragmentManager());

        initRv();

        viewModel = new ViewModelProvider(getViewModelStore(), new FriendsRequestFactory(initRequest())).get(FriendsRequestViewModel.class);
        binding.setViewModel(viewModel);

        onClick();
        onRefresh();
        updateUI();

        onClickError();

        return binding.getRoot();
    }

    private void onRefresh() {
        viewModel.update(initRequest());
    }

    private void updateUI() {
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<FriendsRequestBody>() {
            @Override
            public void onChanged(FriendsRequestBody friendsRequestBody) {
                if (friendsRequestBody.getToken() != null) {
                    if (friendsRequestBody.getToken().equals("error")) {
                        startBaseActivity();
                    }
                }

                if (friendsRequestBody.getStatus().equals("success")) {

                    //проверка на капчу
                    if (friendsRequestBody.getCaptchaImageUrl() != null) {
                        createCaptchaDialog(friendsRequestBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if (friendsRequestBody.getPopup() != null) {
                        if (friendsRequestBody.getPopup().equals("comment")) {
                            createReviewDialog();
                        }
                    }

                    if(friendsRequestBody.getFriends() != null){
                        friendsList = friendsRequestBody.getFriends();
                        choice();
                    }


                } else {
                    Toast.makeText(getContext(), friendsRequestBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onClick() {
        binding.vhod.setOnClickListener(v -> {
            typeStatus = 0;
            showIncoming();
            choice();
        });
        binding.ishod.setOnClickListener(v -> {
            typeStatus = 1;
            showOutGoing();
            choice();
        });
        binding.all.setOnClickListener(v -> {
            typeStatus = 2;
            showAll();
            choice();
        });
    }

    //выбор списка друзей по категории
    private void choice() {

        List<Friends> friends = new ArrayList<>();

        if (typeStatus == 0) {
            for (Friends friend : friendsList) {
                if (friend.getType() == 0) {
                    friends.add(friend);
                }
            }
        }

        if (typeStatus == 1) {
            for (Friends friend : friendsList) {
                if (friend.getType() == 1) {
                    friends.add(friend);
                }
            }
        }

        if (typeStatus == 2) {
            for (Friends friend : friendsList) {
                friends.add(friend);
            }
        }

        adapter.setList(friends);
    }

    private void onClickError() {
        binding.btnError.setOnClickListener(v -> {
            updateUI();
        });
    }

    //показ только входящие
    private void showIncoming() {
        binding.vhod.setBackgroundResource(R.drawable.border_edit);
        binding.ishod.setBackgroundResource(R.drawable.border_edit_dark);
        binding.all.setBackgroundResource(R.drawable.border_edit_dark);
        binding.doneVhod.setVisibility(View.VISIBLE);
        binding.doneIshod.setVisibility(View.GONE);
        binding.doneAll.setVisibility(View.GONE);
    }

    //показ только исходящих
    private void showOutGoing() {
        binding.vhod.setBackgroundResource(R.drawable.border_edit_dark);
        binding.ishod.setBackgroundResource(R.drawable.border_edit);
        binding.all.setBackgroundResource(R.drawable.border_edit_dark);
        binding.doneVhod.setVisibility(View.GONE);
        binding.doneIshod.setVisibility(View.VISIBLE);
        binding.doneAll.setVisibility(View.GONE);
    }

    //показ всех заявок
    private void showAll() {
        binding.vhod.setBackgroundResource(R.drawable.border_edit_dark);
        binding.ishod.setBackgroundResource(R.drawable.border_edit_dark);
        binding.all.setBackgroundResource(R.drawable.border_edit);
        binding.doneVhod.setVisibility(View.GONE);
        binding.doneIshod.setVisibility(View.GONE);
        binding.doneAll.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(String type, long id) {
        if(type.equals("add")){
            viewModel.addRequest(getContext(), initAddRequest(id)).observe(getViewLifecycleOwner(), new Observer<FriendsRequestAddBody>() {
                @Override
                public void onChanged(FriendsRequestAddBody friendsRequestAddBody) {
                    if(friendsRequestAddBody.getToken() != null){
                        if(friendsRequestAddBody.getToken().equals("error")){
                            startBaseActivity();
                        }
                    }

                    if(friendsRequestAddBody.getStatus().equals("success")){

                        //проверка на капчу
                        if(friendsRequestAddBody.getCaptchaImageUrl() != null){
                            createCaptchaDialog(friendsRequestAddBody.getCaptchaImageUrl());
                        }

                        //проверка на отзывы
                        if(friendsRequestAddBody.getPopup() != null){
                            if(friendsRequestAddBody.getPopup().equals("comment")){
                                createReviewDialog();
                            }
                        }

                        for (Iterator<Friends> it = friendsList.iterator(); it.hasNext(); ) {
                            Friends aDrugStrength = it.next();
                            if (aDrugStrength.getId() == (int)id) {
                                it.remove();
                            }
                        }

                        choice();

                        Toast.makeText(getContext(), "Вы добавили игрока в друзья", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getContext(), friendsRequestAddBody.getStatusText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(type.equals("close")){
            viewModel.delRequest(getContext(), initDelRequest(id)).observe(getViewLifecycleOwner(), new Observer<FriendsRequestDelBody>() {
                @Override
                public void onChanged(FriendsRequestDelBody friendsRequestDelBody) {
                    if(friendsRequestDelBody.getToken() != null){
                        if(friendsRequestDelBody.getToken().equals("error")){
                            startBaseActivity();
                        }
                    }

                    if(friendsRequestDelBody.getStatus().equals("success")){

                        //проверка на капчу
                        if(friendsRequestDelBody.getCaptchaImageUrl() != null){
                            createCaptchaDialog(friendsRequestDelBody.getCaptchaImageUrl());
                        }

                        //проверка на отзывы
                        if(friendsRequestDelBody.getPopup() != null){
                            if(friendsRequestDelBody.getPopup().equals("comment")){
                                createReviewDialog();
                            }
                        }

                        for (Iterator<Friends> it = friendsList.iterator(); it.hasNext(); ) {
                            Friends aDrugStrength = it.next();
                            if (aDrugStrength.getId() == (int)id) {
                                it.remove();
                            }
                        }

                        choice();

                        Toast.makeText(getContext(), "Вы отклонили заявку", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getContext(), friendsRequestDelBody.getStatusText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (type.equals("cancel")){
            viewModel.cancelRequest(getContext(), initCancelRequest((int)id)).observe(getViewLifecycleOwner(), new Observer<FriendsRequestCancelBody>() {
                @Override
                public void onChanged(FriendsRequestCancelBody friendsRequestCancelBody) {
                    if(friendsRequestCancelBody.getToken() != null){
                        if(friendsRequestCancelBody.getToken().equals("error")){
                            startBaseActivity();
                        }
                    }

                    if(friendsRequestCancelBody.getStatus().equals("success")){

                        //проверка на капчу
                        if(friendsRequestCancelBody.getCaptchaImageUrl() != null){
                            createCaptchaDialog(friendsRequestCancelBody.getCaptchaImageUrl());
                        }

                        //проверка на отзывы
                        if(friendsRequestCancelBody.getPopup() != null){
                            if(friendsRequestCancelBody.getPopup().equals("comment")){
                                createReviewDialog();
                            }
                        }

                        for (Iterator<Friends> it = friendsList.iterator(); it.hasNext(); ) {
                            Friends aDrugStrength = it.next();
                            if (aDrugStrength.getId() == (int)id) {
                                it.remove();
                            }
                        }

                        choice();

                        Toast.makeText(getContext(), "Текущая заявка отменена", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getContext(), friendsRequestCancelBody.getStatusText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    public void showUser(int id) {
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
                        bundle.putString("show", "true");
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

    private void initRv () {
            binding.rvVhod.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvVhod.setAdapter(adapter);
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

        private FriendsRequestRequest initRequest () {
            return new FriendsRequestRequest(
                    "friends_request",
                    Constant.app_ver,
                    Constant.ln,
                    sharedPreferences.getString("token", "")
            );
        }

        private FriendsRequestCancelRequest initCancelRequest ( int id){
            return new FriendsRequestCancelRequest(
                    "friends_request_cancel",
                    Constant.app_ver,
                    Constant.ln,
                    sharedPreferences.getString("token", ""),
                    id
            );
        }

        private FriendsRequestAddRequest initAddRequest ( long id){
            return new FriendsRequestAddRequest(
                    "friends_request_add",
                    Constant.app_ver,
                    Constant.ln,
                    sharedPreferences.getString("token", ""),
                    (int) id
            );
        }
        private FriendsRequestDelRequest initDelRequest ( long id){
            return new FriendsRequestDelRequest(
                    "friends_request_add",
                    Constant.app_ver,
                    Constant.ln,
                    sharedPreferences.getString("token", ""),
                    (int) id
            );
        }
}