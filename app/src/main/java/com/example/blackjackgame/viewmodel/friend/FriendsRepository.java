package com.example.blackjackgame.viewmodel.friend;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.friend.FriendBody;
import com.example.blackjackgame.model.friend.Global.FriendGlobalBody;
import com.example.blackjackgame.model.friend.referrals.ReferralsBody;
import com.example.blackjackgame.model.friend.request.add.FriendsAddBody;
import com.example.blackjackgame.model.friend.request.del.FriendsDelBody;
import com.example.blackjackgame.model.friend.request.output.FriendsZaprosBody;
import com.example.blackjackgame.model.rating.RatingCustom;
import com.example.blackjackgame.model.rating.ratingLuck.RatingLuckBody;
import com.example.blackjackgame.model.rating.ratingRich.RatingRichBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.friend.FriendReferalsRequest;
import com.example.blackjackgame.network.responce.friend.FriendRequest;
import com.example.blackjackgame.network.responce.friend.FriendSearchRequest;
import com.example.blackjackgame.network.responce.friend.FriendsZaprosRequest;
import com.example.blackjackgame.network.responce.friend.request.FriendsAddRequest;
import com.example.blackjackgame.network.responce.friend.request.FriendsDelRequest;
import com.example.blackjackgame.network.responce.rating.RatingCoinsRequest;
import com.example.blackjackgame.network.responce.rating.RatingRequest;
import com.example.blackjackgame.network.responce.rating.RatingRichRequest;
import com.example.blackjackgame.viewmodel.rating.RatingRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FriendsRepository {

    private static FriendsRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private FriendsRepository(){
        apiService = ApiFactory.getInstance().getJSONApi();
        compositeDisposable = new CompositeDisposable();
    }

    public static FriendsRepository getInstance() {
        if(instance == null){
            instance = new FriendsRepository();
        }
        return instance;
    }

    public MutableLiveData<ReferralsBody> getFriends(FriendReferalsRequest request){
        MutableLiveData<ReferralsBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getReferals(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    public MutableLiveData<FriendBody> getFriends(FriendRequest request){
        MutableLiveData<FriendBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getMyFriend(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    public MutableLiveData<FriendGlobalBody> getFriends(FriendSearchRequest request){
        MutableLiveData<FriendGlobalBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getGlobalFriend(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    //получение всех запросов
    public MutableLiveData<FriendsZaprosBody> getRequest(FriendsZaprosRequest request){
        MutableLiveData<FriendsZaprosBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getRequest(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    //добавление друга
    public MutableLiveData<FriendsAddBody> addFriend(FriendsAddRequest request){
        MutableLiveData<FriendsAddBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.addFriend(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    //удаление/сокрытие заявки друга
    public MutableLiveData<FriendsDelBody> delFriend(FriendsDelRequest request){
        MutableLiveData<FriendsDelBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.delFriend(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

}
