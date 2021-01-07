package com.example.blackjackgame.rViewModel.inviteGame;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.rNetwork.request.news.NewsRequest;
import com.example.blackjackgame.rViewModel.news.NewsViewModel;

public class InviteGameFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new InviteGameViewModel();
    }

}
