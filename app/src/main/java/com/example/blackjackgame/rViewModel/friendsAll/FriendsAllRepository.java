package com.example.blackjackgame.rViewModel.friendsAll;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.friends.all.FriendsAllBody;
import com.example.blackjackgame.rModel.friends.all.FriendsCreateBody;
import com.example.blackjackgame.rModel.friends.all.FriendsSearchBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsCreateRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsSearchRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestRequest;
import com.example.blackjackgame.rNetwork.service.FriendsService;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FriendsAllRepository {

    private static FriendsAllRepository instance;
    private CompositeDisposable compositeDisposable;
    private FriendsService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    private FriendsAllRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getFriendsApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static FriendsAllRepository getInstance() {
        if(instance == null){
            instance = new FriendsAllRepository();
        }
        return instance;
    }

    public MutableLiveData<FriendsAllBody> getAllFriends(FriendsAllRequest request){
        MutableLiveData<FriendsAllBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.getAllFriends(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsAllBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FriendsAllBody friendsAllBody) {
                        liveData.setValue(friendsAllBody);

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

    public MutableLiveData<FriendsSearchBody> getSearchFriends(FriendsSearchRequest request){
        MutableLiveData<FriendsSearchBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);

        service.getSearchFriends(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsSearchBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FriendsSearchBody friendsSearchBody) {
                        liveData.setValue(friendsSearchBody);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        error.set(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return liveData;
    }

    public MutableLiveData<FriendsCreateBody> createRequest(Context context, FriendsCreateRequest request){
        MutableLiveData<FriendsCreateBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);

        service.createRequest(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsCreateBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FriendsCreateBody friendsSearchBody) {
                        liveData.setValue(friendsSearchBody);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "Ошибка сервера. Попробуйте еще раз", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return liveData;
    }

    public ObservableInt getShowContent() {
        return showContent;
    }

    public ObservableInt getLoading() {
        return loading;
    }

    public ObservableInt getError() {
        return error;
    }
}
