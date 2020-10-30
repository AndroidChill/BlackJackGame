package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Card;
import com.example.blackjackgame.databinding.ActivityGame2Binding;
import com.example.blackjackgame.util.CardUtil;
import com.example.blackjackgame.util.GenerateDeck;

public class Game2Activity extends AppCompatActivity {

    private ActivityGame2Binding binding;

    private Card[] cards = CardUtil.getCards();
    private ImageView[] imageViews = new ImageView[55];

    CountDownTimer mCountDownTimer;

    private int currentCard = 0;
    private TextView count;
    private int numberPlayer;
    private ConstraintSet set;

    private int[] counts = {
            0,
            0
    };

    private int lastSecondCard = -1;
    private int firstSecondCard = -1;
    private int countSecondCard = 0;
    private int firstLastCard = -1;
    private int firstFirstCard = -1;
    private int countFirstCard = 0;

    private int currentImage = 0;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game2);

        ConstraintSet set = new ConstraintSet();
        set.clone(this, R.layout.activity_game2);

        cards = CardUtil.getIdShakeCards(cards);
        GenerateDeck.generateDeckSecond(binding, set, imageViews, this);

        binding.infoPlayer2.setOnClickListener(v -> {
            moveToSecondPlayer(imageViews[imageViews.length - currentImage - 1], set, currentCard);
            currentImage++;
            currentCard++;
        });

        binding.materialButton3.setOnClickListener(v -> {

            activateProgressbar(10, 1);

            moveToFirstPlayer(imageViews[imageViews.length - currentImage - 1], imageViews[imageViews.length - currentImage - 1].getId(), set, currentCard);
            currentImage++;
            currentCard++;
        });

        moveToFirstPlayer(imageViews[imageViews.length - currentImage - 1], imageViews[imageViews.length - currentImage - 1].getId(), set, currentCard);
        currentImage++;
        currentCard++;
        moveToFirstPlayer(imageViews[imageViews.length - currentImage - 1], imageViews[imageViews.length - currentImage - 1].getId(), set, currentCard);
        currentImage++;
        currentCard++;
        moveToSecondPlayer(imageViews[imageViews.length - currentImage - 1], set, currentCard);
        currentImage++;
        currentCard++;
        moveToSecondPlayer(imageViews[imageViews.length - currentImage - 1], set, currentCard);
        currentImage++;
        currentCard++;

    }

    private void moveToSecondPlayer(View v, ConstraintSet set, int currentCard1){

//        binding.countPlayer2.setVisibility(View.VISIBLE);

        clearConstraint(v, set);
        //если еще нет ни одной карты
        if(lastSecondCard == -1){
            connectHorizontalFirstCard(v, set);
        } else {
            connectHorizontalOtherCard(v, set);
        }
        lastSecondCard = v.getId();
        connectVerticalSecondCard(v, set);
        applyAnimation(set);
        animateRotationCard(v, currentCard1, binding.countPlayer1, 2, set);
        countSecondCard++;

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
        TransitionManager.beginDelayedTransition(binding.main);
        set.applyTo(binding.main);

        animateRotationCard(v, currentCard, binding.countPlayer1, 1, set);

        countFirstCard++;

    }

    private void animateRotationCard(View v, int currentCard1, TextView count, int numberPlayer, ConstraintSet set){
        this.v = v;
        this.count = count;
        this.numberPlayer = numberPlayer;
        this.set = set;

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setBackgroundResource(cards[currentCard1].getId());

                if(numberPlayer == 1){
                    setCountPlayer(binding.countPlayer1, numberPlayer, currentCard1);
                } else {
                    setCountPlayer(binding.countPlayer2, numberPlayer, currentCard1);
                }

                animationReward(binding.player2.moneyPlayer3, binding.player2.tvPlus);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        v.setAnimation(animation);
        v.startAnimation(animation);
    }

    private void clearConstraint(View v, ConstraintSet set){
        set.clear(v.getId(), ConstraintSet.START);
        set.clear(v.getId(), ConstraintSet.TOP);
        set.clear(v.getId(), ConstraintSet.END);
        set.clear(v.getId(), ConstraintSet.BOTTOM);
    }

    private void connectHorizontalFirstCard(View v, ConstraintSet set){
        set.connect(v.getId(), ConstraintSet.START, binding.main.getId(), ConstraintSet.START);
        set.connect(v.getId(), ConstraintSet.END, binding.main.getId(), ConstraintSet.END);
        firstSecondCard = v.getId();
    }

    private void connectHorizontalOtherCard(View v, ConstraintSet set){
        set.connect(v.getId(), ConstraintSet.START, lastSecondCard, ConstraintSet.START, GenerateDeck.convertIntToDp(12, this));
        set.connect(firstSecondCard, ConstraintSet.END, binding.main.getId(), ConstraintSet.END, countSecondCard * GenerateDeck.convertIntToDp(8, this));
    }

    private void connectVerticalSecondCard(View v, ConstraintSet set){
        set.connect(v.getId(), ConstraintSet.TOP, binding.infoPlayer2.getId(), ConstraintSet.BOTTOM, GenerateDeck.convertIntToDp(8, this));
    }

    private void animationReward(TextView tv1, TextView tv2){

        Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_to_gone);
        tv1.startAnimation(animation12);

        Animation animation121 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_to_visible);
        tv2.startAnimation(animation121);

    }

    //установка очков у игрока
    private void setCountPlayer(TextView count, int numberPlayer, int currentCard1){
        counts[numberPlayer-1] += cards[currentCard1].getCount();
        count.setText(String.valueOf(counts[numberPlayer-1]));

    }

    private void applyAnimation(ConstraintSet set){
        TransitionManager.beginDelayedTransition(binding.main);
        set.applyTo(binding.main);
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

}