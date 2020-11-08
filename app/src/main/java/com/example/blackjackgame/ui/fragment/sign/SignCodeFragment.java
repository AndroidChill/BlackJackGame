package com.example.blackjackgame.ui.fragment.sign;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentSignCodeBinding;
import com.example.blackjackgame.rModel.sign.SignInEmailCodeBody;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailCodeRequest;
import com.example.blackjackgame.rViewModel.signInEmailCode.SignInEmailCodeFactory;
import com.example.blackjackgame.rViewModel.signInEmailCode.SignInEmailCodeViewModel;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.activity.SignActivity;

public class SignCodeFragment extends Fragment {

    private FragmentSignCodeBinding binding;
    private SignInEmailCodeViewModel viewModel;
    private SignInEmailCodeRequest request;
    private SharedPreferences sharedPreferences;

    public static SignCodeFragment newInstance() {

        Bundle args = new Bundle();

        SignCodeFragment fragment = new SignCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_code, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        String k = (String) getArguments().getCharSequence("email");
        request = initRequest(k, binding.editText2 != null ? binding.editText2.getText().toString() : "");
        viewModel = new ViewModelProvider(getViewModelStore(), new SignInEmailCodeFactory(request)).get(SignInEmailCodeViewModel.class);
        binding.etEmail.setText(k);

        binding.btnCancel.setOnClickListener(v -> ((SignActivity)getActivity()).changeInEmailFragment());
        binding.btnDone.setOnClickListener(v -> sendData(k, binding.editText2 != null ? binding.editText2.getText().toString() : ""));

        return binding.getRoot();
    }

    private void sendData(String email, String code){
        request = initRequest(email, code);
        viewModel.update(request);
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<SignInEmailCodeBody>() {
            @Override
            public void onChanged(SignInEmailCodeBody signInEmailCodeBody) {
                if(signInEmailCodeBody.getStatus().equals("success")){
                    sharedPreferences.edit().putString("token", signInEmailCodeBody.getToken()).apply();
                    startActivity(new Intent(getContext(), NavigationActivity.class));
                } else {
                    Toast.makeText(getContext(), signInEmailCodeBody.getErrorText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private SignInEmailCodeRequest initRequest(String email, String code){
        return new SignInEmailCodeRequest(
                "sign_in_email_code",
                Constant.app_ver,
                Constant.ln,
                email,
                code,
                String.valueOf(getSDK()),
                getModel(),
                getManufacturer(),
                getMarketName(),
                sharedPreferences.getString("token", "")
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
}
