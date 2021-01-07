package com.example.blackjackgame.rViewModel.rating;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.rating.RatingBody;
import com.example.blackjackgame.rNetwork.request.rating.RatingRequest;

public class RatingViewModel extends ViewModel {

    private RatingRepository repository;
    private MutableLiveData<RatingBody> liveData;

    public RatingViewModel(RatingRequest request){
        repository = RatingRepository.getInstance();
        liveData = repository.getRating(request);
    }

    public MutableLiveData<RatingBody> getLiveData() {
        return liveData;
    }

    public void update(RatingRequest request){
        liveData = repository.getRating(request);
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
