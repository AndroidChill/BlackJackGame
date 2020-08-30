package com.example.blackjackgame.viewmodel.getmonet;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.model.getmonet.GetMonetBody;
import com.example.blackjackgame.model.getmonet.finish.GetMonetFinishBody;
import com.example.blackjackgame.model.profile.avatar.AvatarBody;
import com.example.blackjackgame.network.responce.getmonet.GetMonetFinishRequest;
import com.example.blackjackgame.network.responce.getmonet.GetMonetRequest;
import com.example.blackjackgame.network.responce.profile.avatar.AvatarChangeRequest;
import com.example.blackjackgame.viewmodel.profile.ProfileRepository;

import io.reactivex.annotations.NonNull;

public class GetMonetViewModel extends AndroidViewModel {

    private GetMonetRepository getMonetRepository;

    public GetMonetViewModel(@NonNull Application application){
        super(application);

        getMonetRepository = GetMonetRepository.getInstance();
    }

    public MutableLiveData<GetMonetBody> getTasks(GetMonetRequest request){
        return getMonetRepository.getTasks(request);
    }

    public MutableLiveData<GetMonetFinishBody> postFinishTask(GetMonetFinishRequest request){
        return getMonetRepository.postFinishTask(request);
    }

}
