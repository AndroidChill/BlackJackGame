package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.blackjackgame.R;
import com.example.blackjackgame.util.CardUtil;

public class ActionGameActivity extends AppCompatActivity {

    /*
    * анимация выдачи карт
    * */

    /*
    * получаем id у списка карт по возрастанию
    * */
    private Integer[] idImages = CardUtil.getCardMassiv();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_game);

//        idImages = CardUtil.getIdShakeCards(idImages);

    }
}