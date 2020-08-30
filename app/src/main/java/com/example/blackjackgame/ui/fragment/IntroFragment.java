package com.example.blackjackgame.ui.fragment;

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
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentIntroBinding;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.activity.SignActivity;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.viewmodel.sign.SignFactory;
import com.example.blackjackgame.viewmodel.sign.SignViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class IntroFragment extends Fragment {

    private FragmentIntroBinding binding;
    private SignViewModel viewModel;
    private SharedPreferences shared;

    public static IntroFragment newInstance() {

        Bundle args = new Bundle();

        IntroFragment fragment = new IntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false);

        if (getActivity().getActionBar() != null){
            getActivity().getActionBar().hide();
        }

        viewModel = new ViewModelProvider(this, new SignFactory(requireActivity().getApplication())).get(SignViewModel.class);

        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        binding.guest.setOnClickListener(v -> {

            if(binding.checkBox.isChecked()){
                viewModel.signGuest().observe(getViewLifecycleOwner(), o -> {
                    if(o.getStatus().equals("success")){

                        shared.edit().putBoolean(Constant.isSign, true).apply();
                        shared.edit().putString("token", o.getToken()).apply();

                        Intent intent = new Intent(getActivity(), NavigationActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
            } else {
                Snackbar.make(binding.layout, "Для начала вы должны ознакомиться с политикой нашего приложения", BaseTransientBottomBar.LENGTH_LONG).show();
            }


        });

        binding.signIn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SignActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
