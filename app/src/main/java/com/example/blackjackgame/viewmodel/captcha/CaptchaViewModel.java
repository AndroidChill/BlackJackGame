package com.example.blackjackgame.viewmodel.captcha;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.model.statics.CaptchaBody;
import com.example.blackjackgame.network.responce.stattics.CaptchaRequest;
import com.example.blackjackgame.util.Captcha;

public class CaptchaViewModel extends ViewModel {

    private MutableLiveData<CaptchaBody> liveData;
    private CaptchaRepository repository;

    public CaptchaViewModel(){
        repository = CaptchaRepository.getInstance();
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<CaptchaBody> checkCaptcha(CaptchaRequest request){
        return repository.checkCaptcha(request);
    }

}
