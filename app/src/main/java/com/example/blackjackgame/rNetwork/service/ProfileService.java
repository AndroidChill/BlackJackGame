package com.example.blackjackgame.rNetwork.service;

import com.example.blackjackgame.rModel.profile.ProfileBody;
import com.example.blackjackgame.rModel.profileAvatar.ProfileAvatarBody;
import com.example.blackjackgame.rModel.profileSend.ProfileSendBody;
import com.example.blackjackgame.rNetwork.request.profile.ProfileRequest;
import com.example.blackjackgame.rNetwork.request.profileAvatar.ProfileAvatarRequest;
import com.example.blackjackgame.rNetwork.request.profileSend.ProfileSendRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProfileService {

    @POST("json")
    Observable<ProfileBody> getProfile(@Body ProfileRequest request);

    @POST("json")
    Observable<ProfileAvatarBody> getProfileAvatar(@Body ProfileAvatarRequest request);

    @POST("json")
    Observable<ProfileSendBody> sendProfile(@Body ProfileSendRequest request);

}
