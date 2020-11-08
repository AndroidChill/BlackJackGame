package com.example.blackjackgame.util;

import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;

public class ImageLoader {

    public static void glideLoad(FragmentActivity fragment, String url, ImageView view){
        Glide
                .with(fragment)
                .load(url)
                .centerCrop()
                .into(view);
    }

}
