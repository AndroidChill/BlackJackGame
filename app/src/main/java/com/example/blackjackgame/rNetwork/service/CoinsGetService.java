package com.example.blackjackgame.rNetwork.service;

import androidx.databinding.ObservableInt;

import com.example.blackjackgame.rModel.coinsGet.CoinsGetBody;
import com.example.blackjackgame.rModel.historyCoins.HistoryCoinsBody;
import com.example.blackjackgame.rNetwork.request.coinsGet.CoinsGetRequest;
import com.example.blackjackgame.rNetwork.request.coinsHistory.CoinsHistoryRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CoinsGetService {

    @POST("json")
    Observable<CoinsGetBody> getListCoins(@Body CoinsGetRequest request);

    @POST("json")
    Observable<HistoryCoinsBody> getHistoryCoins(@Body CoinsHistoryRequest request);


}
