package com.example.blackjackgame.rViewModel.coins;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.coins.CoinsBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.coins.CoinsRequest;
import com.example.blackjackgame.rNetwork.service.CoinsService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CoinsRepository {

    private static CoinsRepository instance;
    private CompositeDisposable compositeDisposable;
    private CoinsService service;

    private CoinsRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getCoinsApi();
    }

    public static CoinsRepository getInstance() {
        if(instance == null){
            instance = new CoinsRepository();
        }
        return instance;
    }

    public MutableLiveData<CoinsBody> getCoins(Context context, CoinsRequest request){
        MutableLiveData<CoinsBody> liveData = new MutableLiveData<>();

        service.getListCoins(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<CoinsBody>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull CoinsBody coinsBody) {
                                liveData.setValue(coinsBody);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Toast.makeText(context, "Ошибка сервера. Попробуйте еще раз - " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

        return liveData;
    }
}
