package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Card;
import com.example.blackjackgame.databinding.ActivityGameFiveBinding;
import com.example.blackjackgame.util.CardUtil;

import java.util.concurrent.TimeUnit;

public class GameFiveActivity extends AppCompatActivity {

    private ActivityGameFiveBinding binding;

    private Card[] cards = CardUtil.getCards();

    private ImageView[] imageViews = new ImageView[55];
    private int countImageView = 54;

    //текущая карта, которую раздали
    private int currentCard = 0;

    //последняя карта у второго игрока
    private int firstLastCard = -1;
    private int firstFirstCard = -1;
    private int countFirstCard = 0;
    private int secondLastCard = -1;
    private int thirdLastCard = -1;
    private int fourthFirstCard = -1;
    private int fourthLastCard = -1;
    private int countFourthCard = 0;
    private int fifthLastCard = -1;
    private int fifthFirstCard = -1;
    private int countFifthCard = 0;

    private Integer[] counts = {0,0,0,0,0};

    CountDownTimer mCountDownTimer;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil.setContentView(this, R.layout.activity_game_five);

         cards = CardUtil.getIdShakeCards(cards);

        ConstraintSet set = new ConstraintSet();

        set.clone(this, R.layout.activity_game_five);

         for(int i = 0; i < 55; i++){
             ImageView iv = new ImageView(this);
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

             set.constrainWidth(iv.getId(), convertIntToDp(50));
             set.constrainHeight(iv.getId(), convertIntToDp(70));

             imageViews[i] = iv;
         }

         binding.infoPlayer2.setClickable(true);
         binding.infoPlayer2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 moveToSecondPlayer(imageViews[countImageView], imageViews[countImageView].getId(), set, currentCard);
                 currentCard++;
                 countImageView--;

                 binding.player2.mfProgressBar.setProgress(100);
                 binding.player2.mfProgressBar.setVisibility(View.VISIBLE);
                 binding.player2.view.setVisibility(View.GONE);
                 mCountDownTimer=new CountDownTimer(10000,100) {

                     @Override
                     public void onTick(long millisUntilFinished) {
                         i++;
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
         });

