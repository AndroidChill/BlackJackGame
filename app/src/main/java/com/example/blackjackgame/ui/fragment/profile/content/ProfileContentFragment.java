package com.example.blackjackgame.ui.fragment.profile.content;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentProfileContentBinding;
import com.example.blackjackgame.model.statics.Review;
import com.example.blackjackgame.model.statics.ReviewBody;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.rModel.profile.Profile;
import com.example.blackjackgame.rModel.profile.ProfileBody;
import com.example.blackjackgame.rNetwork.request.profile.ProfileRequest;
import com.example.blackjackgame.rViewModel.profile.ProfileFactory;
import com.example.blackjackgame.rViewModel.profile.ProfileViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.activity.ProfileEditActivity;
import com.example.blackjackgame.ui.adapter.profile.ProfileProgressAdapter;
import com.example.blackjackgame.ui.adapter.profile.ProfileReferalsAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.fragment.profile.content.edit.ProfileContentEditFragment;

public class ProfileContentFragment extends Fragment {

    private FragmentProfileContentBinding binding;
    private ProfileViewModel viewModel;
    private ProfileRequest request;

    private ProfileProgressAdapter adapter;
    private ProfileReferalsAdapter refAdapter;

    private SharedPreferences shared;
    private Profile profile;

    private boolean isActiveHintRef = false;

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
        setHasOptionsMenu(true);
        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        initRequest();
        viewModel = new ViewModelProvider(getViewModelStore(), new ProfileFactory(request)).get(ProfileViewModel.class);
        binding.setLiveData(viewModel);

        binding.info.setProfile(profile);

        visibilityRef();

        binding.info.rvRef.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.progress.rvProgress.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //изменяем данные
        binding.includeHeader.edit.setOnClickListener(v -> {

            startActivity(new Intent(getContext(), ProfileEditActivity.class));

//            Fragment fragment = ProfileContentEditFragment.newInstance(profile);
//
//            NavigationActivity.fragmentManager.beginTransaction()
//                    .replace(R.id.container_profile, fragment)
//                    .commit();
        });

        refresh();
        updateUI();

        return binding.getRoot();
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

    private void visibilityRef(){
        binding.info.ivRef.setOnClickListener(v -> {
            if(isActiveHintRef){
                binding.info.clHint.setVisibility(View.GONE);
            } else {
                binding.info.clHint.setVisibility(View.VISIBLE);
            }
            isActiveHintRef = !isActiveHintRef;
        });

        binding.info.hint.hideHint.setOnClickListener(v -> {
            binding.info.clHint.setVisibility(View.GONE);
            isActiveHintRef = !isActiveHintRef;
        });

    }

    private void updateUI(){
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<ProfileBody>() {
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

                    profile = profileBody.getProfile();
                    binding.info.setProfile(profile);
                    binding.includeHeader.setModel(profile);
                    binding.setProfile(profile);
                    initAvatar();

                    adapter = new ProfileProgressAdapter(profile.getProgresses());
                    binding.progress.rvProgress.setAdapter(adapter);

                    refAdapter = new ProfileReferalsAdapter(profile.getRefs(), getContext());
                    binding.info.rvRef.setAdapter(refAdapter);

                } else {
                    Toast.makeText(getContext(), profileBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startBaseActivity(){
        startActivity(new Intent(getContext(), MainActivity.class));
        ((NavigationActivity)getActivity()).finish();
    }

    private void initAvatar(){
        switch (profile.getAvatar()){
            case "avatar1.png":
                binding.includeHeader.logo.setImageResource(R.drawable.avatar1);
                break;
            case "avatar2.png":
                binding.includeHeader.logo.setImageResource(R.drawable.avatar2);
                break;
            case "avatar3.png":
                binding.includeHeader.logo.setImageResource(R.drawable.avatar3);
                break;
        }
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
