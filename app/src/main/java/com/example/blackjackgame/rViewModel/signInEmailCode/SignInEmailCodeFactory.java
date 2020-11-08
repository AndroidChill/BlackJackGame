package com.example.blackjackgame.rViewModel.signInEmailCode;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.sign.SignInEmailCodeRequest;

public class SignInEmailCodeFactory implements ViewModelProvider.Factory {

    private SignInEmailCodeRequest request;

    public SignInEmailCodeFactory(SignInEmailCodeRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SignInEmailCodeViewModel(request);
    }
}
