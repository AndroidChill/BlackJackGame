package com.example.blackjackgame.viewmodel.rigthProfile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.model.profile.changeData.ProfileChangeBody;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.profile.change.ProfileChangeDataRequest;

public class RightProfileViewModel extends ViewModel {

    private RightProfileRepository repository;
    private MutableLiveData<ProfileBody> profile = new MutableLiveData<>();

    public RightProfileViewModel(DataProfileRequest request) {

        this.repository = RightProfileRepository.getInstance();
        this.profile = repository.getProfileData(request);
    }

    //получение информации профиля
    public MutableLiveData<ProfileBody> getProfile() {
        return profile;
    }

    //изменение данных пользователя
    public MutableLiveData<ProfileChangeBody> changeData(ProfileChangeDataRequest request){
        return repository.changeData(request);
    }

    public void swipeRefresh(DataProfileRequest request){
        profile = repository.getProfileData(request);
    }

    public ObservableInt getLoading(){
        return repository.getLoading();
    }

    public ObservableInt getError(){
        return repository.getError();
    }

    public ObservableInt getIsShowContent(){
        return repository.getIsShowContent();
    }

}
