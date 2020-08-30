package com.example.blackjackgame.viewmodel.tournament;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.rating.ratingRich.RatingRichBody;
import com.example.blackjackgame.model.tournament.TournamentBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.rating.RatingRichRequest;
import com.example.blackjackgame.network.responce.tournament.TournamentListRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TournamentRepository {

    private static TournamentRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private TournamentRepository(){
        apiService = ApiFactory.getInstance().getJSONApi();
        compositeDisposable = new CompositeDisposable();
    }

    public static TournamentRepository getInstance() {
        if(instance == null){
            instance = new TournamentRepository();
        }
        return instance;
    }

    public MutableLiveData<TournamentBody> getTournaments(TournamentListRequest request){
        MutableLiveData<TournamentBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getTournaments(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

}


//public class RatingRepository {
//
//    private static com.example.blackjackgame.viewmodel.rating.RatingRepository instance;
//    private CompositeDisposable compositeDisposable;
//    private ApiService apiService;
//
//    private RatingRepository(){
//        apiService = ApiFactory.getInstance().getJSONApi();
//        compositeDisposable = new CompositeDisposable();
//    }
//
//    public static com.example.blackjackgame.viewmodel.rating.RatingRepository getInstance() {
//        if(instance == null){
//            instance = new com.example.blackjackgame.viewmodel.rating.RatingRepository();
//        }
//        return instance;
//    }
//
//    public MutableLiveData<RatingRichBody> getRatingRich(RatingRichRequest request){
//        MutableLiveData<RatingRichBody> liveData = new MutableLiveData<>();
//
//        compositeDisposable.add(apiService.getRating(request)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(liveData::setValue, Throwable::printStackTrace));
//
//        return liveData;
//    }
