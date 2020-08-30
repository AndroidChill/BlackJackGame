package com.example.blackjackgame.viewmodel.news;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.friend.referrals.ReferralsBody;
import com.example.blackjackgame.model.news.NewsBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.friend.FriendReferalsRequest;
import com.example.blackjackgame.network.responce.news.NewsRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewsRepository {

    private static NewsRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private NewsRepository(){
        apiService = ApiFactory.getInstance().getJSONApi();
        compositeDisposable = new CompositeDisposable();
    }

    public static NewsRepository getInstance(){
        if(instance == null){
            instance = new NewsRepository();
        }
        return instance;
    }

    public MutableLiveData<NewsBody> getNews(NewsRequest request){
        MutableLiveData<NewsBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getNews(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

}
