package com.example.blackjackgame.ui.fragment.profile.content.edit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentProfileContentEditBinding;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.fragment.profile.content.ProfileContentFragment;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileFactory;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileViewModel;

import java.util.Objects;

public class RightProfileContentEditFragment extends AppCompatActivity {

    private FragmentProfileContentEditBinding binding;
    private RightProfileViewModel viewModel;
    private SharedPreferences shared;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_profile_content_edit);

        setSupportActionBar((Toolbar)binding.toolbar);

        initToolbar();

        shared = getSharedPreferences("shared", Context.MODE_PRIVATE);
        viewModel = new ViewModelProvider(getViewModelStore(), new RightProfileFactory(initRequest())).get(RightProfileViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.getProfile().observe(this, observer -> {
            binding.info.setModel(observer.getProfile());
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
                Toast.makeText(RightProfileContentEditFragment.this, "click", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
