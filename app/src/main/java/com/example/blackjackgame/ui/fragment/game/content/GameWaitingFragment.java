package com.example.blackjackgame.ui.fragment.game.content;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.blackjackgame.R;
import com.example.blackjackgame.ad.GoogleAds;
import com.example.blackjackgame.ad.YandexBannerAds;
import com.example.blackjackgame.databinding.FragmentWaitingGameBinding;
import com.yandex.mobile.ads.AdEventListener;
import com.yandex.mobile.ads.AdRequest;
import com.yandex.mobile.ads.AdRequestError;
import com.yandex.mobile.ads.AdSize;
import com.yandex.mobile.ads.AdView;

public class GameWaitingFragment extends Fragment {

    private FragmentWaitingGameBinding binding;
    private static final String DEMO_BLOCK_ID = "R-M-DEMO-adaptive-sticky";

    private AdView mAdView;

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

        YandexBannerAds yandexBannerAds = new YandexBannerAds(binding, getContext());
        yandexBannerAds.initYandex();

        GoogleAds googleAds = new GoogleAds(binding, getContext());
        googleAds.initAd();

        return binding.getRoot();
    }



}
