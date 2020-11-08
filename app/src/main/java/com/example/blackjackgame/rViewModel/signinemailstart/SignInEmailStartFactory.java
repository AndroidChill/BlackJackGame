package com.example.blackjackgame.rViewModel.signinemailstart;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.sign.SignInEmailStartRequest;


public class SignInEmailStartFactory implements ViewModelProvider.Factory {

    private SignInEmailStartRequest request;

    public SignInEmailStartFactory(SignInEmailStartRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SignInEmailStartViewModel(request);
    }

}
