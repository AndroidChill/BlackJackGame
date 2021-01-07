package com.example.blackjackgame.rViewModel.ratingRich;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.rating.RatingRequest;
import com.example.blackjackgame.rNetwork.request.ratingRich.RatingRichRequest;
import com.example.blackjackgame.rViewModel.rating.RatingViewModel;

public class RatingRichFactory implements ViewModelProvider.Factory {

    private RatingRichRequest request;

    public RatingRichFactory(RatingRichRequest request) {
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RatingRichViewModel(request);
    }

}
