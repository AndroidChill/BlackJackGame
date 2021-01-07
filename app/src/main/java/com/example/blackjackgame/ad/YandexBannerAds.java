package com.example.blackjackgame.ad;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.blackjackgame.databinding.FragmentWaitingGameBinding;
import com.example.blackjackgame.ui.fragment.game.content.GameWaitingFragment;
import com.yandex.mobile.ads.AdEventListener;
import com.yandex.mobile.ads.AdRequest;
import com.yandex.mobile.ads.AdRequestError;
import com.yandex.mobile.ads.AdSize;
import com.yandex.mobile.ads.AdView;

public class YandexBannerAds {

    private FragmentWaitingGameBinding binding;
    private Context context;
    private AdView mAdView;
    private static final String DEMO_BLOCK_ID = "R-M-DEMO-300x250";

    public YandexBannerAds(FragmentWaitingGameBinding binding, Context context){
        this.binding = binding;
        this.context = context;
    }

    public void initYandex(){
        mAdView = binding.adViewBannerYandex;


        // Replace demo R-M-DEMO-adaptive-sticky with actual Block ID
        mAdView.setBlockId(DEMO_BLOCK_ID);
        mAdView.setAdEventListener(new StickyBannerEventListener());
        mAdView.setAdSize(AdSize.BANNER_300x300);
        final AdRequest adRequest = AdRequest.builder().build();
        mAdView.loadAd(adRequest);
    }

    private class StickyBannerEventListener extends AdEventListener.SimpleAdEventListener {
        @Override
        public void onAdLoaded() {
            Toast.makeText(context, "Ad loaded", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdFailedToLoad(@NonNull final AdRequestError error) {
            Toast.makeText(context, "Failed to load with reason: " +
                    error.getDescription(), Toast.LENGTH_SHORT).show();
        }
    }

}
