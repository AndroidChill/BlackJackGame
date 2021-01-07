package com.example.blackjackgame.rNetwork.service;

import com.example.blackjackgame.rModel.inviteGame.InviteGameApplyBody;
import com.example.blackjackgame.rModel.inviteGame.InviteGameCancelBody;
import com.example.blackjackgame.rModel.profile.ProfileBody;
import com.example.blackjackgame.rModel.startGame.StartGameBody;
import com.example.blackjackgame.rNetwork.request.inviteGame.InviteGameApplyRequest;
import com.example.blackjackgame.rNetwork.request.inviteGame.InviteGameCancelRequest;
import com.example.blackjackgame.rNetwork.request.profile.ProfileRequest;
import com.example.blackjackgame.rNetwork.request.startGame.StartGameRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GameService {

    @POST("json")
    Observable<StartGameBody> getStartGame(@Body StartGameRequest request);

    @POST("json")
    Observable<InviteGameApplyBody> applyGame(@Body InviteGameApplyRequest request);

    @POST("json")
    Observable<InviteGameCancelBody> cancelGame(@Body InviteGameCancelRequest request);


}
