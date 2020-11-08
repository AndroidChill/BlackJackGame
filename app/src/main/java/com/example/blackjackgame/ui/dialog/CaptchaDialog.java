package com.example.blackjackgame.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.bumptech.glide.Glide;
import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.DialogCaptchaBinding;
import com.example.blackjackgame.network.responce.stattics.CaptchaRequest;
import com.example.blackjackgame.viewmodel.captcha.CaptchaFactory;
import com.example.blackjackgame.viewmodel.captcha.CaptchaViewModel;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileFactory;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileViewModel;

public class CaptchaDialog {

    private static Dialog dialog;
    private static CaptchaViewModel viewModel;

    public static void createCaptchaDialog(Context context, LayoutInflater inflater, String url, ViewModelStore store,  LifecycleOwner owner){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);

        viewModel = new ViewModelProvider(store, new CaptchaFactory()).get(CaptchaViewModel.class);

        DialogCaptchaBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_captcha, null, false);
        builder.setView(binding.getRoot());

        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .into(binding.ivCaptcha);

        binding.btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });

        binding.btnOk.setOnClickListener(v -> {
            CaptchaRequest request = new CaptchaRequest("captcha", Constant.app_ver, binding.etCaptcha.getText().toString());
            viewModel.checkCaptcha(request).observe(owner, observer -> {
                if(observer.getStatus().equals("success")){
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Попробуй еще раз", Toast.LENGTH_SHORT).show();
                }
            });
        });

        builder.create();
        dialog = builder.show();
    }

}