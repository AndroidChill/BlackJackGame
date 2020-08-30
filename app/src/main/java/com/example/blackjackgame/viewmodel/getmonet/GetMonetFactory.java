package com.example.blackjackgame.viewmodel.getmonet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class GetMonetFactory implements ViewModelProvider.Factory{

    private Application application;

    public GetMonetFactory(Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GetMonetViewModel(application);
    }

}
