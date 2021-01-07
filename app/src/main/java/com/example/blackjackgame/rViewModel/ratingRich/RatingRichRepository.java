package com.example.blackjackgame.rViewModel.ratingRich;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.rating.RatingBody;
import com.example.blackjackgame.rModel.ratingRich.RatingRichBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.rating.RatingRequest;
import com.example.blackjackgame.rNetwork.request.ratingRich.RatingRichRequest;
import com.example.blackjackgame.rNetwork.service.RatingService;
import com.example.blackjackgame.rViewModel.rating.RatingRepository;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RatingRichRepository {

    private static RatingRichRepository instance;
    private CompositeDisposable compositeDisposable;
    private RatingService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    public RatingRichRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getRatingApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static RatingRichRepository getInstance() {
        if(instance == null){
            instance = new RatingRichRepository();
        }
        return instance;
    }

    public MutableLiveData<RatingRichBody> getRating(RatingRichRequest request){
        MutableLiveData<RatingRichBody> liveData = new MutableLiveData<>();

        service.getRatingRich(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RatingRichBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull RatingRichBody ratingBody) {
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
