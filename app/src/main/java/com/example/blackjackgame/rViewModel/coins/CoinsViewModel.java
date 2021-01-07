package com.example.blackjackgame.rViewModel.coins;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.coins.CoinsBody;
import com.example.blackjackgame.rNetwork.request.coins.CoinsRequest;

public class CoinsViewModel extends ViewModel {

    private CoinsRepository repository;

    public CoinsViewModel(){
        repository = CoinsRepository.getInstance();
    }

    public MutableLiveData<CoinsBody> getCoinsList(Context context, CoinsRequest request){
        return repository.getCoins(context, request);
    }

}
