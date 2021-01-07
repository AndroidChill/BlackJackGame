package com.example.blackjackgame.rNetwork.service;

import com.example.blackjackgame.rModel.news.NewsBody;
import com.example.blackjackgame.rModel.startGame.StartGameBody;
import com.example.blackjackgame.rNetwork.request.news.NewsRequest;
import com.example.blackjackgame.rNetwork.request.startGame.StartGameRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NewsService {

    @POST("json")
    Observable<NewsBody> getNewsList(@Body NewsRequest request);

}
