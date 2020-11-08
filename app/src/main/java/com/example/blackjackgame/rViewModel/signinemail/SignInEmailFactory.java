package com.example.blackjackgame.rViewModel.signinemail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.sign.SignInEmailRequest;

public class SignInEmailFactory implements ViewModelProvider.Factory {

    private SignInEmailRequest request;

    public SignInEmailFactory(SignInEmailRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SignInEmailViewModel(request);
    }
}
