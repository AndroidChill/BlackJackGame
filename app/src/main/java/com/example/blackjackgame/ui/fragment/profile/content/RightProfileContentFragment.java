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
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentProfileContentBinding;
import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.ui.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.activity.ProfileEditActivity;
import com.example.blackjackgame.ui.adapter.profile.ProfileProgressAdapter;
import com.example.blackjackgame.ui.adapter.profile.ProfileReferalsAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.fragment.profile.content.edit.RightProfileContentEditFragment;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileFactory;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileViewModel;

import java.util.Objects;

public class RightProfileContentFragment extends Fragment {

    private FragmentProfileContentBinding binding;
    private RightProfileViewModel viewModel;
    private SharedPreferences shared;
    private DataProfileRequest request;
    private ProfileReferalsAdapter adapterRef;
    private ProfileProgressAdapter adapterProgress;

    private boolean isActiveHintRef = false;

    public static RightProfileContentFragment newInstance() {

        Bundle args = new Bundle();

        RightProfileContentFragment fragment = new RightProfileContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_content, container, false);

        Objects.requireNonNull(((NavigationActivity) getActivity()).getSupportActionBar()).show();

        setHasOptionsMenu(true);
        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        request = initRequestProfile();
        viewModel = new ViewModelProvider(this, new RightProfileFactory(request)).get(RightProfileViewModel.class);

        binding.setLiveData(viewModel);

        initRefresh();
        initHintRef();
        initRvRef();

        binding.includeHeader.edit.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), ProfileEditActivity.class);
            startActivity(intent);

//            getFragmentManager().beginTransaction()
//                    .add(R.id.container_profile, RightProfileContentEditFragment.newInstance())
//                    .commit();
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateInfoUi();
    }

    private DataProfileRequest initRequestProfile(){
        return new DataProfileRequest(
                "profile",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "null")
        );
    }

    private void initHintRef(){
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

    private void initRefresh(){
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request = initRequestProfile();
                viewModel.swipeRefresh(request);
                updateInfoUi();
                binding.refresh.setRefreshing(false);
            }
        });
    }

    private void initRvRef(){

        binding.info.rvRef.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.progress.rvProgress.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterRef = new ProfileReferalsAdapter(getContext());
        adapterProgress = new ProfileProgressAdapter();

        binding.progress.rvProgress.setAdapter(adapterProgress);
        binding.info.rvRef.setAdapter(adapterRef);
    }

    private void updateInfoUi(){
        viewModel.getProfile().observe(getViewLifecycleOwner(), profileBody -> {
            binding.info.setProfile(profileBody.getProfile());

            //проверка на ошибочный статус
            if(profileBody.getStatus().equals("error")){
                Toast.makeText(getContext(), profileBody.getError_text(), Toast.LENGTH_SHORT).show();
            }

//            проверка на токен
            if (profileBody.getToken() != null){
                if(profileBody.getToken().equals("error")){
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    ((NavigationActivity)getActivity()).finish();
                }
            }

            //проверка на отзывы
            if (profileBody.getPopup() != null)
                if(profileBody.getPopup().equals("comment")){
                  createReviewDialog();
                }

            //проверка на кпчу
            if(profileBody.getCaptcha_image_url() != null){
                createCaptchaDialog(profileBody);
            }

            adapterProgress.setProgress(profileBody.getProfile().getProgress());
            adapterRef.setList(profileBody.getProfile().getRef());
        });
    }

    private void createCaptchaDialog(ProfileBody profileBody){
        CaptchaDialog.createCaptchaDialog(
                getContext(),
                getLayoutInflater(),
                profileBody.getCaptcha_image_url(),
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

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
}
