package com.example.blackjackgame.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Pair;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.blackjackgame.R;
import com.example.blackjackgame.model.statics.CaptchaBody;
import com.example.blackjackgame.network.responce.stattics.CaptchaRequest;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;

public class Captcha {

    static Dialog dialog;

    public static void createCaptcha(Context context, LayoutInflater inflater, String url, ProfileViewModel viewModel, LifecycleOwner owner){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false);

                    View view = inflater.inflate(R.layout.dialog_captcha, null);
                    builder.setView(view);

                    ImageView ivCaptcha = view.findViewById(R.id.iv_captcha);
                    TextView tvHint = view.findViewById(R.id.tv_error_hint);
                    EditText etCaptcha = view.findViewById(R.id.et_captcha);
                    Button btnOk = view.findViewById(R.id.btn_ok);
                    Button btnCancel = view.findViewById(R.id.btn_cancel);

                    Glide
                            .with(context)
                            .load(url)
                            .centerCrop()
                            .into(ivCaptcha);

                    btnOk.setOnClickListener(v -> {

                        viewModel.checkCaptcha(new CaptchaRequest("captcha", etCaptcha.getText().toString())).observe(owner, new Observer<Pair<String, CaptchaBody>>() {
                            @Override
                            public void onChanged(Pair<String, CaptchaBody> stringCaptchaBodyPair) {
                                if(stringCaptchaBodyPair.first.equals("success")){
                                    if(stringCaptchaBodyPair.second.getStatus().equals("success")){
                                        Toast.makeText(context, "Успех", Toast.LENGTH_SHORT).show();
                                        builder.setCancelable(true);
                                        if(dialog != null){
                                            dialog.dismiss();
                                        }
                                    } else {
                                        Toast.makeText(context, "Не Успех", Toast.LENGTH_SHORT).show();
                                    }
                                } else {

                                }
                            }
                        });

                    });
                     builder.create();
        dialog = builder.show();
    }


}