        binding.infoPlayer3.setClickable(true);
        binding.infoPlayer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToThirdPlayer(imageViews[countImageView], imageViews[countImageView].getId(), set, currentCard);
                currentCard++;
                countImageView--;
            }
        });


        binding.infoPlayer4.setClickable(true);
        binding.infoPlayer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToFourthPlayer(imageViews[countImageView], imageViews[countImageView].getId(), set, currentCard);
                currentCard++;
                countImageView--;
            }
        });

        binding.infoPlayer5.setClickable(true);
        binding.infoPlayer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToFifthPlayer(imageViews[countImageView], imageViews[countImageView].getId(), set, currentCard);
                currentCard++;
                countImageView--;
            }
        });

        binding.materialButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToFirstPlayer(imageViews[countImageView], imageViews[countImageView].getId(), set, currentCard);
                currentCard++;
                countImageView--;
            }
        });

        set.applyTo(binding.main);
    }

    private void moveToRewardSecondPlayer(int id, ConstraintSet set){
        set.clear(id, ConstraintSet.START);
        set.clear(id, ConstraintSet.TOP);
        set.clear(id, ConstraintSet.END);
        set.clear(id, ConstraintSet.BOTTOM);

        set.connect(id, ConstraintSet.START, binding.infoPlayer2.getId(), ConstraintSet.START);
        set.connect(id, ConstraintSet.END, binding.infoPlayer2.getId(), ConstraintSet.END);
        set.connect(id, ConstraintSet.TOP, binding.infoPlayer2.getId(), ConstraintSet.BOTTOM);

        TransitionManager.beginDelayedTransition(binding.main);
        set.applyTo(binding.main);
    }

    private void moveToSecondPlayer(View v, int id, ConstraintSet set, int currentCard){

        set.clear(id, ConstraintSet.START);
        set.clear(id, ConstraintSet.TOP);
        set.clear(id, ConstraintSet.END);
        set.clear(id, ConstraintSet.BOTTOM);

        if(secondLastCard == -1){
            set.connect(id, ConstraintSet.START, binding.infoPlayer2.getId() , ConstraintSet.START);
        } else {
            set.connect(id, ConstraintSet.START, secondLastCard , ConstraintSet.START, convertIntToDp(12));
        }
        secondLastCard = id;
        set.connect(id, ConstraintSet.TOP, binding.infoPlayer2.getId(), ConstraintSet.BOTTOM, convertIntToDp(8));
        // enable animation
        TransitionManager.beginDelayedTransition(binding.main);
        set.applyTo(binding.main);

        animateRotationCard(v, currentCard, binding.countPlayer2, 2, set);
    }

    private void moveToThirdPlayer(View v, int id, ConstraintSet set, int currentCard){
        set.clear(id, ConstraintSet.START);
        set.clear(id, ConstraintSet.TOP);
        set.clear(id, ConstraintSet.END);
        set.clear(id, ConstraintSet.BOTTOM);

        if(thirdLastCard == -1){
            set.connect(id, ConstraintSet.START, binding.infoPlayer3.getId() , ConstraintSet.START);
        } else {
            set.connect(id, ConstraintSet.START, thirdLastCard , ConstraintSet.START, convertIntToDp(12));
        }
        thirdLastCard = id;
        set.connect(id, ConstraintSet.TOP, binding.infoPlayer3.getId(), ConstraintSet.BOTTOM, convertIntToDp(8));
        // enable animation
        TransitionManager.beginDelayedTransition(binding.main);
        set.applyTo(binding.main);

        animateRotationCard(v, currentCard, binding.countPlayer3, 3, set);
    }

    private void moveToFourthPlayer(View v, int id, ConstraintSet set, int currentCard){
        set.clear(id, ConstraintSet.START);
        set.clear(id, ConstraintSet.TOP);
        set.clear(id, ConstraintSet.END);
        set.clear(id, ConstraintSet.BOTTOM);

        if(fourthLastCard == -1){
            set.connect(id, ConstraintSet.END, binding.infoPlayer4.getId() , ConstraintSet.END);
            fourthFirstCard = id;
        } else {
            set.connect(id, ConstraintSet.START, fourthLastCard , ConstraintSet.START, convertIntToDp(12));

            //перемещение первой карты
            set.connect(fourthFirstCard, ConstraintSet.END, binding.infoPlayer4.getId() , ConstraintSet.END, countFourthCard * convertIntToDp(12));
        }

        fourthLastCard = id;
        set.connect(id, ConstraintSet.TOP, binding.infoPlayer4.getId(), ConstraintSet.BOTTOM, convertIntToDp(8));
        // enable animation
        TransitionManager.beginDelayedTransition(binding.main);
        set.applyTo(binding.main);

        animateRotationCard(v, currentCard, binding.countPlayer4, 4, set);

        countFourthCard++;
    }

    private void moveToFifthPlayer(View v, int id, ConstraintSet set, int currentCard){
        set.clear(id, ConstraintSet.START);
        set.clear(id, ConstraintSet.TOP);
        set.clear(id, ConstraintSet.END);
        set.clear(id, ConstraintSet.BOTTOM);

        if(fifthLastCard == -1){
            set.connect(id, ConstraintSet.END, binding.infoPlayer5.getId() , ConstraintSet.END);
            fifthFirstCard = id;
        } else {
            set.connect(id, ConstraintSet.START, fifthLastCard , ConstraintSet.START, convertIntToDp(12));

            //перемещение первой карты
            set.connect(fifthFirstCard, ConstraintSet.END, binding.infoPlayer5.getId() , ConstraintSet.END, countFifthCard * convertIntToDp(12));
        }

        fifthLastCard = id;
        set.connect(id, ConstraintSet.TOP, binding.infoPlayer5.getId(), ConstraintSet.BOTTOM, convertIntToDp(8));
        // enable animation
        TransitionManager.beginDelayedTransition(binding.main);
        set.applyTo(binding.main);

        animateRotationCard(v, currentCard, binding.countPlayer5, 5, set);

        countFifthCard++;
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

            set.connect(id, ConstraintSet.START, firstLastCard, ConstraintSet.START, convertIntToDp(12));

            set.connect(firstFirstCard, ConstraintSet.END, binding.main.getId(), ConstraintSet.END, countFirstCard * convertIntToDp(8));
        }

        firstLastCard = id;
        set.connect(id, ConstraintSet.BOTTOM, binding.main.getId(), ConstraintSet.BOTTOM);
        // enable animation
        TransitionManager.beginDelayedTransition(binding.main);
        set.applyTo(binding.main);

        animateRotationCard(v, currentCard, binding.countPlayer1, 1, set);

        countFirstCard++;

    }

    private int convertIntToDp(int size){
        return Math.round((int)(size * getResources().getDisplayMetrics().density));
    }

    private void animateRotationCard(View v, int currentCard, TextView count, int numberPlayer, ConstraintSet set){

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_anim);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) { }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            v.setBackgroundResource(cards[currentCard].getId());

                            count.setText(String.valueOf(counts[numberPlayer-1] + cards[currentCard].getCount()));
                            counts[numberPlayer-1] += cards[currentCard].getCount();

                            moveToRewardSecondPlayer(binding.reward2.getId(), set);

                            Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_to_gone);
                            binding.player2.moneyPlayer3.startAnimation(animation12);

                            binding.reward2.setVisibility(View.GONE);

                            Animation animation121 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_to_visible);
                            binding.player2.tvPlus.startAnimation(animation121);

                            if(counts[0] > 21){
                                binding.infoPlayer1.setClickable(false);
                            }
                            if(counts[1] > 21){
                                binding.infoPlayer2.setClickable(false);
                            }
                            if(counts[2] > 21){
                                binding.infoPlayer3.setClickable(false);
                            }
                            if(counts[3] > 21){
                                binding.infoPlayer4.setClickable(false);
                            }
                            if(counts[4] > 21){
                                binding.infoPlayer5.setClickable(false);
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) { }
                    });
                    v.setAnimation(animation);
                    v.startAnimation(animation);
    }

}