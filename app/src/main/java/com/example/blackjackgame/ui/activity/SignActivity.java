package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.ActivitySignBinding;
import com.example.blackjackgame.ui.fragment.sign.SignInFragment;

public class SignActivity extends AppCompatActivity {

    private ActivitySignBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        if(!sharedPreferences.getBoolean("isRegister", false)){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_sign, SignInFragment.newInstance())
                    .commit();
        }
    }
}