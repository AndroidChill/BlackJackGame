package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private boolean isActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);

//        val constraintSet = ConstraintSet()
//        constraintSet.clone(this, R.layout.circuit_detail)
//
//        val transition = ChangeBounds()
//        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
//        transition.duration = 1200
//
//        TransitionManager.beginDelayedTransition(constraint, transition)
//        constraintSet.applyTo(constraint)

        binding.imageView18.setOnClickListener(v -> {

//            ConstraintSet constraintSet = new ConstraintSet();
//            constraintSet.clone(binding.image);
//            constraintSet.clone(this, R.layout.activity_game);
//
//            ChangeBounds transition = new ChangeBounds();
//            transition.setInterpolator(new AnticipateOvershootInterpolator(10.0f));
//            transition.setDuration(1200);
//
//            TransitionManager.beginDelayedTransition(binding.main, transition);
//            constraintSet.applyTo(binding.main);
//
            if(isActive){
                Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.card_anim);
                binding.imageView18.startAnimation(aniRotate);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.imageView18.setBackgroundResource(R.drawable.queen_2);
                    }
                }, 500);


            } else {
                Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.card_anim);
                binding.imageView18.startAnimation(aniRotate);
                binding.imageView18.setBackgroundResource(R.drawable.queen_1);
            }
            isActive = !isActive;
        });

    }
}