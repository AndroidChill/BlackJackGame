package com.example.blackjackgame.viewmodel.rigthProfile;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;

public class RightProfileFactory implements ViewModelProvider.Factory{

    private DataProfileRequest request;

    public RightProfileFactory(DataProfileRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RightProfileViewModel(request);
    }
}



//public class MainScreenFactory implements ViewModelProvider.Factory {
//
//    private String city;
//
//    public MainScreenFactory(String city) {
//        this.city = city;
//    }
//
//    @NonNull
//    @Override
//    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        return (T) new MainScreenViewModel(city);
//    }
//}