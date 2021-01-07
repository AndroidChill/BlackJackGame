package com.example.blackjackgame.rNetwork.service;

import com.example.blackjackgame.rModel.friends.all.FriendsAllBody;
import com.example.blackjackgame.rModel.friends.all.FriendsCreateBody;
import com.example.blackjackgame.rModel.friends.all.FriendsSearchBody;
import com.example.blackjackgame.rModel.friends.referrals.FriendsReferralsBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestAddBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestCancelBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestDelBody;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsCreateRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsSearchRequest;
import com.example.blackjackgame.rNetwork.request.friends.referals.FriendsReferralsRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestAddRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestCancelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestDelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FriendsService {

    @POST("json")
    Observable<FriendsAllBody> getAllFriends(@Body FriendsAllRequest request);

    @POST("json")
    Observable<FriendsSearchBody> getSearchFriends(@Body FriendsSearchRequest request);

    @POST("json")
    Observable<FriendsReferralsBody> getReferralsFriends(@Body FriendsReferralsRequest request);

    @POST("json")
    Observable<FriendsRequestBody> getRequestFriends(@Body FriendsRequestRequest request);

    @POST("json")
    Observable<FriendsRequestAddBody> getRequestAddFriends(@Body FriendsRequestAddRequest request);

    @POST("json")
    Observable<FriendsRequestDelBody> getRequestDelFriends(@Body FriendsRequestDelRequest request);

    @POST("json")
    Observable<FriendsRequestCancelBody> getRequestCancelFriends(@Body FriendsRequestCancelRequest request);

    @POST("json")
    Observable<FriendsCreateBody> createRequest(@Body FriendsCreateRequest request);

}
