package com.example.blackjackgame.viewmodel.friend;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.friend.FriendBody;
import com.example.blackjackgame.model.friend.Global.FriendGlobalBody;
import com.example.blackjackgame.model.friend.referrals.ReferralsBody;
import com.example.blackjackgame.model.friend.request.add.FriendsAddBody;
import com.example.blackjackgame.model.friend.request.del.FriendsDelBody;
import com.example.blackjackgame.model.friend.request.output.FriendsZaprosBody;
import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.network.responce.friend.FriendReferalsRequest;
import com.example.blackjackgame.network.responce.friend.FriendRequest;
import com.example.blackjackgame.network.responce.friend.FriendSearchRequest;
import com.example.blackjackgame.network.responce.friend.FriendsZaprosRequest;
import com.example.blackjackgame.network.responce.friend.request.FriendsAddRequest;
import com.example.blackjackgame.network.responce.friend.request.FriendsDelRequest;
import com.example.blackjackgame.viewmodel.profile.ProfileRepository;

import io.reactivex.annotations.NonNull;

public class FriendsViewModel extends AndroidViewModel {

    private FriendsRepository friendsRepository;

    public FriendsViewModel(@NonNull Application application){
        super(application);

        friendsRepository = FriendsRepository.getInstance();
    }

    public MutableLiveData<FriendBody> getFriends(FriendRequest request){
        return friendsRepository.getFriends(request);
    }

    public MutableLiveData<FriendGlobalBody> getGlobalFriends(FriendSearchRequest request){
        return friendsRepository.getFriends(request);
    }

    public MutableLiveData<ReferralsBody> getReferalsFriends(FriendReferalsRequest request){
        return friendsRepository.getFriends(request);
    }

    public MutableLiveData<FriendsZaprosBody> getRequestFriends(FriendsZaprosRequest request){
        return friendsRepository.getRequest(request);
    }

    public MutableLiveData<FriendsAddBody> addFriend(FriendsAddRequest request){
        return friendsRepository.addFriend(request);
    }

    public MutableLiveData<FriendsDelBody> delFriend(FriendsDelRequest request){
        return friendsRepository.delFriend(request);
    }


}
