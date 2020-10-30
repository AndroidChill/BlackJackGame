package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Card;
import com.example.blackjackgame.databinding.ActivityGame3Binding;
import com.example.blackjackgame.util.CardUtil;
import com.example.blackjackgame.util.GenerateDeck;

import java.util.concurrent.TimeUnit;

public class Game3Activity extends AppCompatActivity {

    private ActivityGame3Binding binding;

    private Card[] cards = CardUtil.getCards();
    private ImageView[] imageViews = new ImageView[55];

    private CountDownTimer mCountDownTimer;

    private int[] counts = {
            0,
            0,
            0
    };

    private int firstLastCard = -1;
    private int firstFirstCard = -1;
    private int countFirstCard = 0;

    private int secondLastCard = -1;
    private int thirdFirstCard = -1;
    private int thirdLastCard = -1;
    private int countThirdCard = 0;

    private int lastImageViews = 0;
    private int lastCard = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game3);

        ConstraintSet set = new ConstraintSet();
        set.clone(binding.main);

        cards = CardUtil.getIdShakeCards(cards);
        GenerateDeck.generateDeckThird(binding, set, imageViews, this);

        binding.materialButton3.setOnClickListener(v -> {
            moveToFirstPlayer(imageViews[54 - lastImageViews], imageViews[54 - lastImageViews].getId(), set, lastCard);
            lastImageViews++;
            lastCard++;
        });

        binding.infoPlayer2.setOnClickListener(v -> {
            moveToSecondPlayer(imageViews[54 - lastImageViews], imageViews[54 - lastImageViews].getId(), set, lastCard);
            lastImageViews++;
            lastCard++;
        });

        binding.infoPlayer3.setOnClickListener(v -> {
            moveToThirdPlayer(imageViews[54 - lastImageViews], imageViews[54 - lastImageViews].getId(), set, lastCard);
            lastImageViews++;
            lastCard++;
        });

        startFirstCountDownTimer(1, set);
        startSecondCountDownTimer(1, set);
        startThirdCountDownTimer(1, set);
        startFirstCountDownTimer(2, set);
        startSecondCountDownTimer(2, set);
        startThirdCountDownTimer(2, set);

