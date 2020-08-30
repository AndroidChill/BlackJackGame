package com.example.blackjackgame.viewmodel.rating;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;

public class RatingFactory implements ViewModelProvider.Factory{

    private Application application;

    public RatingFactory(Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RatingViewModel(application);
    }

}
