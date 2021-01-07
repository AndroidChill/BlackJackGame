package com.example.blackjackgame.rViewModel.profileAny;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.profileAny.ProfileAny;
import com.example.blackjackgame.rModel.profileAny.ProfileAnyBody;
import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;

public class ProfileAnyViewModel extends ViewModel {

    private ProfileAnyRepository repository;
    private MutableLiveData<ProfileAnyBody> liveData;

    public ProfileAnyViewModel(ProfileAnyRequest request){
        repository = ProfileAnyRepository.getInstance();
        liveData = repository.getProfileAny(request);
    }

    public MutableLiveData<ProfileAnyBody> getLiveData() {
        return liveData;
    }

    public void update(ProfileAnyRequest request){
        liveData = repository.getProfileAny(request);
    }

    public ObservableInt getError(){
        return repository.getError();
    }

    public ObservableInt getLoading(){
        return repository.getLoading();
    }

    public ObservableInt getShowContent(){
        return repository.getShowContent();
    }
}
