package com.example.blackjackgame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.blackjackgame.R;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.ui.fragment.IntroFragment;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        if(sharedPreferences.getBoolean(Constant.isSign, false)){
            Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
            startActivity(intent);
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, IntroFragment.newInstance())
                    .commit();
        }
    }
}