package com.example.blackjackgame.viewmodel.game;


import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.game.GameBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.game.GameRequest;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GameRepository {

    private static GameRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private GameRepository(){
        apiService = ApiFactory.getInstance().getJSONApi();
        compositeDisposable = new CompositeDisposable();
    }

    public static GameRepository getInstance() {
        if(instance == null){
            instance = new GameRepository();
        }
        return instance;
    }

    public MutableLiveData<GameBody> getGameMain(GameRequest request){
        MutableLiveData<GameBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getGameMain(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }
}
