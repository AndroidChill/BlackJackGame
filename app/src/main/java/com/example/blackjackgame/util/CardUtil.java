package com.example.blackjackgame.util;

import android.widget.ImageView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Card;

import java.util.LinkedList;
import java.util.Random;

public class CardUtil {

    //получаем заднюю сторону для карт(идентификатор)
    public static int getIdResourceBackCard(){

        Random random = new Random();

        switch (random.nextInt(6)){
            case 0 :
                return R.drawable.blue_back;
            case 1 :
                return R.drawable.gray_back;
            case 2 :
                return R.drawable.green_back;
            case 3 :
                return R.drawable.purple_back;
            case 4 :
                return R.drawable.red_back;
            case 5 :
                return R.drawable.yellow_back;
        }
        return R.drawable.purple_back;
    }

    public static LinkedList<ImageView> getShakeCards(LinkedList<ImageView> shuffled){

        LinkedList<ImageView> shufflet = shuffled;

        Random random = new Random();
        for (int i = shuffled.size() - 1; i >= 0; i--) {
            int j = random.nextInt(i + 1);
            /* swap cards i,j */
            ImageView card = shuffled.get(i);
            shuffled.set(i, shuffled.get(j));
            shufflet.set(j, card);
        }

        return shufflet;
    }

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

    public static Integer[] getCardMassiv(){
        return new Integer[]{
                R.drawable.card_2c, R.drawable.card_2d, R.drawable.card_2h, R.drawable.card_2s,
                R.drawable.card_3c, R.drawable.card_3d, R.drawable.card_3h, R.drawable.card_3s,
                R.drawable.card_4c, R.drawable.card_4d, R.drawable.card_4h, R.drawable.card_4s,
                R.drawable.card_5c, R.drawable.card_5d, R.drawable.card_5h, R.drawable.card_5s,
                R.drawable.card_6c, R.drawable.card_6d, R.drawable.card_6h, R.drawable.card_6s,
                R.drawable.card_7c, R.drawable.card_7d, R.drawable.card_7h, R.drawable.card_7s,
                R.drawable.card_8c, R.drawable.card_8d, R.drawable.card_8h, R.drawable.card_8s,
                R.drawable.card_9c, R.drawable.card_9d, R.drawable.card_9h, R.drawable.card_9s,
                R.drawable.card_10c, R.drawable.card_10d, R.drawable.card_10h, R.drawable.card_10s,
                R.drawable.jc, R.drawable.jd, R.drawable.jh, R.drawable.js,
                R.drawable.qc, R.drawable.qd, R.drawable.qh, R.drawable.qs,
                R.drawable.kc, R.drawable.card_ad, R.drawable.ah, R.drawable.as,
                R.drawable.card_ac, R.drawable.kd, R.drawable.kh, R.drawable.ks
        };
    }

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
                new Card(R.drawable.jd,2), new Card(R.drawable.jc,2),
                new Card(R.drawable.jh,2), new Card(R.drawable.js,2),
                new Card(R.drawable.qd,3), new Card(R.drawable.qc,3),
                new Card(R.drawable.qh,3), new Card(R.drawable.qs,3),
                new Card(R.drawable.kd,4), new Card(R.drawable.kc,4),
                new Card(R.drawable.kh,4), new Card(R.drawable.ks,4),
                new Card(R.drawable.card_ad,11, true), new Card(R.drawable.card_ac,11, true),
                new Card(R.drawable.ah,11, true), new Card(R.drawable.as,11, true)
        };

        return cards;
    }

//    public static int getConvertIntToDp(int size){
//        return size * getResources().getDisplayMetrics().density;
//    }

}
