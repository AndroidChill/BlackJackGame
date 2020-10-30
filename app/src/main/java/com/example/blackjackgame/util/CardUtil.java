package com.example.blackjackgame.util;

import android.widget.ImageView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Card;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class CardUtil {


    public static Card[] getIdShakeCards(Card[] shuffled){

        Card[] shufflet = shuffled;

        Random random = new Random();
        for (int i = shuffled.length - 1; i >= 0; i--) {
            int j = random.nextInt(i + 1);
            /* swap cards i,j */
            Card card = shuffled[i];
            shuffled[i] = shuffled[j];
            shufflet[j] = card;
        }

        return shufflet;
    }

    //генерация массива карт с их очками
    public static Card[] getCards(){
        Card[] cards = {
                new Card(R.drawable.card_2d,2), new Card(R.drawable.card_2c,2),
                new Card(R.drawable.card_2h,2), new Card(R.drawable.card_2s,2),
                new Card(R.drawable.card_3d,3), new Card(R.drawable.card_3c,3),
                new Card(R.drawable.card_3h,3), new Card(R.drawable.card_3s,3),
                new Card(R.drawable.card_4d,4), new Card(R.drawable.card_4c,4),
                new Card(R.drawable.card_4h,4), new Card(R.drawable.card_4s,4),
                new Card(R.drawable.card_5d,5), new Card(R.drawable.card_5c,5),
                new Card(R.drawable.card_5h,5), new Card(R.drawable.card_5s,5),
                new Card(R.drawable.card_6d,6), new Card(R.drawable.card_6c,6),
                new Card(R.drawable.card_6h,6), new Card(R.drawable.card_6s,6),
                new Card(R.drawable.card_7d,7), new Card(R.drawable.card_7c,7),
                new Card(R.drawable.card_7h,7), new Card(R.drawable.card_7s,7),
                new Card(R.drawable.card_8d,8), new Card(R.drawable.card_8c,8),
                new Card(R.drawable.card_8h,8), new Card(R.drawable.card_8s,8),
                new Card(R.drawable.card_9d,9), new Card(R.drawable.card_9c,9),
                new Card(R.drawable.card_9h,9), new Card(R.drawable.card_9s,9),
                new Card(R.drawable.card_10d,10), new Card(R.drawable.card_10c,10),
                new Card(R.drawable.card_10h,10), new Card(R.drawable.card_10s,10),
                new Card(R.drawable.card_jd,2), new Card(R.drawable.card_jc,2),
                new Card(R.drawable.card_jh,2), new Card(R.drawable.card_js,2),
                new Card(R.drawable.card_qd,3), new Card(R.drawable.card_qc,3),
                new Card(R.drawable.card_qh,3), new Card(R.drawable.card_qs,3),
                new Card(R.drawable.card_kd,4), new Card(R.drawable.card_kc,4),
                new Card(R.drawable.card_kh,4), new Card(R.drawable.card_ks,4),
                new Card(R.drawable.card_ad,11, true), new Card(R.drawable.card_ac,11, true),
                new Card(R.drawable.card_ah,11, true), new Card(R.drawable.card_as,11, true)
        };

        return cards;
    }

    public static void generateStartCard(){

    }


}
