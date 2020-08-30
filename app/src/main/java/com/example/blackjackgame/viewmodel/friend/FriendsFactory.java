package com.example.blackjackgame.viewmodel.friend;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class FriendsFactory implements ViewModelProvider.Factory {

    private Application application;

    public FriendsFactory(Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FriendsViewModel(application);
    }


}
