package com.example.blackjackgame.rViewModel.signinemailstart;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.sign.SignInEmailStartBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailStartRequest;
import com.example.blackjackgame.rNetwork.service.AuthService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignInEmailStartRepository {

    private static SignInEmailStartRepository instance;
    private AuthService service;
    private CompositeDisposable compositeDisposable;

    private ObservableInt loading;
    private ObservableInt error;
    private ObservableInt showContent;

    private SignInEmailStartRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getSignApi();

        loading = new ObservableInt(View.VISIBLE);
        error = new ObservableInt(View.GONE);
        showContent = new ObservableInt(View.GONE);
    }

    public static SignInEmailStartRepository getInstance() {
        if(instance == null){
            instance = new SignInEmailStartRepository();
        }
        return instance;
    }

    public MutableLiveData<SignInEmailStartBody> signInEmailStartBodyMutableLiveData(SignInEmailStartRequest request){

        MutableLiveData<SignInEmailStartBody> liveData = new MutableLiveData<>();

        service.startSignEmail(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInEmailStartBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull SignInEmailStartBody signInEmailStartBody) {
                        liveData.setValue(signInEmailStartBody);
                        loading.set(View.GONE);
                        error.set(View.GONE);
                        showContent.set(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loading.set(View.GONE);
                        error.set(View.VISIBLE);
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
