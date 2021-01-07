package com.example.blackjackgame.rViewModel.coinsHistory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.coinsHistory.CoinsHistoryRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rViewModel.friendsAll.FriendsAllViewModel;

public class CoinsHistoryFactory implements ViewModelProvider.Factory {

    private CoinsHistoryRequest request;

    public CoinsHistoryFactory(CoinsHistoryRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CoinsHistoryViewModel(request);
    }

}
