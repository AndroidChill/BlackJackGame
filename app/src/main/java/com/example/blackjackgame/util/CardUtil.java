package com.example.blackjackgame.util;

import android.widget.ImageView;

import com.example.blackjackgame.R;

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

}
