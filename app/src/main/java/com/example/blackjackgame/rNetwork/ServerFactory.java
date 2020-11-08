package com.example.blackjackgame.rNetwork;

import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.rModel.profile.Profile;
import com.example.blackjackgame.rNetwork.service.AuthService;
import com.example.blackjackgame.rNetwork.service.CoinsGetService;
import com.example.blackjackgame.rNetwork.service.ProfileService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerFactory<T> {

    private static ServerFactory mInstance;
    private static final String BASE_URL = "http://mazit.ru/api/game/debug4.php/";
    private Retrofit retrofit;

    private ServerFactory() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
    public static ServerFactory getInstance() {
        if (mInstance == null) {
            mInstance = new ServerFactory();
        }
        return mInstance;
    }


    public AuthService getSignApi(){
        return retrofit.create(AuthService.class);
    }

    public ProfileService getProfileApi(){
        return retrofit.create(ProfileService.class);
    }

    public CoinsGetService getCoinsGetApi(){
        return retrofit.create(CoinsGetService.class);
    }

}
