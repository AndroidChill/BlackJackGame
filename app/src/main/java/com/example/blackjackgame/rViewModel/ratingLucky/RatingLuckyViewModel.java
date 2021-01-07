package com.example.blackjackgame.rViewModel.ratingLucky;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.rating.RatingBody;
import com.example.blackjackgame.rModel.ratingLucky.RatingLuckyBody;
import com.example.blackjackgame.rNetwork.request.rating.RatingRequest;
import com.example.blackjackgame.rNetwork.request.ratingLucky.RatingLuckyRequest;
import com.example.blackjackgame.rViewModel.rating.RatingRepository;

public class RatingLuckyViewModel extends ViewModel {

    private RatingLuckyRepository repository;
    private MutableLiveData<RatingLuckyBody> liveData;

    public RatingLuckyViewModel(RatingLuckyRequest request){
        repository = RatingLuckyRepository.getInstance();
        liveData = repository.getRating(request);
    }

    public MutableLiveData<RatingLuckyBody> getLiveData() {
        return liveData;
    }

    public void update(RatingLuckyRequest request){
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
