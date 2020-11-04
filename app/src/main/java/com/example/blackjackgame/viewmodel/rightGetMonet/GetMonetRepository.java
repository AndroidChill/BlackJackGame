package com.example.blackjackgame.viewmodel.rightGetMonet;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.getmonet.GetMonetBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.getmonet.GetMonetRequest;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetMonetRepository {

    private static GetMonetRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    private GetMonetRepository(){
        compositeDisposable = new CompositeDisposable();
        apiService = ApiFactory.getInstance().getJSONApi();

        error = new ObservableInt(View.GONE);
        loading = new ObservableInt(View.VISIBLE);
        showContent = new ObservableInt(View.GONE);
    }

    public static GetMonetRepository getInstance(){
        if(instance == null){
            instance = new GetMonetRepository();
        }
        return instance;
    }

    //получение монет
    public MutableLiveData<GetMonetBody> getTasks(GetMonetRequest request){
        MutableLiveData<GetMonetBody> liveData = new MutableLiveData<>();

        error = new ObservableInt(View.GONE);
        loading = new ObservableInt(View.VISIBLE);
        showContent = new ObservableInt(View.GONE);

        apiService.getTasks(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetMonetBody>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull GetMonetBody getMonetBody) {
                        liveData.setValue(getMonetBody);
                        error = new ObservableInt(View.GONE);
                        loading = new ObservableInt(View.GONE);
                        showContent = new ObservableInt(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        error = new ObservableInt(View.VISIBLE);
                        loading = new ObservableInt(View.GONE);
                        showContent = new ObservableInt(View.GONE);
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
