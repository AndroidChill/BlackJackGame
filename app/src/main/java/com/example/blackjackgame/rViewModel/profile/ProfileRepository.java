package com.example.blackjackgame.rViewModel.profile;

import android.opengl.Visibility;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.profile.ProfileBody;
import com.example.blackjackgame.rModel.profileAvatar.ProfileAvatarBody;
import com.example.blackjackgame.rModel.profileSend.ProfileSendBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.profile.ProfileRequest;
import com.example.blackjackgame.rNetwork.request.profileAvatar.ProfileAvatarRequest;
import com.example.blackjackgame.rNetwork.request.profileSend.ProfileSendRequest;
import com.example.blackjackgame.rNetwork.service.ProfileService;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileRepository {

    private static ProfileRepository instance;
    private CompositeDisposable compositeDisposable;
    private ProfileService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    private ObservableInt errorSend;



    private ProfileRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getProfileApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
        errorSend = new ObservableInt(View.GONE);
    }

    public static ProfileRepository getInstance() {
        if(instance == null){
            instance = new ProfileRepository();
        }
        return instance;
    }

    public MutableLiveData<ProfileBody> getProfile(ProfileRequest request){
        MutableLiveData<ProfileBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.getProfile(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfileBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull ProfileBody profileBody) {
                        liveData.setValue(profileBody);

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

    public MutableLiveData<ProfileAvatarBody> getProfileAvatar(ProfileAvatarRequest request){
        MutableLiveData<ProfileAvatarBody> liveData = new MutableLiveData<>();

        service.getProfileAvatar(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfileAvatarBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ProfileAvatarBody profileAvatarBody) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }

    public MutableLiveData<ProfileSendBody> sendProfile(ProfileSendRequest request){
        MutableLiveData<ProfileSendBody> liveData = new MutableLiveData<>();

        errorSend.set(View.GONE);

        service.sendProfile(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfileSendBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ProfileSendBody profileSendBody) {
                        liveData.setValue(profileSendBody);
                        errorSend.set(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorSend.set(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }

    public ObservableInt getErrorSend() {
        return errorSend;
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
