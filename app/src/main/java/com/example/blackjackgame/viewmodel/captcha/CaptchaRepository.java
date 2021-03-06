package com.example.blackjackgame.viewmodel.captcha;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.statics.CaptchaBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.stattics.CaptchaRequest;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CaptchaRepository {

    private static CaptchaRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;
    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt show;

    private CaptchaRepository(){
        compositeDisposable = new CompositeDisposable();
        apiService = ApiFactory.getInstance().getJSONApi();

        error = new ObservableInt(View.GONE);
        loading = new ObservableInt(View.VISIBLE);
        show = new ObservableInt(View.GONE);
    }

    public static CaptchaRepository getInstance() {
        if(instance == null){
            instance = new CaptchaRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<CaptchaBody> checkCaptcha(CaptchaRequest request){

        MutableLiveData<CaptchaBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        show.set(View.GONE);

        apiService.checkCaptcha(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CaptchaBody>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull CaptchaBody captchaBody) {
                        liveData.setValue(captchaBody);
                        error.set(View.GONE);
                        loading.set(View.GONE);
                        show.set(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        error.set(View.VISIBLE);
                        loading.set(View.GONE);
                        show.set(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }

    public ObservableInt getLoading() {
        return loading;
    }

    public ObservableInt getError() {
        return error;
    }

    public ObservableInt getShow() {
        return show;
    }
}
