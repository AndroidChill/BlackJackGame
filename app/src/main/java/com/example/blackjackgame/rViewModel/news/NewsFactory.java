package com.example.blackjackgame.rViewModel.news;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.coinsGet.CoinsGetRequest;
import com.example.blackjackgame.rNetwork.request.news.NewsRequest;
import com.example.blackjackgame.rViewModel.getMonet.GetMonetViewModel;

public class NewsFactory implements ViewModelProvider.Factory {

    private NewsRequest request;

    public NewsFactory(NewsRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NewsViewModel(request);
    }

}
