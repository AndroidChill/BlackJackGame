package com.example.blackjackgame.rViewModel.ratingLucky;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.rating.RatingRequest;
import com.example.blackjackgame.rNetwork.request.ratingLucky.RatingLuckyRequest;
import com.example.blackjackgame.rViewModel.rating.RatingViewModel;

public class RatingLuckyFactory implements ViewModelProvider.Factory {

    private RatingLuckyRequest request;

    public RatingLuckyFactory(RatingLuckyRequest request) {
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RatingLuckyViewModel(request);
    }

}
