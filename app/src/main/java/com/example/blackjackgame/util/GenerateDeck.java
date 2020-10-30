package com.example.blackjackgame.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.ActivityGame2Binding;
import com.example.blackjackgame.databinding.ActivityGame3Binding;
import com.example.blackjackgame.databinding.ActivityGame4Binding;
import com.example.blackjackgame.ui.activity.Game2Activity;

public class GenerateDeck {

    public static ImageView[] generateDeckSecond(ActivityGame2Binding binding, ConstraintSet set, ImageView[] imageViews, Context context){
        for(int i = 0; i < 55; i++){
            ImageView iv = new ImageView(context);
            iv.setId(View.generateViewId());
            iv.setBackgroundResource(R.drawable.blue_back);
            ConstraintLayout.LayoutParams clpcontactUs = new ConstraintLayout.LayoutParams(
                    100, 100);

            iv.setLayoutParams(clpcontactUs);

            binding.main.addView(iv, 0);

            set.connect(iv.getId(), ConstraintSet.BOTTOM, binding.main.getId(), ConstraintSet.BOTTOM);
            set.connect(iv.getId(), ConstraintSet.TOP, binding.main.getId(), ConstraintSet.TOP);
            set.connect(iv.getId(), ConstraintSet.START, binding.main.getId(), ConstraintSet.START);
            set.connect(iv.getId(), ConstraintSet.END, binding.main.getId(), ConstraintSet.END);

            set.constrainWidth(iv.getId(), convertIntToDp(50, context));
            set.constrainHeight(iv.getId(), convertIntToDp(70, context));

            imageViews[i] = iv;
        }

        set.applyTo(binding.main);

        return imageViews;

    }

    public static ImageView[] generateDeckThird(ActivityGame3Binding binding, ConstraintSet set, ImageView[] imageViews, Context context){
        for(int i = 0; i < 55; i++){
            ImageView iv = new ImageView(context);
            iv.setId(View.generateViewId());
            iv.setBackgroundResource(R.drawable.blue_back);
            ConstraintLayout.LayoutParams clpcontactUs = new ConstraintLayout.LayoutParams(
                    100, 100);

            iv.setLayoutParams(clpcontactUs);

            binding.main.addView(iv, 0);

            set.connect(iv.getId(), ConstraintSet.BOTTOM, binding.main.getId(), ConstraintSet.BOTTOM);
            set.connect(iv.getId(), ConstraintSet.TOP, binding.main.getId(), ConstraintSet.TOP);
            set.connect(iv.getId(), ConstraintSet.START, binding.main.getId(), ConstraintSet.START);
            set.connect(iv.getId(), ConstraintSet.END, binding.main.getId(), ConstraintSet.END);

            set.constrainWidth(iv.getId(), convertIntToDp(50, context));
            set.constrainHeight(iv.getId(), convertIntToDp(70, context));

            imageViews[i] = iv;
        }

        set.applyTo(binding.main);

        return imageViews;

    }

    public static ImageView[] generateDeckFourth(ActivityGame4Binding binding, ConstraintSet set, ImageView[] imageViews, Context context){
        for(int i = 0; i < 55; i++){
            ImageView iv = new ImageView(context);
            iv.setId(View.generateViewId());
            iv.setBackgroundResource(R.drawable.blue_back);
            ConstraintLayout.LayoutParams clpcontactUs = new ConstraintLayout.LayoutParams(
                    100, 100);

            iv.setLayoutParams(clpcontactUs);

            binding.main.addView(iv, 0);

            set.connect(iv.getId(), ConstraintSet.BOTTOM, binding.main.getId(), ConstraintSet.BOTTOM);
            set.connect(iv.getId(), ConstraintSet.TOP, binding.main.getId(), ConstraintSet.TOP);
            set.connect(iv.getId(), ConstraintSet.START, binding.main.getId(), ConstraintSet.START);
            set.connect(iv.getId(), ConstraintSet.END, binding.main.getId(), ConstraintSet.END);

            set.constrainWidth(iv.getId(), convertIntToDp(50, context));
            set.constrainHeight(iv.getId(), convertIntToDp(70, context));

            imageViews[i] = iv;
        }

        set.applyTo(binding.main);

        return imageViews;

    }

    public static int convertIntToDp(int size, Context context){
        return Math.round((int)(size * context.getResources().getDisplayMetrics().density));
    }

}
