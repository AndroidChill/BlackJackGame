package com.example.blackjackgame.rViewModel.friendsRequest;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestRequest;

public class FriendsRequestFactory implements ViewModelProvider.Factory {

    private FriendsRequestRequest request;

    public FriendsRequestFactory(FriendsRequestRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FriendsRequestViewModel(request);
    }
}
