package com.example.blackjackgame.viewmodel.rightRegistration;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.model.registration.RegistrationBody;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.registration.RegistrationRequest;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileRepository;

public class RegistrationViewModel extends ViewModel {

    private RegistrationRepository repository;
    private MutableLiveData<RegistrationBody> registr = new MutableLiveData<>();

    public RegistrationViewModel(RegistrationRequest request){
        repository = RegistrationRepository.getInstance();
        registr = repository.getRegistration(request);
    }

    public MutableLiveData<RegistrationBody> getRegistr() {
        return registr;
    }

    public void update(RegistrationRequest request){
        registr = repository.getRegistration(request);
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

