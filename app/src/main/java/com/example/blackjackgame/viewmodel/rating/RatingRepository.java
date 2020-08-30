package com.example.blackjackgame.viewmodel.rating;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.rating.RatingCustom;
import com.example.blackjackgame.model.rating.ratingLuck.RatingLuckBody;
import com.example.blackjackgame.model.rating.ratingRich.RatingRichBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.rating.RatingCoinsRequest;
import com.example.blackjackgame.network.responce.rating.RatingRequest;
import com.example.blackjackgame.network.responce.rating.RatingRichRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RatingRepository {

    private static RatingRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private RatingRepository(){
        apiService = ApiFactory.getInstance().getJSONApi();
        compositeDisposable = new CompositeDisposable();
    }

    public static RatingRepository getInstance() {
        if(instance == null){
            instance = new RatingRepository();
        }
        return instance;
    }

    public MutableLiveData<RatingRichBody> getRatingRich(RatingRichRequest request){
        MutableLiveData<RatingRichBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getRating(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    public MutableLiveData<RatingLuckBody> getRatingLuck(RatingCoinsRequest request){
        MutableLiveData<RatingLuckBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getRating(request)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    public MutableLiveData<RatingCustom> getRating(RatingRequest request){
        MutableLiveData<RatingCustom> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getRating(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }
}
