package com.example.blackjackgame.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.blackjackgame.R;

public class ConvertStringToImage {
    public static void convert(ImageView v, String image){
        if(image == null) {
            return;
        }
        Context context = v.getContext();

        String[] str = image.split(".png");

        if(str.length > 0) {
            int id = context.getResources().getIdentifier("i" + str[0], "drawable", context.getPackageName());
            if(id == 0){
                v.setImageResource(R.drawable.i_default);

            } else {
                v.setImageResource(id);
            }
            return ;
        }

        v.setImageResource(R.drawable.i_default);

    }
}
