package com.example.blackjackgame.viewmodel.game;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.game.GameBody;
import com.example.blackjackgame.network.responce.game.GameRequest;

public class GameViewModel extends AndroidViewModel {

    private GameRepository gameRepository;

    public GameViewModel(@NonNull Application application) {
        super(application);
        gameRepository = GameRepository.getInstance();
    }

    public MutableLiveData<GameBody> getGameMain(GameRequest request){
        return gameRepository.getGameMain(request);
    }
}
