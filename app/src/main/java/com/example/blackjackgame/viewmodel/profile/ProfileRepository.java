package com.example.blackjackgame.viewmodel.profile;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.friend.getMonet.GiveMonetBody;
import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.model.profile.any.ProfileAnyBody;
import com.example.blackjackgame.model.profile.avatar.AvatarBody;
import com.example.blackjackgame.model.profile.changeData.ProfileChangeBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.friend.GiveMonetRequest;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.profile.any.ProfileAnyRequest;
import com.example.blackjackgame.network.responce.profile.avatar.AvatarChangeRequest;
import com.example.blackjackgame.network.responce.profile.change.ProfileChangeDataRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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

    public MutableLiveData<AvatarBody> getAvatarList(AvatarChangeRequest request){
        MutableLiveData<AvatarBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getAvatarList(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

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

    public MutableLiveData<ProfileBody> getProfileData(DataProfileRequest request){
        MutableLiveData<ProfileBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getProfile(request)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    public MutableLiveData<ProfileAnyBody> getProfileAny(ProfileAnyRequest request){
        MutableLiveData<ProfileAnyBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getProfileAny(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

}
