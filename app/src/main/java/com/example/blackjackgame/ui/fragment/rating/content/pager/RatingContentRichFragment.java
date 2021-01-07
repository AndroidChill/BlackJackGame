package com.example.blackjackgame.ui.fragment.rating.content.pager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Rating;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentRatingContentRichBinding;
import com.example.blackjackgame.model.profile.Profile;
import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.model.rating.RatingUserList;
import com.example.blackjackgame.model.rating.ratingLuck.RatingLuckBody;
import com.example.blackjackgame.model.rating.ratingRich.RatingRich;
import com.example.blackjackgame.rModel.profileAny.ProfileAnyBody;
import com.example.blackjackgame.rModel.ratingRich.RatingUser;
import com.example.blackjackgame.rModel.ratingRich.RatingRichBody;
import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;
import com.example.blackjackgame.rNetwork.request.ratingLucky.RatingLuckyRequest;
import com.example.blackjackgame.rNetwork.request.ratingRich.RatingRichRequest;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyFactory;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyViewModel;
import com.example.blackjackgame.rViewModel.ratingRich.RatingRichFactory;
import com.example.blackjackgame.rViewModel.ratingRich.RatingRichViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.rating.RatingAdapter;
import com.example.blackjackgame.ui.adapter.rating.RatingLuckAdapter;
import com.example.blackjackgame.ui.adapter.rating.RatingRichAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.rating.RatingItemOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.google.gson.Gson;

public class RatingContentRichFragment extends Fragment implements RatingItemOnClick {

    private FragmentRatingContentRichBinding binding;
    private RatingRichViewModel viewModel;
    private RatingRichRequest request;
    private RatingRichAdapter adapter;
    private ProfileAnyViewModel profileAnyViewModel;
    private SharedPreferences sharedPreferences;

    public static RatingContentRichFragment newInstance() {

        Bundle args = new Bundle();

        RatingContentRichFragment fragment = new RatingContentRichFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rating_content_rich, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        initRequest();
        viewModel = new ViewModelProvider(this, new RatingRichFactory(request)).get(RatingRichViewModel.class);
        binding.setViewModel(viewModel);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RatingRichRequest request = new RatingRichRequest(
                "rating_rich",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "null")
        );

        refresh();
        update();

        return binding.getRoot();
    }

    private void refresh(){
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRequest();
                viewModel.update(request);
                update();
                binding.refresh.setRefreshing(false);
            }
        });
    }

    private void update(){
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<RatingRichBody>() {
            @Override
            public void onChanged(RatingRichBody ratingRichBody) {
                if(ratingRichBody.getToken() != null){
                    if(ratingRichBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(ratingRichBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(ratingRichBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(ratingRichBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(ratingRichBody.getPopup() != null){
                        if(ratingRichBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    initMainUser(ratingRichBody.getUser());

                    adapter = new RatingRichAdapter(ratingRichBody.getUsers(), RatingContentRichFragment.this::onCLick);
                    binding.recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), ratingRichBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initMainUser(RatingUser user){
        ConvertStringToImage.convert(binding.currentPosition.imageView4, user.getAvatar());
        binding.currentPosition.name.setText(user.getNick());
        binding.currentPosition.number.setText(String.valueOf(user.getPosition()));
        binding.currentPosition.money.setText(String.valueOf(user.getRating()));
    }

    private void initRequest(){
        request = new RatingRichRequest(
                "rating_rich",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
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

    @Override
    public void onCLick(int id) {

        if (profileAnyViewModel == null){
            profileAnyViewModel = new ViewModelProvider(getViewModelStore(), new ProfileAnyFactory(initProfileAnyRequest(id))).get(ProfileAnyViewModel.class);
        } else {
            profileAnyViewModel.update(initProfileAnyRequest(id));
        }
        profileAnyViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<ProfileAnyBody>() {
            @Override
            public void onChanged(ProfileAnyBody profileAnyBody) {
                if(profileAnyBody.getToken() != null){
                    if(profileAnyBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(profileAnyBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(profileAnyBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(profileAnyBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(profileAnyBody.getPopup() != null){
                        if(profileAnyBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    if(profileAnyBody != null){

                        if(profileAnyBody.getProfileAny() != null){
                            String json = new Gson().toJson(profileAnyBody.getProfileAny());
                            UserInfoDialogFragment dialog = new UserInfoDialogFragment();
                            Bundle bundle = new Bundle();
                            dialog.setArguments(bundle);
                            bundle.putString("model", json);
                            dialog.show(getFragmentManager(), "dialog");
                        }

                    } else {
                        Toast.makeText(getContext(), "null object", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), profileAnyBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ProfileAnyRequest initProfileAnyRequest(int id){
        return new ProfileAnyRequest(
                "profile_any",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                id
        );
    }
}
