package com.example.blackjackgame.rViewModel.signinemail;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.sign.SignInEmailBody;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailRequest;

public class SignInEmailViewModel extends ViewModel {

    private MutableLiveData<SignInEmailBody> liveData;
    private SignInEmailRepository repository;

    public SignInEmailViewModel(SignInEmailRequest request){
        repository = SignInEmailRepository.getInstance();
        liveData = new MutableLiveData<>();
        update(request);
    }

    public MutableLiveData<SignInEmailBody> getLiveData() {
        return liveData;
    }

    public void update(SignInEmailRequest request){
        liveData = repository.getLiveData(request);
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
