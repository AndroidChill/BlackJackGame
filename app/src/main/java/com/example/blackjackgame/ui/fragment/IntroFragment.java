package com.example.blackjackgame.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
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

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentIntroBinding;
import com.example.blackjackgame.network.responce.sign.SignGuestRequest;
import com.example.blackjackgame.rModel.sign.SignGuestBody;
import com.example.blackjackgame.rNetwork.request.sign.SignInGuestRequest;
import com.example.blackjackgame.rViewModel.signGuest.SignGuestFactory;
import com.example.blackjackgame.rViewModel.signGuest.SignGuestViewModel;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.activity.RegistrationActivity;
import com.example.blackjackgame.ui.activity.SignActivity;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.viewmodel.sign.SignFactory;
import com.example.blackjackgame.viewmodel.sign.SignViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class    IntroFragment extends Fragment {

    private FragmentIntroBinding binding;
    private SignViewModel viewModel;
    private SharedPreferences shared;

    private SignGuestViewModel viewModelGuest;

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
        viewModelGuest = new ViewModelProvider(this, new SignGuestFactory()).get(SignGuestViewModel.class);

        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        binding.politika.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mylink.com"));
            startActivity(browserIntent);
        });

        binding.guest.setOnClickListener(v -> {

            if(binding.checkBox.isChecked()){

                viewModelGuest.signGuest(getRequest()).observe(getViewLifecycleOwner(), o -> {
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
            if(binding.checkBox.isChecked()){
                Intent intent = new Intent(getContext(), SignActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Вы должны принять соглашение", Toast.LENGTH_SHORT).show();
            }

        });

        binding.registration.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), RegistrationActivity.class));
        });

        return binding.getRoot();
    }

    private SignInGuestRequest getRequest(){
        return new SignInGuestRequest(
                "sign_in_guest",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", ""),
                "",
                String.valueOf(getSDK()),
                getModel(),
                getMarketName(),
                getManufacturer()
        );
    }


    private int getSDK(){
        return Build.VERSION.SDK_INT;
    }

    private String getManufacturer(){
        return Build.MANUFACTURER;
    }

    private String getModel(){
        return Build.MODEL;
    }

    private String getMarketName(){
        return  android.os.Build.PRODUCT;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
