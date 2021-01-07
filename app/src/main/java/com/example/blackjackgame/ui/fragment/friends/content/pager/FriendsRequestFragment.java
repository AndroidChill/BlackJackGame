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
import com.example.blackjackgame.databinding.FragmentFriendsRequestBinding;
import com.example.blackjackgame.rModel.friends.request.Friends;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestAddBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestCancelBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestDelBody;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestAddRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestCancelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestDelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestRequest;
import com.example.blackjackgame.rViewModel.friendsRequest.FriendsRequestFactory;
import com.example.blackjackgame.rViewModel.friendsRequest.FriendsRequestViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.friend.FriendsRequestAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.interfaceClick.friend.FriendRequestOnClick;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FriendsRequestFragment extends Fragment implements FriendRequestOnClick {

    private FragmentFriendsRequestBinding binding;
    private SharedPreferences sharedPreferences;
    private FriendsRequestRequest request;
    private FriendsRequestViewModel viewModel;
    private FriendsRequestAdapter adapterVhod;
    private FriendsRequestAdapter adapterIshod;

    private boolean activeIshod = false;
    private boolean activeVhod = true;
    private boolean activeAll = false;

    private boolean firstSign = false;

    private List<Friends> friendsList = new ArrayList<>();

    public static FriendsRequestFragment newInstance() {

        Bundle args = new Bundle();

        FriendsRequestFragment fragment = new FriendsRequestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_request, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        initRequest();
        viewModel = new ViewModelProvider(getViewModelStore(), new FriendsRequestFactory(request)).get(FriendsRequestViewModel.class);
        binding.setViewModel(viewModel);

        adapterIshod = new FriendsRequestAdapter(this, getFragmentManager());
        adapterVhod = new FriendsRequestAdapter(this, getFragmentManager());

        initRv();

        refresh();
        updateUI();

        initList();



        return binding.getRoot();
    }

    private void initList(){
        binding.vhod.setOnClickListener(v -> {
            showVhod();
            binding.doneVhod.setVisibility(View.VISIBLE);
            binding.doneIshod.setVisibility(View.GONE);
            binding.doneAll.setVisibility(View.GONE);

        });

        binding.ishod.setOnClickListener(v -> {
            showIshod();
            binding.doneIshod.setVisibility(View.VISIBLE);
            binding.doneVhod.setVisibility(View.GONE);
            binding.doneAll.setVisibility(View.GONE);
        });

        binding.all.setOnClickListener(v -> {
            showAll();
            binding.doneAll.setVisibility(View.VISIBLE);
            binding.doneIshod.setVisibility(View.GONE);
            binding.doneVhod.setVisibility(View.GONE);
        });
    }

    private void showVhod(){

        activeVhod = true;
        activeIshod = false;
        activeAll = false;

        List<Friends> list = new ArrayList<>();

        for (Friends friends : friendsList){
            if(friends.getType() == 0){
                list.add(friends);
            }
        }

        adapterVhod.setList(list);
    }

    private void showIshod(){

        activeVhod = false;
        activeIshod = true;
        activeAll = false;

        List<Friends> list = new ArrayList<>();

        for (Friends friends : friendsList){
            if(friends.getType() == 1){
                list.add(friends);
            }
        }

        adapterVhod.setList(list);
    }

    private void showAll(){

        activeVhod = false;
        activeIshod = false;
        activeAll = true;

        adapterVhod.setList(friendsList);
    }

    private void refresh(){
//        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                initRequest();
//                viewModel.update(request);
//                updateUI();
//                binding.refresh.setRefreshing(false);
//            }
//        });
    }

    private void initRv(){
        binding.rvVhod.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvVhod.setAdapter(adapterVhod);
    }


    private void updateUI(){
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<FriendsRequestBody>() {
            @Override
            public void onChanged(FriendsRequestBody friendsRequestBody) {
                if(friendsRequestBody.getToken() != null){
                    if(friendsRequestBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(friendsRequestBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(friendsRequestBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(friendsRequestBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(friendsRequestBody.getPopup() != null){
                        if(friendsRequestBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    friendsList = friendsRequestBody.getFriends();
                    binding.rvVhod.setAdapter(adapterVhod);

                    if(!firstSign){
                        showVhod();
                        binding.doneVhod.setVisibility(View.VISIBLE);
                        binding.doneIshod.setVisibility(View.GONE);
                        binding.doneAll.setVisibility(View.GONE);
                        firstSign = !firstSign;
                    }

            }
        }});
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
        request = new FriendsRequestRequest(
                "friends_request",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }

    String statusCancel = "error";
    String statusAdd = "error";
    String statusDel = "error";

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

                        statusAdd = "success";

                        for (Iterator<Friends> it = friendsList.iterator(); it.hasNext(); ) {
                            Friends aDrugStrength = it.next();
                            if (aDrugStrength.getId() == (int)id) {
                                it.remove();
                            }
                        }

                        if(activeVhod) {
                            showVhod();
                        } else {
                            if (activeAll){
                                showAll();
                            }
                        }

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

                        if(activeVhod) {
                            showVhod();
                        } else {
                            if (activeAll){
                                showAll();
                            }
                        }

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

                        statusCancel = "success";

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

                        if(activeIshod) {
                            showVhod();
                        } else {
                            if (activeAll){
                                showAll();
                            }
                        }

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

    }

    private FriendsRequestCancelRequest initCancelRequest(int id){
        return new FriendsRequestCancelRequest(
                "friends_request_cancel",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                id
        );
    }

    private FriendsRequestAddRequest initAddRequest(long id){
        return new FriendsRequestAddRequest(
                "friends_request_add",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                (int)id
        );
    }
    private FriendsRequestDelRequest initDelRequest(long id){
        return new FriendsRequestDelRequest(
                "friends_request_add",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                (int)id
        );
    }
}
