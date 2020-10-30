package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
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
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.ui.fragment.profile.content.edit.RightProfileContentEditFragment;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileFactory;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileViewModel;

public class ProfileEditActivity extends AppCompatActivity {

    private ActivityProfileEditBinding binding;
    private SharedPreferences shared;
    private RightProfileViewModel viewModel;
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

        viewModel.getProfile().observe(this, observer -> {
            profile = observer.getProfile();
            binding.info.setModel(profile);
        });

        binding.info.cancel.setOnClickListener(v -> {
            finish();
        });

        binding.info.done.setOnClickListener(v -> {

        });


    }

    private DataProfileRequest initRequest(){
        return new DataProfileRequest(
                "profile",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "null")
        );
    }

    private void initToolbar(){
        ((Toolbar)(binding.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
    }
}