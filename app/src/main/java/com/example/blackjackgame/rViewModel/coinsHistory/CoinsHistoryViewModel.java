package com.example.blackjackgame.rViewModel.coinsHistory;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.friends.all.FriendsAllBody;
import com.example.blackjackgame.rModel.friends.all.FriendsSearchBody;
import com.example.blackjackgame.rModel.historyCoins.HistoryCoinsBody;
import com.example.blackjackgame.rNetwork.request.coinsHistory.CoinsHistoryRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsSearchRequest;
import com.example.blackjackgame.rViewModel.friendsAll.FriendsAllRepository;

public class CoinsHistoryViewModel extends ViewModel {

    private MutableLiveData<HistoryCoinsBody> liveData;
    private CoinsHistoryRepository repository;

    public CoinsHistoryViewModel(CoinsHistoryRequest request){
        repository = CoinsHistoryRepository.getInstance();
        liveData = repository.getHistory(request);
    }

    public MutableLiveData<HistoryCoinsBody> getLiveData() {
        return liveData;
    }


    public void update(CoinsHistoryRequest request){
        liveData = repository.getHistory(request);
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
