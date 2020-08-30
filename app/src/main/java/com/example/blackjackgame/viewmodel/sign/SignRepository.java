package com.example.blackjackgame.viewmodel.sign;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.sign.NewsGuest;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.sign.SignGuestRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SignRepository {

    private static SignRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private SignRepository(){
        apiService = ApiFactory.getInstance().getJSONApi();
        compositeDisposable = new CompositeDisposable();
    }

    public static SignRepository getInstance(){
        if(instance == null){
            instance = new SignRepository();
        }
        return instance;
    }

    public MutableLiveData<NewsGuest> signGuest(){
        MutableLiveData<NewsGuest> liveData = new MutableLiveData<>();

        SignGuestRequest request = new SignGuestRequest("sign_in_guest");

        compositeDisposable.add(apiService.getData(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }
}
