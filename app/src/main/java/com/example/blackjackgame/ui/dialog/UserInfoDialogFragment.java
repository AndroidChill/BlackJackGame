package com.example.blackjackgame.ui.dialog;

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
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.DialogUserInfoBinding;
import com.example.blackjackgame.rModel.friends.all.Friends;
import com.example.blackjackgame.rModel.friends.all.FriendsAllBody;
import com.example.blackjackgame.rModel.friends.all.FriendsCreateBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestAddBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestDelBody;
import com.example.blackjackgame.rModel.profileAny.ProfileAny;
import com.example.blackjackgame.rModel.profileAny.ProfileAnyBody;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsCreateRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsSearchRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestAddRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestCancelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestDelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestRequest;
import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;
import com.example.blackjackgame.rViewModel.friendsAll.FriendsAllFactory;
import com.example.blackjackgame.rViewModel.friendsAll.FriendsAllViewModel;
import com.example.blackjackgame.rViewModel.friendsRequest.FriendsRequestFactory;
import com.example.blackjackgame.rViewModel.friendsRequest.FriendsRequestViewModel;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyFactory;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.getmonet.GetMonetAdapter;
import com.example.blackjackgame.ui.adapter.profile.ProfileAnyProgressAdapter;
import com.example.blackjackgame.ui.fragment.getmonet.content.GetMonetContentFragment;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.example.blackjackgame.viewmodel.friend.FriendsViewModel;
import com.google.gson.Gson;

public class UserInfoDialogFragment extends DialogFragment {

    private DialogUserInfoBinding binding;
    private SharedPreferences sharedPreferences;
    private ProfileAnyProgressAdapter adapter;
    private FriendsRequestViewModel viewModel;
    private ProfileAny profileAny;

    private int type = 0;


    public static UserInfoDialogFragment newInstance() {

        Bundle args = new Bundle();

        UserInfoDialogFragment fragment = new UserInfoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static UserInfoDialogFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("id", id);
        UserInfoDialogFragment fragment = new UserInfoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_user_info, container, false);

        if(!getArguments().getString("show", "").isEmpty()){
            binding.personAdd.setVisibility(View.GONE);
            binding.textView53.setVisibility(View.GONE);
        }

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        viewModel = new ViewModelProvider(getViewModelStore(), new FriendsRequestFactory(initRequest())).get(FriendsRequestViewModel.class);

        profileAny = new Gson().fromJson(getArguments().getString("model"), ProfileAny.class);
        initInfoProfile(profileAny);
        initGiveMoney();

        binding.personAdd.setOnClickListener(v -> {
            sendAction();
        });

