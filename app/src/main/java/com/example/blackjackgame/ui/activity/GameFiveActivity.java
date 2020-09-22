package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.ActivityGameFiveBinding;

public class GameFiveActivity extends AppCompatActivity {

    private ActivityGameFiveBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil.setContentView(this, R.layout.activity_game_five);
    }

    private float convertIntToDp(int size){
        return size * getResources().getDisplayMetrics().density;
    }
}