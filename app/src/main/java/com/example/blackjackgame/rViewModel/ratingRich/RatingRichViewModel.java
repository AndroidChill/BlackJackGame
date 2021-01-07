package com.example.blackjackgame.rViewModel.ratingRich;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.rating.RatingBody;
import com.example.blackjackgame.rModel.ratingRich.RatingRichBody;
import com.example.blackjackgame.rNetwork.request.rating.RatingRequest;
import com.example.blackjackgame.rNetwork.request.ratingRich.RatingRichRequest;
import com.example.blackjackgame.rViewModel.rating.RatingRepository;

public class RatingRichViewModel extends ViewModel {

    private RatingRichRepository repository;
    private MutableLiveData<RatingRichBody> liveData;

    public RatingRichViewModel(RatingRichRequest request){
        repository = RatingRichRepository.getInstance();
        liveData = repository.getRating(request);
    }

    public MutableLiveData<RatingRichBody> getLiveData() {
        return liveData;
    }

    public void update(RatingRichRequest request){
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
