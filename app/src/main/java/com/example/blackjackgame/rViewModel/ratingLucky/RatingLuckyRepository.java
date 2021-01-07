package com.example.blackjackgame.rViewModel.ratingLucky;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.rating.RatingBody;
import com.example.blackjackgame.rModel.ratingLucky.RatingLuckyBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.rating.RatingRequest;
import com.example.blackjackgame.rNetwork.request.ratingLucky.RatingLuckyRequest;
import com.example.blackjackgame.rNetwork.service.RatingService;
import com.example.blackjackgame.rViewModel.rating.RatingRepository;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RatingLuckyRepository {

    private static RatingLuckyRepository instance;
    private CompositeDisposable compositeDisposable;
    private RatingService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    public RatingLuckyRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getRatingApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static RatingLuckyRepository getInstance() {
        if(instance == null){
            instance = new RatingLuckyRepository();
        }
        return instance;
    }

    public MutableLiveData<RatingLuckyBody> getRating(RatingLuckyRequest request){
        MutableLiveData<RatingLuckyBody> liveData = new MutableLiveData<>();

        service.getRatingLucky(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RatingLuckyBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull RatingLuckyBody ratingBody) {
                        liveData.setValue(ratingBody);

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
