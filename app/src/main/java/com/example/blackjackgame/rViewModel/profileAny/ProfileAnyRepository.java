package com.example.blackjackgame.rViewModel.profileAny;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.profileAny.ProfileAnyBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;
import com.example.blackjackgame.rNetwork.service.ProfileService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileAnyRepository {

    private static ProfileAnyRepository instance;
    private CompositeDisposable compositeDisposable;
    private ProfileService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    private ProfileAnyRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getProfileApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static ProfileAnyRepository getInstance() {
        if(instance == null){
            instance = new ProfileAnyRepository();
        }
        return instance;
    }

    public MutableLiveData<ProfileAnyBody> getProfileAny(ProfileAnyRequest request){
        MutableLiveData<ProfileAnyBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.getProfileAny(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfileAnyBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull ProfileAnyBody profileAnyBody) {
                        liveData.setValue(profileAnyBody);

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
