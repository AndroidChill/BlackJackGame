package com.example.blackjackgame.viewmodel.review;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.statics.ReviewBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.stattics.ReviewRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ReviewRepository {

    private CompositeDisposable compositeDisposable;
    private ApiService apiService;
    private static ReviewRepository instance;

    private ReviewRepository(){
        compositeDisposable = new CompositeDisposable();
        apiService = ApiFactory.getInstance().getJSONApi();
    }

    public static ReviewRepository getInstance() {
        if(instance == null){
            instance = new ReviewRepository();
        }
        return instance;
    }

    public MutableLiveData<ReviewBody> getReview(ReviewRequest request){

        MutableLiveData<ReviewBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(
                apiService.checkReview(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviewBody -> {

                    liveData.setValue(reviewBody);

                },
                        throwable -> {
                    throwable.printStackTrace();
                        })
        );

        return liveData;
    }
}
