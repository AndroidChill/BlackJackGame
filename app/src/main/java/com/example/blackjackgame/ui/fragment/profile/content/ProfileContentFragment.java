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

import com.bumptech.glide.Glide;
import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentProfileContentBinding;
import com.example.blackjackgame.model.profile.Profile;
import com.example.blackjackgame.model.statics.Review;
import com.example.blackjackgame.model.statics.ReviewBody;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.stattics.ReviewRequest;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.profile.ProfileProgressAdapter;
import com.example.blackjackgame.ui.adapter.profile.ProfileReferalsAdapter;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.fragment.profile.content.edit.ProfileContentEditFragment;
import com.example.blackjackgame.util.Captcha;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.example.blackjackgame.viewmodel.profile.ProfileFactory;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

public class ProfileContentFragment extends Fragment {

    private FragmentProfileContentBinding binding;
    private ProfileViewModel viewModel;

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



        binding.info.rvRef.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getProfileData(binding.ivError, request).observe(getViewLifecycleOwner(), o -> {
//
//            Log.d("tag", "onCreateView: getProfile 1" + o.first + "1");
//            if(o.first.equals("error")){
//                binding.btnError.setVisibility(View.VISIBLE);
//                binding.tvError.setVisibility(View.VISIBLE);
//                binding.ivError.setVisibility(View.VISIBLE);
//            } else {
//                //проверка успешного ответа
//                if(o.second.getStatus().equals(Constant.success)){
//
//                    if(o.second.getCaptcha_image_url() != null ){
////                    Captcha.createCaptcha(getContext(), inflater, o.getCaptcha_image_url(), viewModel, getViewLifecycleOwner());
//                    }
//
//                    if(o.second.getPopup().equals("comment")){
////                    ReviewDialogHelper.buildReview(getContext(), inflater, container, viewModel, getViewLifecycleOwner());
//                    }
//
//                    //прячем прогрессбар и показываем всякие view с информацией
//                    binding.progressBar.setVisibility(View.GONE);
//                    binding.layoutHeader.setVisibility(View.VISIBLE);
//                    binding.layoutInfo.setVisibility(View.VISIBLE);
//                    binding.layoutProgress.setVisibility(View.VISIBLE);
//
//                    //заполняем данные
//                    binding.info.setProfile(o.second.getProfile());
//                    ConvertStringToImage.convert(binding.includeHeader.logo, o.second.getProfile().getUser_avatar());
//                    binding.includeHeader.nickname.setText(o.second.getProfile().getUser_nickname());
//                    binding.includeHeader.id.setText(String.valueOf(o.second.getProfile().getUser_id()));
//
//                    //заполняем адаптер для достижений
//                    adapter = new ProfileProgressAdapter(o.second.getProfile().getProgress());
//                    refAdapter = new ProfileReferalsAdapter(o.second.getProfile().getRef(), getContext());
//                    //устанавливаем горизонтальный список
//                    LinearLayoutManager horizontalLL1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//                    binding.progress.rvProgress.setLayoutManager(horizontalLL1);
//                    binding.progress.rvProgress.setAdapter(adapter);
//
//                    binding.info.rvRef.setAdapter(refAdapter);
//                } else {
//                    if(o.second.getError_text().equals(Constant.failed_token)){
//                        failedToken();
//                    }
//                    //TODO: обработка ошибки с сервера
//                }
//            }


        });

        //изменяем данные
        binding.includeHeader.edit.setOnClickListener(v -> {
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

    @Override
    public void onPause() {
        super.onPause();
        Log.d("tag", "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("tag", "onStop: ");
        ReviewRequest request = new ReviewRequest(
                "comment",
                new Review(
                        String.valueOf(5),
                        "",
                        "2")
        );
//
//        viewModel.checkReview(request).observe(getViewLifecycleOwner(), new Observer<Pair<String, ReviewBody>>() {
//            @Override
//            public void onChanged(Pair<String, ReviewBody> stringReviewBodyPair) {
//                Log.d("tag", "он пытался");
//                if(stringReviewBodyPair.first.equals("success")){
//                    Log.d("tag", "попытка отзыва выполнена успешно");
//                    if(stringReviewBodyPair.second.getSuccess().equals("success")){
//
//                    }
//                }
//            }
//        });
    }

}
