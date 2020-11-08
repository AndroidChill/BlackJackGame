package com.example.blackjackgame.rViewModel.signGuest;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.network.responce.sign.SignGuestRequest;
import com.example.blackjackgame.rModel.sign.SignGuestBody;
import com.example.blackjackgame.rNetwork.request.sign.SignInGuestRequest;

public class SignGuestViewModel extends ViewModel {

    private SignGuestRepository repository;

    public SignGuestViewModel(){
        repository = SignGuestRepository.getInstance();
    }

    public MutableLiveData<SignGuestBody> signGuest(SignInGuestRequest request){
        return repository.signGuest(request);
    }

}
