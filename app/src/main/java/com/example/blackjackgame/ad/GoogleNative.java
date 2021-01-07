package com.example.blackjackgame.ad;

import android.content.Context;

import com.example.blackjackgame.databinding.FragmentWaitingGameBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

public class GoogleNative {

    private FragmentWaitingGameBinding binding;
    private Context context;
    private AdView mPublisherAdView;

    public GoogleNative(FragmentWaitingGameBinding binding, Context context) {
        this.binding = binding;
        this.context = context;
    }

    public void initAdd(){

        PublisherAdView adView = new PublisherAdView(context);

        adView.setAdSizes(AdSize.BANNER);

        adView.setAdUnitId("/6499/example/banner");

        mPublisherAdView = binding.adViewBannerGoogle;
        AdRequest request = new AdRequest.Builder().build();
        AdSize customAdSize = new AdSize(300, 250);
        mPublisherAdView.loadAd(request);
        adView.setAdSizes(customAdSize);

        AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // Show the ad.
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();

//        mPublisherAdView.loadAd(adLoader);
    }
}
