package com.example.blackjackgame.viewmodel.rightRegistration;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.network.responce.registration.RegistrationRequest;

import org.jetbrains.annotations.NotNull;

public class RegistrationFactory implements ViewModelProvider.Factory {

    private RegistrationRequest request;

    public RegistrationFactory(RegistrationRequest request){
        this.request = request;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new RegistrationViewModel(request);
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