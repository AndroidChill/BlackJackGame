package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.ActivityProfileEditBinding;
import com.example.blackjackgame.rModel.Avatar;
import com.example.blackjackgame.rModel.AvatarChangeBody;
import com.example.blackjackgame.rModel.profile.Profile;
import com.example.blackjackgame.model.profile.changeData.ProfileChangeBody;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.profile.change.ProfileChangeDataRequest;
import com.example.blackjackgame.rModel.profile.ProfileBody;
import com.example.blackjackgame.rModel.profileSend.ProfileSendBody;
import com.example.blackjackgame.rNetwork.request.ChangePhotoRequest;
import com.example.blackjackgame.rNetwork.request.profile.ProfileRequest;
import com.example.blackjackgame.rNetwork.request.profileSend.ProfileSendRequest;
import com.example.blackjackgame.rViewModel.profile.ProfileFactory;
import com.example.blackjackgame.rViewModel.profile.ProfileViewModel;
import com.example.blackjackgame.ui.adapter.profile.ProfileProgressAdapter;
import com.example.blackjackgame.ui.adapter.profile.ProfileReferalsAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ProfileChangePhotoDialogFragment;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.fragment.profile.content.edit.RightProfileContentEditFragment;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileFactory;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProfileEditActivity extends AppCompatActivity {

    private ActivityProfileEditBinding binding;
    private SharedPreferences shared;
    private ProfileRequest request;
    private ProfileSendRequest sendRequest;
    private Profile profile;

    private Avatar avatar;

    private ProfileViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit);
        shared = getSharedPreferences("shared", Context.MODE_PRIVATE);
        initRequest();
        viewModel = new ViewModelProvider(this, new ProfileFactory(request)).get(ProfileViewModel.class);
        binding.setViewModel(viewModel);
        binding.info.setViewModel(viewModel);
        binding.info.done.setOnClickListener(v -> {
            sendData();
            finish();
        });

        changeAvatar();

        binding.info.cancel.setOnClickListener(v -> {
            finish();
        });



        binding.info.emailCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.info.email.setEnabled(true);
                } else {
                    binding.info.email.setEnabled(false);
                }
            }
        });

        refresh();
        updateUI();

    }

    private void sendData(){
        initSendRequest();
        viewModel.sendProfile(sendRequest).observe(this, new Observer<ProfileSendBody>() {
            @Override
            public void onChanged(ProfileSendBody profileSendBody) {
                //проверка на токен
                if(profileSendBody.getToken() != null){
                    if(profileSendBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(profileSendBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(profileSendBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(profileSendBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(profileSendBody.getPopup() != null){
                        if(profileSendBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }
                    Log.d("tag", "onChanged: " + profileSendBody.getStatusText());
                    Toast.makeText(ProfileEditActivity.this, "Успешно", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ProfileEditActivity.this, profileSendBody.getStatusText(), Toast.LENGTH_LONG).show();
                } else {
                    Log.d("tag", "onChanged: " + profileSendBody.getStatusText());
                    Toast.makeText(ProfileEditActivity.this, profileSendBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void changeHeader(Avatar avatar){
        this.avatar = avatar;
        choiceAvatar(avatar.getImage());
    }

    private List<Avatar> avatars = new ArrayList<>();

    private void choiceAvatar(String avatar){
        ConvertStringToImage.convert(binding.header1.circleImageView, avatar);
    }

    private void changeAvatar(){
        binding.header1.changePhoto.setOnClickListener(v -> {
            viewModel.getChangeAvatar(ProfileEditActivity.this, initPhotoRequest()).observe(ProfileEditActivity.this, new Observer<AvatarChangeBody>() {
                @Override
                public void onChanged(AvatarChangeBody avatarChangeBody) {
                    //проверка на токен
                    if(avatarChangeBody.getToken() != null){
                        if(avatarChangeBody.getToken().equals("error")){
                            startBaseActivity();
                        }
                    }

                    if(avatarChangeBody.getStatus().equals("success")){

                        //проверка на капчу
                        if(avatarChangeBody.getCaptchaImageUrl() != null){
                            createCaptchaDialog(avatarChangeBody.getCaptchaImageUrl());
                        }

                        //проверка на отзывы
                        if(avatarChangeBody.getPopup() != null){
                            if(avatarChangeBody.getPopup().equals("comment")){
                                createReviewDialog();
                            }
                        }

                        if(avatarChangeBody.getAvatars() != null){
                            avatars = avatarChangeBody.getAvatars();

                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                            if (prev != null) {
                                ft.remove(prev);
                            }
                            ft.addToBackStack(null);

                            ProfileChangePhotoDialogFragment dialog = new ProfileChangePhotoDialogFragment();
                            dialog.show(ft, "dialog");
                        } else {
                            Toast.makeText(ProfileEditActivity.this, "Вам пока не доступны новые фотографии для профиля", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(ProfileEditActivity.this, avatarChangeBody.getStatusText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

    public List<Avatar> getAvatar(){
        return avatars;
    }


    private ChangePhotoRequest initPhotoRequest(){
        return new ChangePhotoRequest(
                "avatar_change",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "")
        );
    }

    private void initSendRequest(){
        if (avatar != null)
            profile.setAvatar(avatar.getImage());
        sendRequest = new ProfileSendRequest(
                "profile_save",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", ""),
                profile
        );
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

    private void initRequest(){
        request = new ProfileRequest(
                "profile",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "")
        );
    }

    private void updateUI(){
        viewModel.getLiveData().observe(this, new Observer<com.example.blackjackgame.rModel.profile.ProfileBody>() {
            @Override
            public void onChanged(ProfileBody profileBody) {

                //проверка на токен
                if(profileBody.getToken() != null){
                    if(profileBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(profileBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(profileBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(profileBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(profileBody.getPopup() != null){
                        if(profileBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    if(profileBody.getProfile() != null){
                        profile = profileBody.getProfile();
                        binding.info.setViewModel(viewModel);
                        binding.info.setModel(profile);
                        if(profileBody.getProfile().getAvatar() != null){
                            initAvatar();
                        }
                    }
                } else {
                    Toast.makeText(ProfileEditActivity.this, profileBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startBaseActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void initAvatar(){
        ConvertStringToImage.convert(binding.header1.circleImageView, profile.getAvatar());
    }

    //создание диалога с капчей
    private void createCaptchaDialog(String url){
        CaptchaDialog.createCaptchaDialog(
                this,
                getLayoutInflater(),
                url,
                getViewModelStore(),
                this
        );
    }

    //создание диалога с отзывами
    private void createReviewDialog(){
        ReviewDialogHelper.buildReview(
                this,
                getLayoutInflater(),
                getViewModelStore(),
                this
        );
    }

    public boolean setAvatar(Avatar avatar) {

        if(profile.getCoins() < avatar.getCoast()){
            return false;
        } else {
            this.avatar = avatar;
            ConvertStringToImage.convert(binding.header1.circleImageView, avatar.getImage());
            return true;
        }
    }

}