package com.example.blackjackgame.ui.fragment.game.content;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentWaitingGameBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class GameWaitingFragment extends Fragment {

    private FragmentWaitingGameBinding binding;

    public static GameWaitingFragment newInstance() {

        Bundle args = new Bundle();

        GameWaitingFragment fragment = new GameWaitingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_waiting_game, container, false);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.progress.setProgress((int)(millisUntilFinished/1000));
                binding.progressText.setText(String.valueOf((int)(millisUntilFinished/1000)));
            }

            public void onFinish() {
                binding.progress.setVisibility(View.GONE);
                binding.progressText.setText("0");
            }

        }.start();

        AdView mAdView = new AdView(getContext());

        mAdView.setAdSize(AdSize.BANNER);

        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an card_ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an card_ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an card_ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an card_ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an card_ad.
            }
        });

        return binding.getRoot();
    }
}
