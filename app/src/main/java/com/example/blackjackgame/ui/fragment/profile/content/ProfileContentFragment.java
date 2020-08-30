package com.example.blackjackgame.ui.fragment.profile.content;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentProfileContentBinding;
import com.example.blackjackgame.model.profile.Profile;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.profile.ProfileProgressAdapter;
import com.example.blackjackgame.ui.fragment.profile.content.edit.ProfileContentEditFragment;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.example.blackjackgame.viewmodel.profile.ProfileFactory;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;

public class ProfileContentFragment extends Fragment {

    private FragmentProfileContentBinding binding;
    private ProfileViewModel viewModel;
    private ProfileProgressAdapter adapter;
    private SharedPreferences shared;
    private Profile profile;

    public static ProfileContentFragment newInstance() {

        Bundle args = new Bundle();

        ProfileContentFragment fragment = new ProfileContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_content, container, false);

        viewModel = new ViewModelProvider(this, new ProfileFactory(getActivity().getApplication())).get(ProfileViewModel.class);

        setHasOptionsMenu(true);

        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        DataProfileRequest request = new DataProfileRequest(
                "profile",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "null")
        );

        profile = new Profile();
        binding.info.setProfile(profile);

        viewModel.getProfileData(request).observe(getViewLifecycleOwner(), o -> {

            //проверка успешного ответа
            if(o.getStatus().equals(Constant.success)){

                //прячем прогрессбар и показываем всякие view с информацией
                binding.progressBar.setVisibility(View.GONE);
                binding.layoutHeader.setVisibility(View.VISIBLE);
                binding.layoutInfo.setVisibility(View.VISIBLE);
                binding.layoutProgress.setVisibility(View.VISIBLE);

                //заполняем данные
                binding.info.setProfile(o.getProfile());
                ConvertStringToImage.convert(binding.header.logo, o.getProfile().getUser_avatar());
                binding.header.nickname.setText(o.getProfile().getUser_nickname());
                binding.header.id.setText(String.valueOf(o.getProfile().getUser_id()));

                //заполняем адаптер для достижений
                adapter = new ProfileProgressAdapter(o.getProfile().getProgress());

                //устанавливаем горизонтальный список
                LinearLayoutManager horizontalLL1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.progress.rvProgress.setLayoutManager(horizontalLL1);
                binding.progress.rvProgress.setAdapter(adapter);
            } else {
                if(o.getError_text().equals(Constant.failed_token)){
                    failedToken();
                }
                //TODO: обработка ошибки с сервера
            }
        });

        //изменяем данные
        binding.header.edit.setOnClickListener(v -> {
            NavigationActivity.fragmentManager.beginTransaction()
                    .replace(R.id.container_profile, ProfileContentEditFragment.newInstance())
                    .commit();
        });

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void failedToken(){
        //обнуляем вход
        shared.edit().putBoolean(Constant.isSign, false).apply();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        ((NavigationActivity)getActivity()).finish();
    }

}
