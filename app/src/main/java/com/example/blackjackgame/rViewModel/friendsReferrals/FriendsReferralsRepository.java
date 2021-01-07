package com.example.blackjackgame.rViewModel.friendsReferrals;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.friends.referrals.FriendsReferralsBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.friends.referals.FriendsReferralsRequest;
import com.example.blackjackgame.rNetwork.service.FriendsService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FriendsReferralsRepository {

    private static FriendsReferralsRepository instance;
    private CompositeDisposable compositeDisposable;
    private FriendsService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    private FriendsReferralsRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getFriendsApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static FriendsReferralsRepository getInstance() {
        if(instance == null){
            instance = new FriendsReferralsRepository();
        }
        return instance;
    }

    public MutableLiveData<FriendsReferralsBody> getReferralsFriends(FriendsReferralsRequest request){
        MutableLiveData<FriendsReferralsBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.getReferralsFriends(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsReferralsBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull FriendsReferralsBody friendsReferralsBody) {
                        liveData.setValue(friendsReferralsBody);

                        error.set(View.GONE);
                        loading.set(View.GONE);
                        showContent.set(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        error.set(View.VISIBLE);
                        loading.set(View.GONE);
                        showContent.set(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }

    public ObservableInt getError() {
        return error;
    }

    public ObservableInt getLoading() {
        return loading;
    }

    public ObservableInt getShowContent() {
        return showContent;
    }
}
