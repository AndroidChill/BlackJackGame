package com.example.blackjackgame.rViewModel.registrationStart;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.registration.RegistrationBody;
import com.example.blackjackgame.rModel.registration.RegistrationStartBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.registration.RegistrationRequest;
import com.example.blackjackgame.rNetwork.request.registration.RegistrationStartRequest;
import com.example.blackjackgame.rNetwork.service.AuthService;
import com.example.blackjackgame.util.ObservableString;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistrationStartRepository {

    private static RegistrationStartRepository instance;
    private CompositeDisposable compositeDisposable;
    private AuthService service;

    private ObservableInt loading;
    private ObservableInt error;
    private ObservableInt showContent;

    private RegistrationStartRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getSignApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static RegistrationStartRepository getInstance(){
        if(instance == null){
            instance = new RegistrationStartRepository();
        }
        return instance;
    }

    public MutableLiveData<RegistrationStartBody> registrationStart(RegistrationStartRequest request){
        MutableLiveData<RegistrationStartBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.registrationStart(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistrationStartBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull RegistrationStartBody registrationStartBody) {
                        liveData.setValue(registrationStartBody);

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

    public void clearComposite(){
        compositeDisposable.clear();
    }

    public MutableLiveData<RegistrationBody> send(RegistrationRequest request){
        MutableLiveData<RegistrationBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);

        service.registration(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistrationBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegistrationBody registrationBody) {
                        liveData.setValue(registrationBody);
                        showContent.set(View.VISIBLE);
                        error.set(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showContent.set(View.GONE);
                        error.set(View.VISIBLE);
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
