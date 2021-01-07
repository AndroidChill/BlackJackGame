package com.example.blackjackgame.rViewModel.startGame;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.startGame.StartGameRequest;

public class StartGameFactory implements ViewModelProvider.Factory {

    private StartGameRequest request;

    public StartGameFactory(StartGameRequest request){
        this.request = request;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StartGameViewModel(request);
    }
}
