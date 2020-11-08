package com.example.blackjackgame.rViewModel.signinemailstart;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rNetwork.request.sign.SignInEmailStartRequest;
import com.example.blackjackgame.rModel.sign.SignInEmailStartBody;

public class SignInEmailStartViewModel extends ViewModel {

    private SignInEmailStartRepository repository;
    private MutableLiveData<SignInEmailStartBody> liveData;

    public SignInEmailStartViewModel(SignInEmailStartRequest request){
        repository = SignInEmailStartRepository.getInstance();
        liveData = new MutableLiveData<>();
        liveData = repository.signInEmailStartBodyMutableLiveData(request);
    }

    public MutableLiveData<SignInEmailStartBody> getLiveData() {
        return liveData;
    }

    public void updateData(SignInEmailStartRequest request){
        this.liveData = repository.signInEmailStartBodyMutableLiveData(request);
    }

    public ObservableInt getLoading(){
        return repository.getLoading();
    }

    public ObservableInt getError(){
        return repository.getError();
    }

    public ObservableInt getShowContent(){
        return repository.getShowContent();
    }

}
