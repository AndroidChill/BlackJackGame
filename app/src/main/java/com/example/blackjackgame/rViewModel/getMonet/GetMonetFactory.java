package com.example.blackjackgame.rViewModel.getMonet;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.coinsGet.CoinsGetRequest;

public class GetMonetFactory implements ViewModelProvider.Factory {

    private CoinsGetRequest request;

    public GetMonetFactory(CoinsGetRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GetMonetViewModel(request);
    }
}
