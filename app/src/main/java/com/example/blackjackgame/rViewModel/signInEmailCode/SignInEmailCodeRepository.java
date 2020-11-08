package com.example.blackjackgame.rViewModel.signInEmailCode;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.sign.SignInEmailCodeBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailCodeRequest;
import com.example.blackjackgame.rNetwork.service.AuthService;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignInEmailCodeRepository {

    private static SignInEmailCodeRepository instance;
    private AuthService service;
    private CompositeDisposable compositeDisposable;

    private ObservableInt loading;
    private ObservableInt error;
    private ObservableInt showContent;

    private SignInEmailCodeRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getSignApi();

        loading = new ObservableInt(View.VISIBLE);
        error = new ObservableInt(View.GONE);
        showContent = new ObservableInt(View.GONE);
    }

    public static SignInEmailCodeRepository getInstance() {
        if(instance == null){
            instance = new SignInEmailCodeRepository();
        }
        return instance;
    }

    public MutableLiveData<SignInEmailCodeBody> getLoginEnd(SignInEmailCodeRequest request){

        MutableLiveData<SignInEmailCodeBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.loginEnd(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInEmailCodeBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull SignInEmailCodeBody signInEmailCodeBody) {
                        liveData.setValue(signInEmailCodeBody);

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