//        moveToFirstPlayer(imageViews[54 - lastImageViews], imageViews[54 - lastImageViews].getId(), set, lastCard);
//        lastImageViews++;
//        lastCard++;
//        moveToFirstPlayer(imageViews[54 - lastImageViews], imageViews[54 - lastImageViews].getId(), set, lastCard);
//        lastImageViews++;
//        lastCard++;

    }

    private void moveDoubleToSecondPlayer(View v, int id, ConstraintSet set, int currentCard){
        boolean active = false;
        while(!active){

        }
    }

    private void moveToSecondPlayer(View v, int id, ConstraintSet set, int currentCard){
        set.clear(id, ConstraintSet.START);
        set.clear(id, ConstraintSet.TOP);
        set.clear(id, ConstraintSet.END);
        set.clear(id, ConstraintSet.BOTTOM);

        if(secondLastCard == -1){
            set.connect(id, ConstraintSet.START, binding.infoPlayer2.getId() , ConstraintSet.START);
        } else {
            set.connect(id, ConstraintSet.START, secondLastCard, ConstraintSet.START, GenerateDeck.convertIntToDp(12, this));
        }
        secondLastCard = id;
        set.connect(id, ConstraintSet.TOP, binding.infoPlayer2.getId(), ConstraintSet.BOTTOM, GenerateDeck.convertIntToDp(8, this));
        // enable animation
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(1000);
        TransitionManager.beginDelayedTransition(binding.main, autoTransition);
        set.applyTo(binding.main);

        animateRotationCard(v, currentCard, binding.countPlayer2, 2, set);
    }

    private void moveToThirdPlayer(View v, int id, ConstraintSet set, int currentCard){
        set.clear(id, ConstraintSet.START);
        set.clear(id, ConstraintSet.TOP);
        set.clear(id, ConstraintSet.END);
        set.clear(id, ConstraintSet.BOTTOM);

        if(thirdLastCard == -1){
            set.connect(id, ConstraintSet.END, binding.infoPlayer3.getId() , ConstraintSet.END);
            thirdFirstCard = id;
        } else {
            set.connect(id, ConstraintSet.START, thirdLastCard, ConstraintSet.START, GenerateDeck.convertIntToDp(12, this));
            //перемещение первой карты
            set.connect(thirdFirstCard, ConstraintSet.END, binding.infoPlayer3.getId() , ConstraintSet.END, countThirdCard * GenerateDeck.convertIntToDp(12, this));
        }

        thirdLastCard = id;
        set.connect(id, ConstraintSet.TOP, binding.infoPlayer3.getId(), ConstraintSet.BOTTOM, GenerateDeck.convertIntToDp(8, this));
        // enable animation
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(1000);
        TransitionManager.beginDelayedTransition(binding.main, autoTransition);
        set.applyTo(binding.main);

        animateRotationCard(v, currentCard, binding.countPlayer3, 3, set);

        countThirdCard++;
    }
    private void moveToFirstPlayer(View v, int id, ConstraintSet set, int currentCard){
        set.clear(id, ConstraintSet.START);
        set.clear(id, ConstraintSet.TOP);
        set.clear(id, ConstraintSet.END);
        set.clear(id, ConstraintSet.BOTTOM);

        if(firstLastCard == -1){
            set.connect(id, ConstraintSet.START, binding.main.getId(), ConstraintSet.START);
            set.connect(id, ConstraintSet.END, binding.main.getId(), ConstraintSet.END);

            firstFirstCard = id;
        } else {

            set.connect(id, ConstraintSet.START, firstLastCard, ConstraintSet.START, GenerateDeck.convertIntToDp(12, this));

            set.connect(firstFirstCard, ConstraintSet.END, binding.main.getId(), ConstraintSet.END, countFirstCard * GenerateDeck.convertIntToDp(8, this));
        }

        firstLastCard = id;
        set.connect(id, ConstraintSet.BOTTOM, binding.main.getId(), ConstraintSet.BOTTOM);
        // enable animation
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(1000);
        TransitionManager.beginDelayedTransition(binding.main, autoTransition);
        set.applyTo(binding.main);;

        animateRotationCard(v, currentCard, binding.countPlayer1, 1, set);

        countFirstCard++;

    }


    private void animateRotationCard(View v, int currentCard, TextView count, int numberPlayer, ConstraintSet set){

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setBackgroundResource(cards[currentCard].getId());

//                activateProgressbar(10, numberPlayer);

                switch (numberPlayer){
                    case 1:
                        setCountPlayer(binding.countPlayer1, 1, currentCard);
                        break;
                    case 2:
                        setCountPlayer(binding.countPlayer2, 2, currentCard);
                        break;
                    case 3:
                        setCountPlayer(binding.countPlayer3, 3, currentCard);
                        break;
                }

//                animationReward(numberPlayer);

            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        v.setAnimation(animation);
        v.startAnimation(animation);
    }

    private void animationReward(int numberPlayer){
        switch (numberPlayer){
            case 1:
                Animation animation1211111 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_to_gone);
                binding.player1.moneyPlayer3.startAnimation(animation1211111);

                Animation animation121111111 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_to_visible);
                binding.player1.tvPlus.startAnimation(animation121111111);
                break;
            case 2:
                Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_to_gone);
                binding.player2.moneyPlayer3.startAnimation(animation12);

                Animation animation121 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_to_visible);
                binding.player2.tvPlus.startAnimation(animation121);
                break;
            case 3:
                Animation animation12111 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_to_gone);
                binding.player3.moneyPlayer3.startAnimation(animation12111);

                Animation animation1211 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_to_visible);
                binding.player3.tvPlus.startAnimation(animation1211);
        }
    }

    //установка очков у игрока
    private void setCountPlayer(TextView count, int numberPlayer, int currentCard1){
        counts[numberPlayer-1] += cards[currentCard1].getCount();
        count.setText(String.valueOf(counts[numberPlayer-1]));

    }

    private void activateProgressbar(int interval, int numberPlayer){

        switch (numberPlayer){
            case 1:
                binding.player1.mfProgressBar.setProgress(100);
                binding.player1.mfProgressBar.setVisibility(View.VISIBLE);
                binding.player1.view.setVisibility(View.GONE);
                mCountDownTimer =new CountDownTimer(interval * 1000,100) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        binding.player1.mfProgressBar.setProgress((int)(millisUntilFinished/100));
                    }

                    @Override
                    public void onFinish() {
                        //Do what you want
                        binding.player1.mfProgressBar.setVisibility(View.GONE);
                        binding.player1.view.setVisibility(View.VISIBLE);
                    }
                };
                mCountDownTimer.start();
                break;
            case 2:
                binding.player2.mfProgressBar.setProgress(100);
                binding.player2.mfProgressBar.setVisibility(View.VISIBLE);
                binding.player2.view.setVisibility(View.GONE);
                mCountDownTimer =new CountDownTimer(interval * 1000,100) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        binding.player2.mfProgressBar.setProgress((int)(millisUntilFinished/100));
                    }

                    @Override
                    public void onFinish() {
                        //Do what you want
                        binding.player2.mfProgressBar.setVisibility(View.GONE);
                        binding.player2.view.setVisibility(View.VISIBLE);
                    }
                };
                mCountDownTimer.start();
                break;
            case 3:
                binding.player3.mfProgressBar.setProgress(100);
                binding.player3.mfProgressBar.setVisibility(View.VISIBLE);
                binding.player3.view.setVisibility(View.GONE);
                mCountDownTimer =new CountDownTimer(interval * 1000,100) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        binding.player3.mfProgressBar.setProgress((int)(millisUntilFinished/100));
                    }

                    @Override
                    public void onFinish() {
                        //Do what you want
                        binding.player3.mfProgressBar.setVisibility(View.GONE);
                        binding.player3.view.setVisibility(View.VISIBLE);
                    }
                };
                mCountDownTimer.start();
                break;
        }

        binding.player2.mfProgressBar.setProgress(100);
        binding.player2.mfProgressBar.setVisibility(View.VISIBLE);
        binding.player2.view.setVisibility(View.GONE);
        mCountDownTimer =new CountDownTimer(interval * 1000,100) {

            @Override
            public void onTick(long millisUntilFinished) {
                binding.player2.mfProgressBar.setProgress((int)(millisUntilFinished/100));
            }

            @Override
            public void onFinish() {
                //Do what you want
                binding.player2.mfProgressBar.setVisibility(View.GONE);
                binding.player2.view.setVisibility(View.VISIBLE);
            }
        };
        mCountDownTimer.start();
    }

    private void startFirstCountDownTimer(int interval, ConstraintSet set){
        mCountDownTimer =new CountDownTimer(interval * 1000,100) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                moveToFirstPlayer(imageViews[54 - lastImageViews], imageViews[54 - lastImageViews].getId(), set, lastCard);
                lastImageViews++;
                lastCard++;
            }
        };
        mCountDownTimer.start();
    }

    private void startSecondCountDownTimer(int interval, ConstraintSet set){
        mCountDownTimer =new CountDownTimer(interval * 1000,100) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                moveToSecondPlayer(imageViews[54 - lastImageViews], imageViews[54 - lastImageViews].getId(), set, lastCard);
                lastImageViews++;
                lastCard++;
            }
        };
        mCountDownTimer.start();
    }
    private void startThirdCountDownTimer(int interval, ConstraintSet set){
        mCountDownTimer =new CountDownTimer(interval * 1000,100) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                moveToThirdPlayer(imageViews[54 - lastImageViews], imageViews[54 - lastImageViews].getId(), set, lastCard);
                lastImageViews++;
                lastCard++;
            }
        };
        mCountDownTimer.start();
    }
}