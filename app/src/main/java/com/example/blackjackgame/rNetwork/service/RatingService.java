package com.example.blackjackgame.rNetwork.service;

import com.example.blackjackgame.rModel.profile.ProfileBody;
import com.example.blackjackgame.rModel.rating.RatingBody;
import com.example.blackjackgame.rModel.ratingLucky.RatingLuckyBody;
import com.example.blackjackgame.rModel.ratingRich.RatingRichBody;
import com.example.blackjackgame.rNetwork.request.profile.ProfileRequest;
import com.example.blackjackgame.rNetwork.request.rating.RatingRequest;
import com.example.blackjackgame.rNetwork.request.ratingLucky.RatingLuckyRequest;
import com.example.blackjackgame.rNetwork.request.ratingRich.RatingRichRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RatingService {

    @POST("json")
    Observable<RatingRichBody> getRatingRich(@Body RatingRichRequest request);

    @POST("json")
    Observable<RatingLuckyBody> getRatingLucky(@Body RatingLuckyRequest request);

    @POST("json")
    Observable<RatingBody> getRating(@Body RatingRequest request);




}
