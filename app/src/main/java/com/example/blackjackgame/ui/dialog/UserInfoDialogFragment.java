package com.example.blackjackgame.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.DialogUserInfoBinding;
import com.example.blackjackgame.model.friend.getMonet.GiveMonetBody;
import com.example.blackjackgame.model.profile.any.ProfileAnyBody;
import com.example.blackjackgame.network.responce.friend.GiveMonetRequest;
import com.example.blackjackgame.network.responce.profile.any.ProfileAnyRequest;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.profile.ProfileAnyProgressAdapter;
import com.example.blackjackgame.ui.adapter.profile.ProfileProgressAdapter;
import com.example.blackjackgame.viewmodel.profile.ProfileFactory;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class UserInfoDialogFragment extends DialogFragment {

    private DialogUserInfoBinding binding;
    private ProfileViewModel viewModel;
    private SharedPreferences sharedPreferences;

    private boolean isActive = true;
    private String nickname;

    public static UserInfoDialogFragment newInstance() {

        Bundle args = new Bundle();

        UserInfoDialogFragment fragment = new UserInfoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_user_info, container, false);

        viewModel = new ViewModelProvider(this, new ProfileFactory(getActivity().getApplication())).get(ProfileViewModel.class);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        //TODO: добавить в апи выдачу id по нику

        ProfileAnyRequest request = new ProfileAnyRequest(
                "profile_any",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "null"),
                getArguments().getInt("id")
        );

//        binding.nickname.setText(getArguments().getString("nick_user"));

        //логика с показыванием вьюшек по передаче монет
        viewModel.getProfileData(request).observe(getViewLifecycleOwner(), new Observer<ProfileAnyBody>() {
            @Override
            public void onChanged(ProfileAnyBody profileAnyBody) {

                if (profileAnyBody.getStatus().equals(Constant.success)){
                    binding.nickname.setText(profileAnyBody.getProfile_any().getUser_nickname());
                    binding.info.surname.setText(profileAnyBody.getProfile_any().getUser_surname());
                    binding.info.username.setText(profileAnyBody.getProfile_any().getUser_name());
                    binding.info.userrating.setText(String.valueOf(profileAnyBody.getProfile_any().getUser_rating()));

                    LinearLayoutManager horizontalLL1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.info.recyclerView.setLayoutManager(horizontalLL1);
                    binding.info.recyclerView.setAdapter(new ProfileAnyProgressAdapter(profileAnyBody.getProfile_any().getProgress()));

                    if(profileAnyBody.getProfile_any().getCoins_transfer() == 0){
                        binding.giveMoney.setVisibility(View.GONE);
                    } else{
                        binding.giveMoney.setOnClickListener(v -> {
                            binding.tvMoney.setVisibility(View.VISIBLE);
                            binding.countMoney.setVisibility(View.VISIBLE);
                            binding.btnDone.setVisibility(View.VISIBLE);
                        });
                    }
                } else {
                    if(profileAnyBody.getError_text().equals(Constant.failed_token)){
                        failedToken();

                    } else {
                        //TODO: обработка ошибки с сервера
                    }
                }


            }
        });

        binding.btnDone.setOnClickListener(v -> {
            int money = Integer.parseInt(binding.countMoney.getText().toString());

            GiveMonetRequest giveMonetRequest = new GiveMonetRequest(
                    "coins_transfer",
                    Constant.app_ver,
                    Constant.ln,
                    sharedPreferences.getString("token", "null"),
                    getArguments().getInt("id"),
                    money
            );

            viewModel.giveMonet(giveMonetRequest).observe(getViewLifecycleOwner(), new Observer<GiveMonetBody>() {
                @Override
                public void onChanged(GiveMonetBody giveMonetBody) {
                    if(giveMonetBody.getStatus().equals(Constant.success)){
                        dismiss();
                        Snackbar.make(getView(), "Монеты успешно переведены", BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                }
            });

        });

        binding.giveMoney.setOnClickListener(v -> {
            if(isActive){
                binding.btnDone.setVisibility(View.VISIBLE);
                binding.tvMoney.setVisibility(View.VISIBLE);
                binding.countMoney.setVisibility(View.VISIBLE);
            } else {
                binding.btnDone.setVisibility(View.GONE);
                binding.tvMoney.setVisibility(View.GONE);
                binding.countMoney.setVisibility(View.GONE);
            }
            isActive = !isActive;
        });

        return binding.getRoot();
    }

    private void failedToken(){
        //обнуляем вход
        sharedPreferences.edit().putBoolean(Constant.isSign, false).apply();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        ((NavigationActivity)getActivity()).finish();
    }

}
