package com.example.blackjackgame.ui.fragment.sign;

import android.content.Context;
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
import com.example.blackjackgame.databinding.FragmentSignEmailBinding;
import com.example.blackjackgame.rModel.sign.SignInEmailBody;
import com.example.blackjackgame.rModel.sign.SignInEmailStartBody;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailRequest;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailStartRequest;
import com.example.blackjackgame.rViewModel.signinemail.SignInEmailFactory;
import com.example.blackjackgame.rViewModel.signinemail.SignInEmailViewModel;
import com.example.blackjackgame.rViewModel.signinemailstart.SignInEmailStartFactory;
import com.example.blackjackgame.rViewModel.signinemailstart.SignInEmailStartViewModel;
import com.example.blackjackgame.ui.activity.SignActivity;
import com.example.blackjackgame.util.ImageLoader;

public class SignEmailFragment extends Fragment {

    private FragmentSignEmailBinding binding;
    private SignInEmailStartViewModel viewModel;
    private SignInEmailViewModel viewModelEnd;
    private SignInEmailStartRequest requestStart;
    private SignInEmailRequest requestEnd;

    private SharedPreferences sharedPreferences;

    public static SignEmailFragment newInstance() {

        Bundle args = new Bundle();

        SignEmailFragment fragment = new SignEmailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_email, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        requestStart = initRequestStart();
        requestEnd = initRequestEnd("", "");
        viewModelEnd = new ViewModelProvider(this, new SignInEmailFactory(requestEnd)).get(SignInEmailViewModel.class);
        viewModel = new ViewModelProvider(this, new SignInEmailStartFactory(requestStart)).get(SignInEmailStartViewModel.class);
        binding.setViewModel(viewModel);

        binding.btnSendEmail.setOnClickListener(v -> {

            if(isValidEmailAddress(binding.email.getText().toString()) && !isEmpty(binding.etCaptcha)){
                String email = binding.email.getText().toString();
                String captcha = binding.etCaptcha.getText().toString();
                if(validCaptcha(captcha)){
                    requestEnd = initRequestEnd(email, captcha);
                    viewModelEnd.update(requestEnd);
                    sendData();
                } else {
                    Toast.makeText(getContext(), "Введены некорректные символы", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getContext(), "Введите корректный email...", Toast.LENGTH_SHORT).show();
            }

        });

        refresh();
        updateUI();

        return binding.getRoot();
    }

    private void sendData(){
        viewModelEnd.getLiveData().observe(getViewLifecycleOwner(), new Observer<SignInEmailBody>() {
            @Override
            public void onChanged(SignInEmailBody signInEmailBody) {
                if(signInEmailBody.getStatus().equals("success")){
                    ((SignActivity)getActivity()).changeInCodeFragment(binding.email.getText().toString());
                } else {
                    Toast.makeText(getContext(), signInEmailBody.getErrorText(), Toast.LENGTH_SHORT).show();
                    ImageLoader.glideLoad(getActivity(), signInEmailBody.getCaptchaImageUrl(), binding.captcha);
                }
            }
        });
    }

    private void refresh(){
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestStart = initRequestStart();
                viewModel.updateData(requestStart);
                updateUI();
                binding.swipe.setRefreshing(false);
            }
        });
    }

    private void updateUI(){
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<SignInEmailStartBody>() {
            @Override
            public void onChanged(SignInEmailStartBody signInEmailStartBody) {
                ImageLoader.glideLoad(getActivity(), signInEmailStartBody.getCaptchaImageUrl(), binding.captcha);
            }
        });
    }

    private SignInEmailStartRequest initRequestStart(){
        return new SignInEmailStartRequest(
                "sign_in_email_start",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }

    private SignInEmailRequest initRequestEnd(String email, String captcha){
        return new SignInEmailRequest(
                "sign_in_email",
                Constant.app_ver,
                Constant.ln,
                email,
                captcha.replaceAll(" ", ""),
                String.valueOf(getSDK()),
                getModel(),
                getManufacturer(),
                getMarketName(),
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
