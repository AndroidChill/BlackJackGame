package com.example.blackjackgame.rViewModel.profile;


import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.profile.ProfileBody;
import com.example.blackjackgame.rModel.profileSend.ProfileSendBody;
import com.example.blackjackgame.rNetwork.request.profile.ProfileRequest;
import com.example.blackjackgame.rNetwork.request.profileSend.ProfileSendRequest;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<ProfileBody> liveData;
    private ProfileRepository repository;

    public ProfileViewModel(ProfileRequest request){
        repository = ProfileRepository.getInstance();
        liveData = repository.getProfile(request);
    }

    public MutableLiveData<ProfileBody> getLiveData() {
        return liveData;
    }

    public MutableLiveData<ProfileSendBody> sendProfile(ProfileSendRequest request){
        return repository.sendProfile(request);
    }

    public void update(ProfileRequest request){
        liveData = repository.getProfile(request);
    }

    public ObservableInt getShowContent(){
        return repository.getShowContent();
    }

    public ObservableInt getError(){
        return repository.getError();
    }

    public ObservableInt getLoading(){
        return repository.getLoading();
    }

    public ObservableInt getErrorSend(){
        return repository.getErrorSend();
    }
}
