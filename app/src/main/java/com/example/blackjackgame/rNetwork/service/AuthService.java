package com.example.blackjackgame.rNetwork.service;

import com.example.blackjackgame.network.responce.sign.SignGuestRequest;
import com.example.blackjackgame.rModel.registration.RegistrationBody;
import com.example.blackjackgame.rModel.registration.RegistrationStartBody;
import com.example.blackjackgame.rModel.sign.SignGuestBody;
import com.example.blackjackgame.rModel.sign.SignInEmailBody;
import com.example.blackjackgame.rModel.sign.SignInEmailCodeBody;
import com.example.blackjackgame.rModel.sign.SignInEmailStartBody;
import com.example.blackjackgame.rNetwork.request.registration.RegistrationRequest;
import com.example.blackjackgame.rNetwork.request.registration.RegistrationStartRequest;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailCodeRequest;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailRequest;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailStartRequest;
import com.example.blackjackgame.rNetwork.request.sign.SignInGuestRequest;
import com.example.blackjackgame.rViewModel.signInEmailCode.SignInEmailCodeRepository;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("json")
    Observable<SignInEmailBody> signEmail(@Body SignInEmailRequest request);

    @POST("json")
    Observable<SignInEmailStartBody> startSignEmail(@Body SignInEmailStartRequest request);

    @POST("json")
    Observable<SignInEmailCodeBody> loginEnd(@Body SignInEmailCodeRequest request);

    @POST("json")
    Observable<SignGuestBody> signGuest(@Body SignInGuestRequest request);

    @POST("json")
    Observable<RegistrationStartBody> registrationStart(@Body RegistrationStartRequest request);

    @POST("json")
    Observable<RegistrationBody> registration(@Body RegistrationRequest request);
}
