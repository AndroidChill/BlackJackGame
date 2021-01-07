package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.ActivityInfoGetMonetBinding;
import com.example.blackjackgame.rModel.coinsGet.CoinsGet;

public class InfoGetMonetActivity extends AppCompatActivity {

    private ActivityInfoGetMonetBinding binding;
    private CoinsGet coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,  R.layout.activity_info_get_monet);

        initValue();

    }

    private void initValue(){
        coins = getIntent().getParcelableExtra("task");
        String choose = "";
        switch(coins.getType()){
            case 0 :
                choose = "просмотр рекламы гугл";
                break;
            case 1 :
                choose = "просмотр видео рекламы гугл";
                break;
            case 2 :
                choose = "Подтвердить email";
                break;
        }

        if(!coins.getText().isEmpty()){
            binding.description.setVisibility(View.GONE);
        }

        binding.cancel.setOnClickListener(v -> finish());

        binding.header.setText(choose);
        binding.setModel(coins);
    }

}