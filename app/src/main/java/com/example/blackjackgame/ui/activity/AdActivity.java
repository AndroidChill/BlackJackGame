package com.example.blackjackgame.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.ActivityAdBinding;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class AdActivity extends AppCompatActivity {

    private ActivityAdBinding binding;
    private RewardedAd rewardedAd;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ad);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        this.rewardedAd = new RewardedAd(this, "ca-app-pub-3940256099942544/5224354917");
        RewardedAdLoadCallback callback = new RewardedAdLoadCallback(){
            @Override
            public void onRewardedAdFailedToLoad(LoadAdError loadAdError) {
                super.onRewardedAdFailedToLoad(loadAdError);
                Log.i("tag", "onRewardedAdFailedToLoad: ");
            }

            @Override
            public void onRewardedAdLoaded() {
                super.onRewardedAdLoaded();


                binding.progress.setVisibility(View.GONE);

                showAd();
            }
        };
        this.rewardedAd.loadAd(new AdRequest.Builder().build(), callback);
    }


    public void showAd(){
        if (this.rewardedAd.isLoaded()){
            RewardedAdCallback callback = new RewardedAdCallback() {

                @Override
                public void onRewardedAdFailedToShow(int i) {
                    super.onRewardedAdFailedToShow(i);
                }

                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    Log.i("tag", "onUserEarnedReward: ");
                    sharedPreferences.edit().putBoolean(Constant.isCompleteTask,true).apply();
                    finish();
                }

                @Override
                public void onRewardedAdOpened() {
                    super.onRewardedAdOpened();
                    Log.i("tag", "onRewardedAdOpened: ");
                }

                @Override
                public void onRewardedAdClosed() {
                    super.onRewardedAdClosed();
                    Log.i("tag", "onRewardedAdClosed: ");
                    finish();
                }

                @Override
                public void onRewardedAdFailedToShow(AdError adError) {
                    super.onRewardedAdFailedToShow(adError);
                    Log.i("tag", "onRewardedAdFailedToShow: ");
                    finish();
                }
            };
            this.rewardedAd.show(this, callback);
        } else {
            Log.i("tag", "not loaded: ");
        }
    }
}