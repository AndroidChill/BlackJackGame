package com.example.blackjackgame.rViewModel.registrationStart;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.registration.RegistrationBody;
import com.example.blackjackgame.rModel.registration.RegistrationStartBody;
import com.example.blackjackgame.rNetwork.request.registration.RegistrationRequest;
import com.example.blackjackgame.rNetwork.request.registration.RegistrationStartRequest;

public class RegistrationStartViewModel extends ViewModel {

    private MutableLiveData<RegistrationStartBody> liveData;
    private RegistrationStartRepository repository;

    public RegistrationStartViewModel(RegistrationStartRequest request){
        repository = RegistrationStartRepository.getInstance();
        liveData = new MutableLiveData<>();
        update(request);
    }

    public void update(RegistrationStartRequest request){
        liveData = repository.registrationStart(request);
    }

    public MutableLiveData<RegistrationStartBody> getLiveData(){
        return liveData;
    }

    public MutableLiveData<RegistrationBody> send(RegistrationRequest request){
        return repository.send(request);
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
