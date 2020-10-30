package com.example.blackjackgame.viewmodel.captcha;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.statics.CaptchaBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.stattics.CaptchaRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CaptchaRepository {

    private static CaptchaRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private CaptchaRepository(){
        compositeDisposable = new CompositeDisposable();
        apiService = ApiFactory.getInstance().getJSONApi();
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

        compositeDisposable.add(apiService.checkCaptcha(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }
}
