package com.example.blackjackgame.rViewModel.friendsReferrals;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.friends.referals.FriendsReferralsRequest;

public class FriendsReferralsFactory implements ViewModelProvider.Factory {

    private FriendsReferralsRequest request;

    public FriendsReferralsFactory(FriendsReferralsRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FriendsReferralsViewModel(request);
    }
}
