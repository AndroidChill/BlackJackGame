package com.example.blackjackgame.ui.fragment.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.RFragmentRegistrationBinding;
import com.example.blackjackgame.rModel.registration.RegistrationBody;
import com.example.blackjackgame.rModel.registration.RegistrationStartBody;
import com.example.blackjackgame.rModel.sign.SignInEmailStartBody;
import com.example.blackjackgame.rNetwork.request.registration.RegistrationRequest;
import com.example.blackjackgame.rNetwork.request.registration.RegistrationStartRequest;
import com.example.blackjackgame.rViewModel.registrationStart.RegistrationStartFactory;
import com.example.blackjackgame.rViewModel.registrationStart.RegistrationStartViewModel;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.util.ImageLoader;

import java.util.Locale;

public class RegistrationFragment extends Fragment {

    private RFragmentRegistrationBinding binding;
    private SharedPreferences sharedPreferences;
    private RegistrationStartViewModel viewModel;
    private RegistrationStartRequest request;
    private RegistrationRequest requestEnd;

    public static RegistrationFragment newInstance() {

        Bundle args = new Bundle();

        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.r_fragment_registration, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        initRequest();
        viewModel = new ViewModelProvider(getViewModelStore(), new RegistrationStartFactory(request)).get(RegistrationStartViewModel.class);

        binding.btnSendEmail.setOnClickListener(v -> {
            if(isValidEmailAddress(binding.email.getText().toString())){
                if((!isEmpty(binding.editText3)) && (!isEmpty(binding.email)) && (!isEmpty(binding.etCaptcha))){
                    sendData(binding.email.getText().toString(), binding.editText3.getText().toString(), binding.etCaptcha.getText().toString());
                } else {
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Введите корректный email", Toast.LENGTH_SHORT).show();
            }


        });

        binding.setViewModel(viewModel);

        refresh();
        updateUI();
        return binding.getRoot();
    }

    private void refresh(){
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRequest();
                viewModel.update(request);
                updateUI();
                binding.swipe.setRefreshing(false);
            }
        });
    }

    private void sendData(String email, String nick, String captcha){
        initRequestEnd(email, nick, captcha);
        viewModel.send(requestEnd).observe(getViewLifecycleOwner(), new Observer<RegistrationBody>() {
            @Override
            public void onChanged(RegistrationBody registrationBody) {
                if(registrationBody.getStatus().equals("success")){
                    startActivity(new Intent(getContext(), NavigationActivity.class));
                } else {
                    ImageLoader.glideLoad(getActivity(), registrationBody.getCaptchaImageUrl(), binding.captcha);
                    Toast.makeText(getContext(), registrationBody.getStatusText(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void initRequestEnd(String email, String nick, String captcha){
        requestEnd = new RegistrationRequest(
                "registration",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                getSDK(),
                getModel(),
                getMarketName(),
                getManufacturer(),
                email,
                nick,
                "",
                captcha,
                Locale.getDefault().getCountry()
        );
    }

    private void updateUI(){
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<RegistrationStartBody>() {
            @Override
            public void onChanged(RegistrationStartBody registrationStartBody) {
                ImageLoader.glideLoad(getActivity(), registrationStartBody.getCaptchaImageUrl(), binding.captcha);
            }
        });
    }

    private void initRequest(){
        request = new RegistrationStartRequest(
                "registration_start",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private boolean validCaptcha(String str){

        String check = "фбвгдеёжзийклмнопрстуфхцчшщъыьэюяЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮQWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890-_.:@";
        for(int i = 0; i < str.length(); i++){
            if(check.indexOf(String.valueOf(str.charAt(i))) == -1){
                return false;
            }
        }
        return true;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
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
}
