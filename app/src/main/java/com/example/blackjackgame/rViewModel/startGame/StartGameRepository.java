package com.example.blackjackgame.rViewModel.startGame;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;


import com.example.blackjackgame.rModel.startGame.StartGameBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.startGame.StartGameRequest;
import com.example.blackjackgame.rNetwork.service.GameService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StartGameRepository {

    private static StartGameRepository instance;
    private CompositeDisposable compositeDisposable;
    private GameService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    private StartGameRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getGameApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static StartGameRepository getInstance() {
        if(instance == null){
            instance = new StartGameRepository();
        }
        return instance;
    }

    public MutableLiveData<StartGameBody> startGame(StartGameRequest request){
        MutableLiveData<StartGameBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.getStartGame(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StartGameBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull StartGameBody friendsAllBody) {
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
