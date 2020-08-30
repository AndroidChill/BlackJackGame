package com.example.blackjackgame.viewmodel.rating;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.rating.RatingCustom;
import com.example.blackjackgame.model.rating.ratingLuck.RatingLuckBody;
import com.example.blackjackgame.model.rating.ratingRich.RatingRichBody;
import com.example.blackjackgame.network.responce.rating.RatingCoinsRequest;
import com.example.blackjackgame.network.responce.rating.RatingRequest;
import com.example.blackjackgame.network.responce.rating.RatingRichRequest;

public class RatingViewModel extends AndroidViewModel {

    private RatingRepository ratingRepository;

    public RatingViewModel(@NonNull Application application) {
        super(application);

        ratingRepository = RatingRepository.getInstance();
    }

    public MutableLiveData<RatingRichBody> getRatingRich(RatingRichRequest request){
        return ratingRepository.getRatingRich(request);
    }

    public MutableLiveData<RatingLuckBody> getRatingLuck(RatingCoinsRequest request){
        return ratingRepository.getRatingLuck(request);
    }

    public MutableLiveData<RatingCustom> getRating(RatingRequest request){
        return ratingRepository.getRating(request);
    }
}
