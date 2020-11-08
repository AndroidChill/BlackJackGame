package com.example.blackjackgame.rViewModel.signInEmailCode;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.sign.SignInEmailCodeBody;
import com.example.blackjackgame.rNetwork.request.sign.SignInEmailCodeRequest;

public class SignInEmailCodeViewModel extends ViewModel {

    private MutableLiveData<SignInEmailCodeBody> liveData;
    private SignInEmailCodeRepository repository;

    public SignInEmailCodeViewModel(SignInEmailCodeRequest request){
        repository = SignInEmailCodeRepository.getInstance();
        liveData = new MutableLiveData<>();
        update(request);
    }

    public void update(SignInEmailCodeRequest request){
        liveData = repository.getLoginEnd(request);
    }

    public MutableLiveData<SignInEmailCodeBody> getLiveData() {
        return liveData;
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
