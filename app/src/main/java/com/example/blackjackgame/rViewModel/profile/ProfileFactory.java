package com.example.blackjackgame.rViewModel.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.profile.ProfileRequest;

public class ProfileFactory implements ViewModelProvider.Factory {

    private ProfileRequest request;

    public ProfileFactory(ProfileRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProfileViewModel(request);
    }
}