        return binding.getRoot();
    }

    private void initInfoProfile(ProfileAny profileAny){

        if(profileAny.getNickname() != null){
            binding.nickname.setText(profileAny.getNickname());
        }

        binding.info.setProfile(profileAny);

        if(profileAny.getProgresses() != null){
            if (profileAny.getProgresses().size() == 0 ){
                binding.info.textView11.setVisibility(View.GONE);
            } else {
                binding.info.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                adapter = new ProfileAnyProgressAdapter(profileAny.getProgresses());
                binding.info.recyclerView.setAdapter(adapter);
            }

        } else {
            binding.info.textView11.setVisibility(View.GONE);
        }

        // инициализация типа заявки
        type = profileAny.getFriendStatus();

        switch(type){
            case 0:
                binding.textView53.setText("Удалить друга");
                binding.personAdd.setImageResource(R.drawable.ic_baseline_person_remove_alt_1_24);
                break;
            case 1:
                binding.textView53.setText("Отменить заявку");
                binding.personAdd.setImageResource(R.drawable.ic_baseline_person_remove_alt_1_24);
                break;
            case 2:
                binding.textView53.setText("Принять дружбу");
                binding.personAdd.setImageResource(R.drawable.ic_baseline_person_add_alt_1_24);
                break;
            case 3:
                binding.personAdd.setImageResource(R.drawable.ic_baseline_person_add_alt_1_24);
                binding.textView53.setText("Создать заявку");
                break;
        }

        ConvertStringToImage.convert(binding.imageView22, profileAny.getAvatar());

    }

    private void sendAction(){
        switch(type){
            case 0:
            case 1:
                // удалить из друзей
                // удалить заявку
                if(profileAny != null){
                    viewModel.delRequest(getContext(), initRequestDel(profileAny.getId())).observe(getViewLifecycleOwner(), new Observer<FriendsRequestDelBody>() {
                        @Override
                        public void onChanged(FriendsRequestDelBody friendsRequestDelBody) {
                            if (friendsRequestDelBody.getToken() != null) {
                                if (friendsRequestDelBody.getToken().equals("error")) {
                                    startBaseActivity();
                                }
                            }

                            if (friendsRequestDelBody.getStatus().equals("success")) {

                                //проверка на капчу
                                if (friendsRequestDelBody.getCaptchaImageUrl() != null) {
                                    createCaptchaDialog(friendsRequestDelBody.getCaptchaImageUrl());
                                }

                                //проверка на отзывы
                                if (friendsRequestDelBody.getPopup() != null) {
                                    if (friendsRequestDelBody.getPopup().equals("comment")) {
                                        createReviewDialog();
                                    }
                                }

                                binding.personAdd.setImageResource(R.drawable.ic_baseline_person_add_alt_1_24);
                                type = 3;
                                binding.textView53.setText("Создать заявку");
                                Toast.makeText(getContext(), "Заявка текущего пользователя отменена", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getContext(), friendsRequestDelBody.getStatusText(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                break;
            case 2:
                // принять из друзей
                viewModel.addRequest(getContext(), initRequestAdd(profileAny.getId())).observe(getViewLifecycleOwner(), new Observer<FriendsRequestAddBody>() {
                    @Override
                    public void onChanged(FriendsRequestAddBody friendsRequestAddBody) {
                        if (friendsRequestAddBody.getToken() != null) {
                            if (friendsRequestAddBody.getToken().equals("error")) {
                                startBaseActivity();
                            }
                        }

                        if (friendsRequestAddBody.getStatus().equals("success")) {

                            //проверка на капчу
                            if (friendsRequestAddBody.getCaptchaImageUrl() != null) {
                                createCaptchaDialog(friendsRequestAddBody.getCaptchaImageUrl());
                            }

                            //проверка на отзывы
                            if (friendsRequestAddBody.getPopup() != null) {
                                if (friendsRequestAddBody.getPopup().equals("comment")) {
                                    createReviewDialog();
                                }
                            }

                            type = 1;
                            binding.textView53.setText("Удалить дружбу");
                            binding.personAdd.setImageResource(R.drawable.ic_baseline_person_remove_alt_1_24);
                            Toast.makeText(getContext(), "Заявка текущего пользователя принята", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getContext(), friendsRequestAddBody.getStatusText(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case 3:
                //создать заявку
                viewModel.createSearch(getContext(), initCreateRequest(profileAny.getId())).observe(getViewLifecycleOwner(), new Observer<FriendsCreateBody>() {
                    @Override
                    public void onChanged(FriendsCreateBody friendsCreateBody) {
                        if (friendsCreateBody.getToken() != null) {
                            if (friendsCreateBody.getToken().equals("error")) {
                                startBaseActivity();
                            }
                        }

                        if (friendsCreateBody.getStatus().equals("success")) {

                            //проверка на капчу
                            if (friendsCreateBody.getCaptchaImageUrl() != null) {
                                createCaptchaDialog(friendsCreateBody.getCaptchaImageUrl());
                            }

                            //проверка на отзывы
                            if (friendsCreateBody.getPopup() != null) {
                                if (friendsCreateBody.getPopup().equals("comment")) {
                                    createReviewDialog();
                                }
                            }

                            type = 0;
                            binding.textView53.setText("Отменить заявку");
                            binding.personAdd.setImageResource(R.drawable.ic_baseline_person_remove_alt_1_24);
                            Toast.makeText(getContext(), "Заявка создана", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getContext(), friendsCreateBody.getStatusText(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }

    private FriendsRequestRequest initRequest(){
        return new FriendsRequestRequest(
                "friends_request",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }

    // создание заявки
    private FriendsCreateRequest initCreateRequest(int userId){
        return new FriendsCreateRequest(
                "friends_request_create",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                userId
        );
    }

    // удаление друга
    private FriendsRequestDelRequest initRequestDel(int userId){
        return new FriendsRequestDelRequest(
                "friends_request_del",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                userId
        );
    }

    // принятие заявки
    private FriendsRequestAddRequest initRequestAdd(int userId){
        return new FriendsRequestAddRequest(
                "friends_request_add",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                userId
        );
    }


    // отмена заявки
    private FriendsRequestCancelRequest initCancelRequest(int userId){
        return new FriendsRequestCancelRequest(
                "friends_request_cancel",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                userId
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


    private void initGiveMoney(){
        binding.giveMoney.setOnClickListener(v -> {
            TransferMonetDialogFragment dialogFragment = new TransferMonetDialogFragment();
            Bundle args = new Bundle();
            args.putInt("id", profileAny.getId());
            dialogFragment.setArguments(args);
            dialogFragment.show(getFragmentManager(), "dialog");
        });
    }

}
