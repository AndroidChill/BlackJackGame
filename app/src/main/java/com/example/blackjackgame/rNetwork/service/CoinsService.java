package com.example.blackjackgame.rNetwork.service;

import com.example.blackjackgame.rModel.coins.CoinsBody;
import com.example.blackjackgame.rModel.coinsGet.CoinsGetBody;
import com.example.blackjackgame.rNetwork.request.coins.CoinsRequest;
import com.example.blackjackgame.rNetwork.request.coinsGet.CoinsGetRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CoinsService {

    @POST("json")
    Observable<CoinsBody> getListCoins(@Body CoinsRequest request);


}
