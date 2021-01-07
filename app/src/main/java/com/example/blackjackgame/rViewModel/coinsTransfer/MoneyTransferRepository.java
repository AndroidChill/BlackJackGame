package com.example.blackjackgame.rViewModel.coinsTransfer;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.historyCoins.HistoryCoinsBody;
import com.example.blackjackgame.rModel.moneyTransfer.MoneyTransferBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.coinsHistory.CoinsHistoryRequest;
import com.example.blackjackgame.rNetwork.request.moneyTransfer.MoneyTransferRequest;
import com.example.blackjackgame.rNetwork.service.CoinsGetService;
import com.example.blackjackgame.rNetwork.service.MoneyTransferService;
import com.example.blackjackgame.rViewModel.coinsHistory.CoinsHistoryRepository;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoneyTransferRepository {

    private static MoneyTransferRepository instance;
    private CompositeDisposable compositeDisposable;
    private MoneyTransferService service;

    private MoneyTransferRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getMoneyTransferApi();
    }

    public static MoneyTransferRepository getInstance() {
        if(instance == null){
            instance = new MoneyTransferRepository();
        }
        return instance;
    }

    public MutableLiveData<MoneyTransferBody> getHistory(Context context, MoneyTransferRequest request){
        MutableLiveData<MoneyTransferBody> liveData = new MutableLiveData<>();

        service.getMoneyTransfer(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoneyTransferBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MoneyTransferBody friendsAllBody) {
                        liveData.setValue(friendsAllBody);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Toast.makeText(context, "Ошибка сервера. Попробуйте еще раз", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }


}
