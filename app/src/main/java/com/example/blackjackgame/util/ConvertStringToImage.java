package com.example.blackjackgame.util;

import android.view.View;
import android.widget.ImageView;

import com.example.blackjackgame.R;

public class ConvertStringToImage {

    public static void convert(ImageView v, String image){
        if(image.equals("avatar1.png")){
            v.setImageResource(R.drawable.avatar1);
        } else {
            if(image.equals("avatar2.png")){
                v.setImageResource(R.drawable.avatar2);
            } else {
                v.setImageResource(R.drawable.avatar3);
            }
        }
    }

}
