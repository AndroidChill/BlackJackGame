package com.example.blackjackgame.viewmodel.rightGetMonet;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.model.getmonet.GetMonetBody;
import com.example.blackjackgame.network.responce.getmonet.GetMonetRequest;

public class GetMonetViewModel extends ViewModel {

    private MutableLiveData<GetMonetBody> liveData;
    private GetMonetRepository repository;

    public GetMonetViewModel(GetMonetRequest request){
        repository = GetMonetRepository.getInstance();
        liveData = repository.getTasks(request);
    }

    public MutableLiveData<GetMonetBody> getLiveData() {
        return liveData;
    }

    public void update(GetMonetRequest request){
        liveData = repository.getTasks(request);
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
