package com.example.blackjackgame.viewmodel.sign;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.sign.NewsGuest;

public class SignViewModel extends AndroidViewModel {

    private SignRepository signRepository;

    public SignViewModel(@NonNull Application application) {
        super(application);

        signRepository = SignRepository.getInstance();
    }

    public MutableLiveData<NewsGuest> signGuest(){
        return signRepository.signGuest();
    }

}
