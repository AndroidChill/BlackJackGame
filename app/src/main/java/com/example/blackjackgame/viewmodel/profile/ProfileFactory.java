package com.example.blackjackgame.viewmodel.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.viewmodel.sign.SignViewModel;

public class ProfileFactory implements ViewModelProvider.Factory{

    private Application application;

    public ProfileFactory(Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProfileViewModel(application);
    }

}
