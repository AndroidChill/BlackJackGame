package com.example.blackjackgame.rViewModel.getMonet;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.coinsGet.CoinsGetBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.coinsGet.CoinsGetRequest;
import com.example.blackjackgame.rNetwork.service.CoinsGetService;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetMonetRepository {

    private static GetMonetRepository instance;
    private CompositeDisposable compositeDisposable;
    private CoinsGetService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    private GetMonetRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getCoinsGetApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static GetMonetRepository getInstance() {
        if(instance == null){
            instance = new GetMonetRepository();
        }
        return instance;
    }

    public MutableLiveData<CoinsGetBody> getCoinsList(CoinsGetRequest request){
        MutableLiveData<CoinsGetBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.getListCoins(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CoinsGetBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CoinsGetBody coinsGetBody) {
                        liveData.setValue(coinsGetBody);

                        error.set(View.GONE);
                        loading.set(View.GONE);
                        showContent.set(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        error.set(View.VISIBLE);
                        loading.set(View.GONE);
                        showContent.set(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }
    

    public ObservableInt getError() {
        return error;
    }

    public ObservableInt getLoading() {
        return loading;
    }

    public ObservableInt getShowContent() {
        return showContent;
    }
}
