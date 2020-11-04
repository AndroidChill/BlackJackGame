package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.ui.fragment.IntroFragment;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);
//
//        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
//        startActivity(intent);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, IntroFragment.newInstance())
                .commit();

        //проверка на то, входил уже пользователь или нет
//        if(sharedPreferences.getBoolean(Constant.isSign, false)){
//            Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
//            startActivity(intent);
//        } else {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, IntroFragment.newInstance())
//                    .commit();
//        }



    }
}