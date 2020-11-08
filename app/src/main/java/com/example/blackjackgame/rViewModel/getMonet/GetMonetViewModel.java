package com.example.blackjackgame.rViewModel.getMonet;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.coinsGet.CoinsGetBody;
import com.example.blackjackgame.rNetwork.request.coinsGet.CoinsGetRequest;

public class GetMonetViewModel extends ViewModel {

    private GetMonetRepository repository;
    private MutableLiveData<CoinsGetBody> liveData;

    public GetMonetViewModel(CoinsGetRequest request){
        repository = GetMonetRepository.getInstance();
        liveData = repository.getCoinsList(request);
    }

    public MutableLiveData<CoinsGetBody> getLiveData() {
        return liveData;
    }

    public void update(CoinsGetRequest request){
        liveData = repository.getCoinsList(request);
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
