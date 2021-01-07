package com.example.blackjackgame.rViewModel.startGame;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.friends.all.FriendsAllBody;
import com.example.blackjackgame.rModel.friends.all.FriendsSearchBody;
import com.example.blackjackgame.rModel.startGame.StartGameBody;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsSearchRequest;
import com.example.blackjackgame.rNetwork.request.startGame.StartGameRequest;
import com.example.blackjackgame.rViewModel.friendsAll.FriendsAllRepository;

public class StartGameViewModel extends ViewModel {

    private MutableLiveData<StartGameBody> liveData;
    private StartGameRepository repository;

    public StartGameViewModel(StartGameRequest request){
        repository = StartGameRepository.getInstance();
        liveData = repository.startGame(request);
    }

    public MutableLiveData<StartGameBody> getLiveData() {
        return liveData;
    }

    public MutableLiveData<StartGameBody> getSearch(StartGameRequest request){
        return repository.startGame(request);
    }

    public void update(StartGameRequest request){
        liveData = repository.startGame(request);
    }

    public ObservableInt getError(){
        return repository.getError();
    }

    public ObservableInt getLoading(){
        return repository.getLoading();
    }

    public ObservableInt getShowContent(){
        return repository.getShowContent();
    }

}
