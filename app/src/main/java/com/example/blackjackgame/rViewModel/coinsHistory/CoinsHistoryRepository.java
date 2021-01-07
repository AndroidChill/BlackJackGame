package com.example.blackjackgame.rViewModel.coinsHistory;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.friends.all.FriendsAllBody;
import com.example.blackjackgame.rModel.friends.all.FriendsSearchBody;
import com.example.blackjackgame.rModel.historyCoins.HistoryCoinsBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.coinsHistory.CoinsHistoryRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsSearchRequest;
import com.example.blackjackgame.rNetwork.service.CoinsGetService;
import com.example.blackjackgame.rNetwork.service.FriendsService;
import com.example.blackjackgame.rViewModel.friendsAll.FriendsAllRepository;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CoinsHistoryRepository {

    private static CoinsHistoryRepository instance;
    private CompositeDisposable compositeDisposable;
    private CoinsGetService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    private CoinsHistoryRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getCoinsGetApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static CoinsHistoryRepository getInstance() {
        if(instance == null){
            instance = new CoinsHistoryRepository();
        }
        return instance;
    }

    public MutableLiveData<HistoryCoinsBody> getHistory(CoinsHistoryRequest request){
        MutableLiveData<HistoryCoinsBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.getHistoryCoins(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryCoinsBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HistoryCoinsBody friendsAllBody) {
                        liveData.setValue(friendsAllBody);

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



    public ObservableInt getShowContent() {
        return showContent;
    }

    public ObservableInt getLoading() {
        return loading;
    }

    public ObservableInt getError() {
        return error;
    }

}
