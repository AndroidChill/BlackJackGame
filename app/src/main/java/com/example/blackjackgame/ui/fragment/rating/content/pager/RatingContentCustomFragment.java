package com.example.blackjackgame.ui.fragment.rating.content.pager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentRatingContentCustomBinding;
import com.example.blackjackgame.rModel.profileAny.ProfileAnyBody;
import com.example.blackjackgame.rModel.rating.RatingBody;
import com.example.blackjackgame.rModel.rating.RatingUser;
import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;
import com.example.blackjackgame.rNetwork.request.rating.RatingRequest;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyFactory;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyViewModel;
import com.example.blackjackgame.rViewModel.rating.RatingFactory;
import com.example.blackjackgame.rViewModel.rating.RatingViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.friend.FriendsAllAdapter;
import com.example.blackjackgame.ui.adapter.rating.RatingAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsContentAllFragment;
import com.example.blackjackgame.ui.interfaceClick.rating.RatingItemOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.google.gson.Gson;

public class RatingContentCustomFragment extends Fragment implements RatingItemOnClick {

    private FragmentRatingContentCustomBinding binding;
    private RatingViewModel viewModel;
    private RatingRequest request;
    private RatingAdapter adapter;
    private ProfileAnyViewModel profileAnyViewModel;
    private SharedPreferences sharedPreferences;

    public static RatingContentCustomFragment newInstance() {

        Bundle args = new Bundle();

        RatingContentCustomFragment fragment = new RatingContentCustomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rating_content_custom, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        initRequest();
        viewModel = new ViewModelProvider(this, new RatingFactory(request)).get(RatingViewModel.class);

        binding.setViewModel(viewModel);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<RatingBody>() {
            @Override
            public void onChanged(RatingBody ratingBody) {
                if(ratingBody.getToken() != null){
                    if(ratingBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(ratingBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(ratingBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(ratingBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(ratingBody.getPopup() != null){
                        if(ratingBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    if(ratingBody.getUser() != null){
                        initMainUser(ratingBody.getUser());
                    }

                    if(ratingBody.getUsers() != null){
                        adapter = new RatingAdapter(ratingBody.getUsers(), RatingContentCustomFragment.this::onCLick);
                        binding.recyclerView.setAdapter(adapter);
                    }

                } else {
                    Toast.makeText(getContext(), ratingBody.getStatusText(), Toast.LENGTH_SHORT).show();
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
        request = new RatingRequest(
                "rating",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
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

    private String getNumber(int i){
        return  i == 0 ? "..." : String.valueOf(i);
    }
}
