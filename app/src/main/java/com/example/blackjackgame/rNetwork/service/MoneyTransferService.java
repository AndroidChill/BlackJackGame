package com.example.blackjackgame.rNetwork.service;

import com.example.blackjackgame.rModel.moneyTransfer.MoneyTransferBody;
import com.example.blackjackgame.rModel.startGame.StartGameBody;
import com.example.blackjackgame.rNetwork.request.moneyTransfer.MoneyTransferRequest;
import com.example.blackjackgame.rNetwork.request.startGame.StartGameRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MoneyTransferService {

    @POST("json")
    Observable<MoneyTransferBody> getMoneyTransfer(@Body MoneyTransferRequest request);

}
