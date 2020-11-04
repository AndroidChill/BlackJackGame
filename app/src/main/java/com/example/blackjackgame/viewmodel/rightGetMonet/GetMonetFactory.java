package com.example.blackjackgame.viewmodel.rightGetMonet;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.network.responce.getmonet.GetMonetRequest;

import org.jetbrains.annotations.NotNull;

public class GetMonetFactory implements ViewModelProvider.Factory {

    private GetMonetRequest request;

    public GetMonetFactory(GetMonetRequest request){
        this.request = request;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new GetMonetViewModel(request);
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