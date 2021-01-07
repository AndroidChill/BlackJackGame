package com.example.blackjackgame.ad;

import android.content.Context;
import android.widget.Toast;

import com.example.blackjackgame.databinding.FragmentWaitingGameBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

public class GoogleAds {

    private FragmentWaitingGameBinding binding;
    private Context context;
    private AdView mPublisherAdView;

    public GoogleAds(FragmentWaitingGameBinding binding, Context context) {
        this.binding = binding;
        this.context = context;
    }

    public void initAd(){
        PublisherAdView adView = new PublisherAdView(context);

        adView.setAdSizes(AdSize.BANNER);

        adView.setAdUnitId("/6499/example/banner");

        mPublisherAdView = binding.adViewBannerGoogle;
        AdRequest request = new AdRequest.Builder().build();
        AdSize customAdSize = new AdSize(300, 250);
        mPublisherAdView.loadAd(request);
        adView.setAdSizes(customAdSize);

        mPublisherAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }
}
