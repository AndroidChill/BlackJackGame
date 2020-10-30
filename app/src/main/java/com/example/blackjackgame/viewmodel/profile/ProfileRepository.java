package com.example.blackjackgame.viewmodel.profile;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.core.util.Pair;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.friend.getMonet.GiveMonetBody;
import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.model.profile.any.ProfileAnyBody;
import com.example.blackjackgame.model.profile.avatar.AvatarBody;
import com.example.blackjackgame.model.profile.changeData.ProfileChangeBody;
import com.example.blackjackgame.network.responce.stattics.CaptchaRequest;
import com.example.blackjackgame.model.statics.CaptchaBody;
import com.example.blackjackgame.model.statics.ReviewBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.friend.GiveMonetRequest;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.profile.any.ProfileAnyRequest;
import com.example.blackjackgame.network.responce.profile.avatar.AvatarChangeRequest;
import com.example.blackjackgame.network.responce.profile.change.ProfileChangeDataRequest;
import com.example.blackjackgame.network.responce.stattics.ReviewRequest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileRepository {

    private static ProfileRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private ProfileRepository(){
        apiService = ApiFactory.getInstance().getJSONApi();
        compositeDisposable = new CompositeDisposable();
    }

    public static ProfileRepository getInstance(){
        if(instance == null){
            instance = new ProfileRepository();
        }
        return instance;
    }

    //получение монет
    public MutableLiveData<GiveMonetBody> giveMonet(GiveMonetRequest request){
        MutableLiveData<GiveMonetBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.giveMonet(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    public MutableLiveData<Pair<String, CaptchaBody>> checkCaptcha(CaptchaRequest request){



        MutableLiveData<Pair<String, CaptchaBody>> liveData = new MutableLiveData<>();

        apiService.checkCaptcha(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CaptchaBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CaptchaBody captchaBody) {
                        liveData.setValue(new Pair<String, CaptchaBody>("success", captchaBody));
                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.setValue(new Pair<String, CaptchaBody>("error", null));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }

    public MutableLiveData<Pair<String, ReviewBody>> checkReview(ReviewRequest request){
        MutableLiveData<Pair<String, ReviewBody>> liveData = new MutableLiveData<>();

        apiService.checkReview(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReviewBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReviewBody reviewBody) {
                        liveData.setValue(new Pair<String, ReviewBody>("success", reviewBody));
                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.setValue(new Pair<String, ReviewBody>("error", null));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }

    public MutableLiveData<Pair<String, AvatarBody>> getAvatarList(AvatarChangeRequest request){
        MutableLiveData<Pair<String, AvatarBody>> liveData = new MutableLiveData<>();

        apiService.getAvatarList(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AvatarBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AvatarBody avatarBody) {
                        liveData.setValue(new Pair<String, AvatarBody>("success", avatarBody));
                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.setValue(new Pair<String, AvatarBody>("error", null));
                        Log.i("tag", "onError: косяк");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }

    public MutableLiveData<ProfileChangeBody> changeData(ProfileChangeDataRequest request){
        MutableLiveData<ProfileChangeBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.changeDataProfile(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<ProfileBody> getProfileData(ImageView view, DataProfileRequest request){
        MutableLiveData<ProfileBody> liveData = new MutableLiveData<>();

        apiService.getProfile(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(profileBodyResponse -> {
                liveData.setValue(profileBodyResponse.body());
                view.setVisibility(View.GONE);
            }, throwable -> {
                view.setVisibility(View.VISIBLE);
            });
        return liveData;
    }

//    public MutableLiveData<WeatherBody> getInfoWeather(String city) {
//        MutableLiveData<WeatherBody> liveData = new MutableLiveData<>();
//
//        loading.set(View.VISIBLE);
//        content.set(View.GONE);
//        error.set(View.GONE);
//        isLoading.set(true);
//
//        compositeDisposable.add(apiWeatherService.getApiWeather(city, Constant.UNITS_METRIC, Constant.LANG)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(weatherBody -> {
//                    liveData.setValue(weatherBody);
//                    content.set(View.VISIBLE);
//                    loading.set(View.GONE);
//                    isLoading.set(false);
//
//                }, throwable -> {
//                    error.set(View.VISIBLE);
//                    content.set(View.GONE);
//                    loading.set(View.GONE);
//                    isLoading.set(false);
//
//                    throwable.printStackTrace();
//                }));
//
//        return liveData;
//    }

    public MutableLiveData<ProfileAnyBody> getProfileAny(ProfileAnyRequest request){
        MutableLiveData<ProfileAnyBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getProfileAny(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, this::handleError));

        return liveData;
    }

    private void handleError(Throwable throwable) {

        Log.d("tag", "handleError: ");

    }


}
