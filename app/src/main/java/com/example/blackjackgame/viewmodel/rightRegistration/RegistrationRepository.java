package com.example.blackjackgame.viewmodel.rightRegistration;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.registration.RegistrationBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.registration.RegistrationRequest;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistrationRepository {

    private static RegistrationRepository instance;
    private CompositeDisposable compositeDisposable;
    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;
    private ApiService apiService;

    private RegistrationRepository(){
        apiService = ApiFactory.getInstance().getJSONApi();
        error = new ObservableInt(View.GONE);
        loading = new ObservableInt(View.VISIBLE);
        showContent = new ObservableInt(View.GONE);
    }

    static RegistrationRepository getInstance(){
        if(instance == null){
            instance = new RegistrationRepository();
        }
        return instance;
    }

    public MutableLiveData<RegistrationBody> getRegistration(RegistrationRequest request){

        MutableLiveData<RegistrationBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        apiService.getRegistration(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistrationBody>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NotNull RegistrationBody registrationBody) {
                        liveData.setValue(registrationBody);
                        error.set(View.GONE);
                        loading.set(View.GONE);
                        showContent.set(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
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
