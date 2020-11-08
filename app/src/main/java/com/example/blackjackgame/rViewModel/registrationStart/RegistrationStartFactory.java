package com.example.blackjackgame.rViewModel.registrationStart;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.registration.RegistrationStartRequest;

public class RegistrationStartFactory  implements ViewModelProvider.Factory {

    private RegistrationStartRequest request;

    public RegistrationStartFactory(RegistrationStartRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RegistrationStartViewModel(request);
    }
}
