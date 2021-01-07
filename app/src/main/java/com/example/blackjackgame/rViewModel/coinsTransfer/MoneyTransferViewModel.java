package com.example.blackjackgame.rViewModel.coinsTransfer;

import android.content.Context;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.historyCoins.HistoryCoinsBody;
import com.example.blackjackgame.rModel.moneyTransfer.MoneyTransferBody;
import com.example.blackjackgame.rNetwork.request.coinsHistory.CoinsHistoryRequest;
import com.example.blackjackgame.rNetwork.request.moneyTransfer.MoneyTransferRequest;
import com.example.blackjackgame.rViewModel.coinsHistory.CoinsHistoryRepository;

public class MoneyTransferViewModel extends ViewModel {

    private MutableLiveData<MoneyTransferBody> liveData;
    private MoneyTransferRepository repository;

    public MoneyTransferViewModel(){
        repository = MoneyTransferRepository.getInstance();
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<MoneyTransferBody> getLiveData(Context context, MoneyTransferRequest request) {
        return repository.getHistory(context, request);
    }



}
