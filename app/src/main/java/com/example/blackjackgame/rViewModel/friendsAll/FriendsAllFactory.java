package com.example.blackjackgame.rViewModel.friendsAll;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;

public class FriendsAllFactory implements ViewModelProvider.Factory {

    private FriendsAllRequest request;

    public FriendsAllFactory(FriendsAllRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FriendsAllViewModel(request);
    }
}
