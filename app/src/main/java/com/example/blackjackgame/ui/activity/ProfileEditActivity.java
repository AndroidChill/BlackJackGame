package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.ActivityProfileEditBinding;
import com.example.blackjackgame.model.profile.Profile;
import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.model.profile.changeData.ProfileChangeBody;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.profile.change.ProfileChangeDataRequest;
import com.example.blackjackgame.ui.MainActivity;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ProfileChangePhotoDialogFragment;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.fragment.profile.content.edit.RightProfileContentEditFragment;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileFactory;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileViewModel;

public class ProfileEditActivity extends AppCompatActivity {

    private ActivityProfileEditBinding binding;
    private SharedPreferences shared;
    private RightProfileViewModel   viewModel;
    private DataProfileRequest request;
    private ProfileChangeDataRequest newRequest;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit);

        Log.d("tag", "onCreate: ");

        setSupportActionBar((Toolbar)binding.toolbar);
        Toast.makeText(ProfileEditActivity.this, "click", Toast.LENGTH_SHORT).show();
        initToolbar();

        shared = getSharedPreferences("shared", Context.MODE_PRIVATE);
        viewModel = new ViewModelProvider(getViewModelStore(), new RightProfileFactory(initRequest())).get(RightProfileViewModel.class);
        binding.setViewModel(viewModel);

        updateUI();

        binding.info.done.setOnClickListener(v -> {
            sendEditData();
            finish();
        });

        binding.info.cancel.setOnClickListener(v -> {
            finish();
        });

        binding.header1.changePhoto.setOnClickListener(v -> {
            ProfileChangePhotoDialogFragment dialogFragment = ProfileChangePhotoDialogFragment.newInstance(binding.header1.circleImageView);
            dialogFragment.show(getSupportFragmentManager().beginTransaction(), "dialog");
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

        initRefresh();

    }

    private void initRefresh(){
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request = initRequestProfile();
                viewModel.swipeRefresh(request);
                updateUI();
                binding.refresh.setRefreshing(false);
            }
        });
    }

    //обновление пользовательского интерфейса
    private void updateUI(){
        viewModel.getProfile().observe(this, new Observer<ProfileBody>() {
            @Override
            public void onChanged(ProfileBody profileBody) {

                profile = profileBody.getProfile();

                binding.info.setModel(profile);

                //проверка на ошибочный статус
                if(profileBody.getStatus().equals("error")){
                    Toast.makeText(ProfileEditActivity.this, profileBody.getError_text(), Toast.LENGTH_SHORT).show();
                } else {

                    //проверка на токен
                    if (profileBody.getToken() != null){
                        if(profileBody.getToken().equals("error")){
                            Intent intent = new Intent(ProfileEditActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    //проверка на отзывы
                    if (profileBody.getPopup() != null)
                        if(profileBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }

                    //проверка на капчу
                    if(profileBody.getCaptcha_image_url() != null){
                        createCaptchaDialog(profileBody.getCaptcha_image_url());
                    }

                }
            }
        });
    }

    private void sendEditData(){

        newRequest = initRequestData();

        viewModel.changeData(newRequest).observe(this, new Observer<ProfileChangeBody>() {
            @Override
            public void onChanged(ProfileChangeBody profileChangeBody) {
                //проверка на ошибочный статус
                if(profileChangeBody.getStatus().equals("error")){
                    Toast.makeText(ProfileEditActivity.this, profileChangeBody.getStatus_text(), Toast.LENGTH_SHORT).show();
                } else {
                    //проверка на токен
                    if (profileChangeBody.getToken() != null){
                        if(profileChangeBody.getToken().equals("error")){
                            Intent intent = new Intent(ProfileEditActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    //проверка на отзывы
                    if (profileChangeBody.getPopup() != null)
                        if(profileChangeBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    //проверка на капчу
                    if(profileChangeBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(profileChangeBody.getCaptchaImageUrl());
                    }
                }
            }
        });
    }

    private ProfileChangeDataRequest initRequestData(){

        profile.setUser_avatar(shared.getString("selectImage", profile.getUser_avatar()));

        return new ProfileChangeDataRequest(
                "change_data",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "null"),
                profile
        );
    }

    //инициализация создания запроса на данные пользователя
    private DataProfileRequest initRequest(){
        return new DataProfileRequest(
                "profile",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "null")
        );
    }

    //закрыть изменение данных
    private void initToolbar(){
        ((Toolbar)(binding.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
    }

    //создание диалога с капчей
    private void createCaptchaDialog(String image_url){
        CaptchaDialog.createCaptchaDialog(
                ProfileEditActivity.this,
                getLayoutInflater(),
                image_url,
                getViewModelStore(),
                this
        );
    }

    //создание диалога с отзывами
    private void createReviewDialog(){
        ReviewDialogHelper.buildReview(
                ProfileEditActivity.this,
                getLayoutInflater(),
                getViewModelStore(),
                this
        );
    }

    //создание запроса с инфой о пользователе
    private DataProfileRequest initRequestProfile(){
        return new DataProfileRequest(
                "profile",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "null")
        );
    }
}