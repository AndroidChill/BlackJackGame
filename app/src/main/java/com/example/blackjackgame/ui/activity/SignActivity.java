package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.ActivitySignBinding;
import com.example.blackjackgame.ui.fragment.sign.SignCodeFragment;
import com.example.blackjackgame.ui.fragment.sign.SignEmailFragment;
import com.example.blackjackgame.ui.fragment.sign.SignInFragment;

public class SignActivity extends AppCompatActivity {

    private ActivitySignBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_sign_email, SignEmailFragment.newInstance())
                .commit();
    }


    public void changeInCodeFragment(String email){

        Fragment fragment = SignCodeFragment.newInstance();
        Bundle args = new Bundle();
        args.putCharSequence("email", email);
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_sign_email, fragment)
                .commit();
    }

    public void changeInEmailFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_sign_email, SignEmailFragment.newInstance())
                .commit();
    }

}