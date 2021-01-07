package com.example.blackjackgame.rViewModel.profileAny;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;

public class ProfileAnyFactory implements ViewModelProvider.Factory {

    private ProfileAnyRequest request;

    public ProfileAnyFactory(ProfileAnyRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProfileAnyViewModel(request);
    }
}
