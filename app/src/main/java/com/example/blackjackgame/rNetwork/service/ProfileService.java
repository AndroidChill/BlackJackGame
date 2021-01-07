package com.example.blackjackgame.rNetwork.service;

import com.example.blackjackgame.network.responce.profile.avatar.AvatarChangeRequest;
import com.example.blackjackgame.rModel.AvatarChangeBody;
import com.example.blackjackgame.rModel.profile.ProfileBody;
import com.example.blackjackgame.rModel.profileAny.ProfileAnyBody;
import com.example.blackjackgame.rModel.profileAvatar.ProfileAvatarBody;
import com.example.blackjackgame.rModel.profileSend.ProfileSendBody;
import com.example.blackjackgame.rNetwork.request.ChangePhotoRequest;
import com.example.blackjackgame.rNetwork.request.profile.ProfileRequest;
import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;
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

    @POST("json")
    Observable<ProfileAnyBody> getProfileAny(@Body ProfileAnyRequest request);

    @POST("json")
    Observable<AvatarChangeBody> getAvatarChange(@Body ChangePhotoRequest request);

}
