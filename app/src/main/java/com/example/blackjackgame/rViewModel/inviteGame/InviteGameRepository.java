package com.example.blackjackgame.rViewModel.inviteGame;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.inviteGame.InviteGameApplyBody;
import com.example.blackjackgame.rModel.inviteGame.InviteGameCancelBody;
import com.example.blackjackgame.rModel.news.NewsBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.inviteGame.InviteGameApplyRequest;
import com.example.blackjackgame.rNetwork.request.inviteGame.InviteGameCancelRequest;
import com.example.blackjackgame.rNetwork.request.news.NewsRequest;
import com.example.blackjackgame.rNetwork.service.GameService;
import com.example.blackjackgame.rNetwork.service.NewsService;
import com.example.blackjackgame.rViewModel.news.NewsRepository;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InviteGameRepository {

    private static InviteGameRepository instance;
    private CompositeDisposable compositeDisposable;
    private GameService service;

    private InviteGameRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getGameApi();
    }

    public static InviteGameRepository getInstance() {
        if(instance == null){
            instance = new InviteGameRepository();
        }
        return instance;
    }

    public MutableLiveData<InviteGameApplyBody> applyGame(Context context, InviteGameApplyRequest request){
        MutableLiveData<InviteGameApplyBody> liveData = new MutableLiveData<>();

        service.applyGame(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InviteGameApplyBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull InviteGameApplyBody coinsGetBody) {
                        liveData.setValue(coinsGetBody);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "Произошла ошибка", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }

    public MutableLiveData<InviteGameCancelBody> cancelGame(Context context, InviteGameCancelRequest request){
        MutableLiveData<InviteGameCancelBody> liveData = new MutableLiveData<>();

        service.cancelGame(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InviteGameCancelBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull InviteGameCancelBody coinsGetBody) {
                        liveData.setValue(coinsGetBody);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "Произошла ошибка", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }



}
