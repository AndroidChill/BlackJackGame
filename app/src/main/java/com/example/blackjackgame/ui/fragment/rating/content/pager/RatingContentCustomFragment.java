package com.example.blackjackgame.ui.fragment.rating.content.pager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentRatingContentCustomBinding;
import com.example.blackjackgame.model.profile.Profile;
import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.model.rating.RatingCustom;
import com.example.blackjackgame.model.rating.RatingUserList;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.rating.RatingRequest;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.rating.RatingAdapter;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.rating.RatingItemOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.example.blackjackgame.viewmodel.profile.ProfileFactory;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;
import com.example.blackjackgame.viewmodel.rating.RatingFactory;
import com.example.blackjackgame.viewmodel.rating.RatingViewModel;

public class RatingContentCustomFragment extends Fragment implements RatingItemOnClick {

    private FragmentRatingContentCustomBinding binding;
    private RatingViewModel viewModel;
    private ProfileViewModel profileViewModel;
    private RatingAdapter adapter;
    private SharedPreferences sharedPreferences;

    private int user_id;

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

        viewModel = new ViewModelProvider(this, new RatingFactory(getActivity().getApplication())).get(RatingViewModel.class);
        profileViewModel = new ViewModelProvider(this, new ProfileFactory(getActivity().getApplication())).get(ProfileViewModel.class);

        RatingRequest request = new RatingRequest(
                "rating",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "null")
        );

        viewModel.getRating(request).observe(getViewLifecycleOwner(), new Observer<RatingCustom>() {
            @Override
            public void onChanged(RatingCustom ratingCustom) {

                if(ratingCustom.getStatus().equals(Constant.success)){
                    DataProfileRequest dataProfileRequest = new DataProfileRequest(
                            "profile",
                            Constant.app_ver,
                            Constant.ln,
                            sharedPreferences.getString("token", "null")
                    );

                    profileViewModel.getProfileData(dataProfileRequest).observe(getViewLifecycleOwner(), new Observer<ProfileBody>() {
                        @Override
                        public void onChanged(ProfileBody profileBody) {

                            if(profileBody.getStatus().equals(Constant.success)){
                                Profile profile = profileBody.getProfile();

                                for(RatingUserList user : ratingCustom.getRating()){
                                    if(profile.getUser_nickname().equals(user.getNick())){
                                        //TODO: добавить в вывод номер позиции(или как возможность сделать это в данном классе)
                                        binding.currentPosition.number.setText(String.valueOf(profile.getUser_rating_position()));
                                        ConvertStringToImage.convert(binding.currentPosition.imageView4, profile.getUser_avatar());
                                        binding.currentPosition.name.setText(profile.getUser_nickname());
                                        binding.currentPosition.money.setText(String.valueOf(profile.getUser_coins()));
                                        binding.currentPosition.number.setText(getNumber(ratingCustom.getRatingUser().getPosition()));
                                        user_id = user.getUser_id();
                                    }
                                }
                            } else {
                                if (profileBody.getError_text().equals(Constant.failed_token)){
                                    failedToken();
                                }
                                //TODO: обработка ошибки с сервера
                            }
                        }
                    });

                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.recyclerView.setAdapter(new RatingAdapter(ratingCustom.getRating(), RatingContentCustomFragment.this::onCLick));
                } else {
                    if(ratingCustom.getError_text().equals(Constant.failed_token)){
                        failedToken();
                    }
                    //TODO: обработка ошибки с сервера
                }



            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCLick(int id) {
        UserInfoDialogFragment dialog = new UserInfoDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("id", id);

        dialog.setArguments(bundle);

        dialog.show(getFragmentManager(), "dialog");
    }

    private void failedToken(){
        //обнуляем вход
        sharedPreferences.edit().putBoolean(Constant.isSign, false).apply();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        ((NavigationActivity)getActivity()).finish();
    }

    private String getNumber(int i){
        return  i == 0 ? "..." : String.valueOf(i);
    }
}
