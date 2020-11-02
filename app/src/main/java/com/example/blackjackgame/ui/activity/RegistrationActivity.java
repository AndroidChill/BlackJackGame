package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.ActivityRegistrationBinding;
import com.example.blackjackgame.model.registration.RegistrationBody;
import com.example.blackjackgame.model.statics.CaptchaBody;
import com.example.blackjackgame.network.responce.registration.RegistrationRequest;
import com.example.blackjackgame.network.responce.stattics.CaptchaRequest;
import com.example.blackjackgame.viewmodel.captcha.CaptchaFactory;
import com.example.blackjackgame.viewmodel.captcha.CaptchaViewModel;
import com.example.blackjackgame.viewmodel.rightRegistration.RegistrationViewModel;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;
    private CaptchaViewModel viewModel;
    private RegistrationRequest request;
    private RegistrationViewModel viewModelRegistr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);

        viewModel = new ViewModelProvider(this, new CaptchaFactory()).get(CaptchaViewModel.class);


        binding.setViewModel(viewModel);

        send();
        updateUI();

        initRefresh();

    }

    private void initRefresh(){
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request = initRequest();
                viewModelRegistr.update(request);
                updateUI();
                binding.refresh.setRefreshing(false);
            }
        });
    }

    private void updateUI(){
        viewModel.checkCaptcha(new CaptchaRequest(
                "sign_in_email_start",
                Constant.app_ver
        )).observe(this, new Observer<CaptchaBody>() {
            @Override
            public void onChanged(CaptchaBody captchaBody) {
                Glide
                        .with(RegistrationActivity.this)
                        .load(captchaBody.getCaptcha_image_url())
                        .centerCrop()
                        .into(binding.ivCaptcha);
            }
        });
    }

    private void send(){
        binding.done.setOnClickListener(v -> {

            viewModelRegistr = new ViewModelProvider(this, (ViewModelProvider.Factory) new RegistrationViewModel(initRequest())).get(RegistrationViewModel.class);
            viewModelRegistr.getRegistr().observe(this, new Observer<RegistrationBody>() {
                @Override
                public void onChanged(RegistrationBody registrationBody) {

                }
            });

        });
    }

    private RegistrationRequest initRequest(){
        return new RegistrationRequest(
                "registration",
                Constant.app_ver,
                binding.email.getText().toString(),
                binding.tvCaptcha.getText().toString(),
                "ref_code",
                binding.nick.getText().toString(),
                String.valueOf(getSDK()),
                getModel(),
                getManufacturer(),
                getMarketName()
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
    protected void onPause() {
        super.onPause();
        Log.d("mySpicialTag", "onPause: ");
    }
}