package com.example.blackjackgame.viewmodel.review;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.model.statics.ReviewBody;
import com.example.blackjackgame.network.responce.stattics.ReviewRequest;

public class ReviewViewModel extends ViewModel {

    private ReviewRepository repository;
    private MutableLiveData<ReviewBody> liveData;

    public ReviewViewModel(){
        repository = ReviewRepository.getInstance();
    }

    public MutableLiveData<ReviewBody> getLiveData(ReviewRequest request){
        return repository.getReview(request);
    }

}
