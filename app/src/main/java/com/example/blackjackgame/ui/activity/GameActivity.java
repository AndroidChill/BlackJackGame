package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.ActivityGameBinding;
import com.example.blackjackgame.util.CardUtil;

import java.util.LinkedList;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private boolean isActive = true;

    int countCardPlayer1 = 0;
    int countCardPlayer2 = 0;
    int countCardPlayer3 = 0;
    int countCardPlayer4 = 0;
    int countCardPlayer5 = 0;

    private LinkedList<ImageView> images = new LinkedList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        //создание стопки игральных карт
        for(int i = 0; i < 54; i++){

            ImageView childView = new ImageView(this);

            ConstraintLayout.LayoutParams clpcontactUs = new ConstraintLayout.LayoutParams(
                    (int) (70 * getResources().getDisplayMetrics().density), (int) (110 * getResources().getDisplayMetrics().density));

            childView.setLayoutParams(clpcontactUs);

            childView.setBackgroundResource(R.drawable.blue_back);
            childView.setId(View.generateViewId());
            binding.main.addView(childView, 0);
            images.add(childView);

        }

        ConstraintSet set = new ConstraintSet();
        set.clone(binding.main);

        //делаем привязку карт отосительно сторон экрана
        for(ImageView childView : images){
            set.connect(childView.getId(), ConstraintSet.TOP, binding.main.getId(), ConstraintSet.TOP);
            set.connect(childView.getId(), ConstraintSet.BOTTOM, binding.main.getId(), ConstraintSet.BOTTOM);
            set.connect(childView.getId(), ConstraintSet.START, binding.main.getId(), ConstraintSet.START);
            set.connect(childView.getId(), ConstraintSet.END, binding.main.getId(), ConstraintSet.END);
        }

        images = CardUtil.getShakeCards(images);

        for(ImageView imageView : images){

            imageView.setClickable(true);

            imageView.setOnClickListener(v -> {
                moveCard(set, v);
                TransitionManager.beginDelayedTransition(binding.main);
                set.applyTo(binding.main);
            });
        }

        changeConstraints(set);

        set.applyTo(binding.main);

    }

            int backCard = CardUtil.getIdResourceBackCard();
    private void changeConstraints(ConstraintSet set) {
        set.clear(binding.imageView18.getId(), ConstraintSet.END);
        set.clear(binding.imageView18.getId(), ConstraintSet.TOP);
        set.clear(binding.imageView18.getId(), ConstraintSet.BOTTOM);
        set.clear(binding.imageView18.getId(), ConstraintSet.START);

        set.connect(binding.imageView18.getId(), ConstraintSet.START, binding.player2.getId(), ConstraintSet.START);
        set.connect(binding.imageView18.getId(), ConstraintSet.BOTTOM, binding.player2.getId(), ConstraintSet.TOP, 16);
    }
    private void moveCard(ConstraintSet set, View v) {
        set.clear(v.getId(), ConstraintSet.END);
        set.clear(v.getId(), ConstraintSet.TOP);
        set.clear(v.getId(), ConstraintSet.BOTTOM);
        set.clear(v.getId(), ConstraintSet.START);

        set.connect(v.getId(), ConstraintSet.START, binding.imageView18.getId(), ConstraintSet.START, (int) (24 * countCardPlayer2 * getResources().getDisplayMetrics().density));
        set.connect(v.getId(), ConstraintSet.BOTTOM, binding.imageView18.getId(), ConstraintSet.TOP, 16);

        countCardPlayer2++;
    }


}