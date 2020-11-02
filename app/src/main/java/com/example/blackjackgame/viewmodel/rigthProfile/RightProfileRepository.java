package com.example.blackjackgame.viewmodel.rigthProfile;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.core.util.Pair;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.model.profile.avatar.AvatarBody;
import com.example.blackjackgame.model.profile.changeData.ProfileChangeBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.profile.avatar.AvatarChangeRequest;
import com.example.blackjackgame.network.responce.profile.change.ProfileChangeDataRequest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RightProfileRepository {

    private static RightProfileRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private ObservableInt loading;
    private ObservableInt error;
    private ObservableInt isShowContent;

    private RightProfileRepository(){

        loading = new ObservableInt();
        error = new ObservableInt();
        isShowContent = new ObservableInt();
        loading.set(View.VISIBLE);
        error.set(View.GONE);
        isShowContent.set(View.GONE);

        compositeDisposable = new CompositeDisposable();
        apiService = ApiFactory.getInstance().getJSONApi();
    }

    public static RightProfileRepository getInstance() {
        if(instance == null){
            instance = new RightProfileRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<ProfileBody> getProfileData(DataProfileRequest request){
        MutableLiveData<ProfileBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        isShowContent.set(View.GONE);
        loading.set(View.VISIBLE);

        apiService.getProfile(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profileBodyResponse -> {

                    liveData.setValue(profileBodyResponse.body());

                    loading.set(View.GONE);
                    error.set(View.GONE);
                    isShowContent.set(View.VISIBLE);

                }, throwable -> {

                    loading.set(View.GONE);
                    error.set(View.VISIBLE);
                    isShowContent.set(View.GONE);

                    throwable.printStackTrace();
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

    public MutableLiveData<AvatarBody> getAvatarList(AvatarChangeRequest request){
        MutableLiveData<AvatarBody> liveData = new MutableLiveData<>();

        apiService.getAvatarList(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace);

        return liveData;
    }

    public ObservableInt getError() {
        return error;
    }

    public ObservableInt getLoading() {
        return loading;
    }

    public ObservableInt getIsShowContent() {
        return isShowContent;
    }
}
