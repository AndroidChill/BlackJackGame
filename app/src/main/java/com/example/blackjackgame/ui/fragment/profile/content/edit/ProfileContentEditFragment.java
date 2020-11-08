package com.example.blackjackgame.ui.fragment.profile.content.edit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentProfileContentEditBinding;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.profile.change.ProfileChangeDataRequest;
import com.example.blackjackgame.rModel.profile.Profile;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.dialog.ProfileChangePhotoDialogFragment;
import com.example.blackjackgame.ui.fragment.profile.content.ProfileContentFragment;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.example.blackjackgame.viewmodel.profile.ProfileFactory;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class ProfileContentEditFragment extends Fragment {

    private FragmentProfileContentEditBinding binding;
    private ProfileViewModel viewModel;
    private Toolbar toolbar;
    private long coins = -1;
    private ProfileChangePhotoDialogFragment profileChangePhotoDialogFragment;
    private SharedPreferences shared;

    private Profile profile;

    public static ProfileContentEditFragment newInstance(Profile profile) {

        Bundle args = new Bundle();
        args.putParcelable("profile", profile);
        ProfileContentEditFragment fragment = new ProfileContentEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_content_edit, container, false);

        profile = getArguments().getParcelable("profile");



//
//        setHasOptionsMenu(true);
//
//        toolbar = (Toolbar)binding.toolbar;
//        Objects.requireNonNull(((NavigationActivity) getActivity()).getSupportActionBar()).hide();
//
//        profileChangePhotoDialogFragment = ProfileChangePhotoDialogFragment.newInstance(binding.header1.circleImageView);
//
//        viewModel = new ViewModelProvider(this, new ProfileFactory(getActivity().getApplication())).get(ProfileViewModel.class);
//
//        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
//
//        //составляем запрос
//        DataProfileRequest request = new DataProfileRequest(
//                "profile",
//                Constant.app_ver,
//                Constant.ln,
//                shared.getString("token", "null")
//        );
//
//        //получаем данные
////        viewModel.getProfileData(, request).observe(getViewLifecycleOwner(), o -> {
////
////            if(o.second.getStatus().equals(Constant.success)){
////                //скрываем прогрессбар и показываем данные
////                binding.progressBar.setVisibility(View.GONE);
////                binding.layoutHeader.setVisibility(View.VISIBLE);
////                binding.layoutInfo.setVisibility(View.VISIBLE);
////
////                setupLogo(o.second.getProfile().getUser_avatar());
////
////                coins = o.second.getProfile().getUser_coins();
////
////                //передаем модель
////                binding.info.setModel(o.second.getProfile());
////            } else {
////                if(o.second.getError_text().equals(Constant.failed_token)){
////                    failedToken();
////                } else {
////                    //TODO: обработка ошибки с сервера
////                }
////            }
////
////        });
//
//        //изменяем фото
//        binding.header1.changePhoto.setOnClickListener(v -> {
//            assert getFragmentManager() != null;
//            profileChangePhotoDialogFragment.show(getFragmentManager(), "dialog");
//
//        });
//
//        binding.info.cancel.setOnClickListener(v -> {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.container_profile, ProfileContentFragment.newInstance())
//                    .commit();
//        });
//
//        //от правляем измененные данные
//        binding.info.done.setOnClickListener(v -> {
////            ProfileChangeDataRequest requestChange = new ProfileChangeDataRequest(
////                    "profile_save",
////                    Constant.app_ver,
////                    Constant.ln,
////                    shared.getString("token", "null"),
////                    new (
////                            binding.info.nickname.getText().toString(),
////                            binding.info.name.getText().toString(),
////                            binding.info.surname.getText().toString(),
////                            binding.info.email.getText().toString(),
////                            binding.info.info.getText().toString(),
////                            shared.getString("selectImage", "avatarNone")
////                    )
////            );
//
//            //отправляем данные и получаем ответ
////            viewModel.changeData(requestChange).observe(getViewLifecycleOwner(), o -> {
////                if(!o.getStatus().equals("success")){
////                    Snackbar.make(binding.main, o.getStatus_text(), Snackbar.LENGTH_LONG)
////                            .show();
////                }
////                getFragmentManager().beginTransaction()
////                        .replace(R.id.container_profile, ProfileContentFragment.newInstance())
////                        .commit();
////            });
//        });
//
//        backToolbar();

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

    private void backToolbar(){

//        ((Toolbar)(binding.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.container_profile, ProfileContentFragment.newInstance())
//                        .commit();
//
//            }
//        });
    }

    //установка изображения(конвертация строки в ссылку)
    private void setupLogo(String image){
//        if(image.equals("avatar1.png")){
//            binding.header1.circleImageView.setImageResource(R.drawable.avatar1);
//        } else {
//            if(image.equals("avatar2.png")){
//                binding.header1.circleImageView.setImageResource(R.drawable.avatar2);
//            } else {
//                binding.header1.circleImageView.setImageResource(R.drawable.avatar3);
//            }
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("tag", "onResume: profile");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("tag", "onStop: ");

    }

    @Override
    public void onDestroy() {
        Log.d("tag", "onDestroy: ");
        Objects.requireNonNull(((NavigationActivity) getActivity()).getSupportActionBar()).show();
        super.onDestroy();
    }
}
