package com.example.blackjackgame.rViewModel.news;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.coinsGet.CoinsGetBody;
import com.example.blackjackgame.rModel.news.NewsBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.coinsGet.CoinsGetRequest;
import com.example.blackjackgame.rNetwork.request.news.NewsRequest;
import com.example.blackjackgame.rNetwork.service.CoinsGetService;
import com.example.blackjackgame.rNetwork.service.NewsService;
import com.example.blackjackgame.rViewModel.getMonet.GetMonetRepository;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsRepository {

    private static NewsRepository instance;
    private CompositeDisposable compositeDisposable;
    private NewsService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    private NewsRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getNewsApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static NewsRepository getInstance() {
        if(instance == null){
            instance = new NewsRepository();
        }
        return instance;
    }

    public MutableLiveData<NewsBody> getNewsList(NewsRequest request){
        MutableLiveData<NewsBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.getNewsList(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsBody coinsGetBody) {
                        liveData.setValue(coinsGetBody);

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


    public ObservableInt getError() {
        return error;
    }

    public ObservableInt getLoading() {
        return loading;
    }

    public ObservableInt getShowContent() {
        return showContent;
    }

}
