package com.example.blackjackgame.rViewModel.signinemail;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.rModel.sign.SignInEmailBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailRequest;
import com.example.blackjackgame.rNetwork.service.AuthService;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignInEmailRepository {

    private static SignInEmailRepository instance;
    private AuthService service;
    private CompositeDisposable compositeDisposable;

    private ObservableInt loading;
    private ObservableInt error;
    private ObservableInt showContent;

    private SignInEmailRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getSignApi();

        loading = new ObservableInt(View.VISIBLE);
        error = new ObservableInt(View.GONE);
        showContent = new ObservableInt(View.GONE);
    }

    public static SignInEmailRepository getInstance() {
        if(instance == null){
            instance = new SignInEmailRepository();
        }
        return instance;
    }

    public MutableLiveData<SignInEmailBody> getLiveData(SignInEmailRequest request){
        MutableLiveData<SignInEmailBody> liveData = new MutableLiveData<>();

        loading.set(View.VISIBLE);
        error.set(View.GONE);
        showContent.set(View.GONE);

        service.signEmail(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInEmailBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull SignInEmailBody signInEmailBody) {
                        liveData.setValue(signInEmailBody);
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

    public ObservableInt getError(){
        return error;
    }

    public ObservableInt getLoading() {
        return loading;
    }

    public ObservableInt getShowContent() {
        return showContent;
    }
}
