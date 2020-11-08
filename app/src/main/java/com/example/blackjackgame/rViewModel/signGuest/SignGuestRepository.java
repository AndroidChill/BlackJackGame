package com.example.blackjackgame.rViewModel.signGuest;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.network.responce.sign.SignGuestRequest;
import com.example.blackjackgame.rModel.sign.SignGuestBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.sign.SignInGuestRequest;
import com.example.blackjackgame.rNetwork.service.AuthService;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignGuestRepository {

    private static SignGuestRepository instance;
    private CompositeDisposable compositeDisposable;
    private AuthService service;

    private SignGuestRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getSignApi();

    }

    public static SignGuestRepository getInstance() {
        if(instance == null){
            instance = new SignGuestRepository();
        }
        return instance;
    }

    public MutableLiveData<SignGuestBody> signGuest(SignInGuestRequest request){
        MutableLiveData<SignGuestBody> liveData = new MutableLiveData<>();

        service.signGuest(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignGuestBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull SignGuestBody signGuestBody) {
                        liveData.setValue(signGuestBody);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("tag", "onError: ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }
}
