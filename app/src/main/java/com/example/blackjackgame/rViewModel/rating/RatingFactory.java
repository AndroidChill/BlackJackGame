package com.example.blackjackgame.rViewModel.rating;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.rating.RatingRequest;

public class RatingFactory implements ViewModelProvider.Factory {

    private RatingRequest request;

    public RatingFactory(RatingRequest request) {
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RatingViewModel(request);
    }
}
