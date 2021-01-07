package com.example.blackjackgame.rViewModel.friendsRequest;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.rModel.friends.all.FriendsCreateBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestAddBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestCancelBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestDelBody;
import com.example.blackjackgame.rNetwork.ServerFactory;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsCreateRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestAddRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestCancelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestDelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestRequest;
import com.example.blackjackgame.rNetwork.service.FriendsService;
import com.example.blackjackgame.rViewModel.friendsReferrals.FriendsReferralsRepository;
import com.google.android.material.snackbar.Snackbar;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FriendsRequestRepository {

    private static FriendsRequestRepository instance;
    private CompositeDisposable compositeDisposable;
    private FriendsService service;

    private ObservableInt error;
    private ObservableInt loading;
    private ObservableInt showContent;

    private FriendsRequestRepository(){
        compositeDisposable = new CompositeDisposable();
        service = ServerFactory.getInstance().getFriendsApi();

        error = new ObservableInt();
        loading = new ObservableInt();
        showContent = new ObservableInt();
    }

    public static FriendsRequestRepository getInstance() {
        if(instance == null){
            instance = new FriendsRequestRepository();
        }
        return instance;
    }

    public MutableLiveData<FriendsRequestBody> getRequestFriends(FriendsRequestRequest request){
        MutableLiveData<FriendsRequestBody> liveData = new MutableLiveData<>();

        error.set(View.GONE);
        loading.set(View.VISIBLE);
        showContent.set(View.GONE);

        service.getRequestFriends(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsRequestBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull FriendsRequestBody friendsRequestBody) {
                        liveData.setValue(friendsRequestBody);

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

    public MutableLiveData<FriendsRequestAddBody> addRequest(Context context, FriendsRequestAddRequest request){
        MutableLiveData<FriendsRequestAddBody> liveData = new MutableLiveData<>();

        service.getRequestAddFriends(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsRequestAddBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FriendsRequestAddBody friendsRequestAddBody) {
                        liveData.setValue(friendsRequestAddBody);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "Ошибка сервера, попробуйте еще раз", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }

    public MutableLiveData<FriendsRequestDelBody> delRequest(Context context, FriendsRequestDelRequest request){
        MutableLiveData<FriendsRequestDelBody> liveData = new MutableLiveData<>();

        service.getRequestDelFriends(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsRequestDelBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FriendsRequestDelBody friendsRequestDelBody) {
                        liveData.setValue(friendsRequestDelBody);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "Ошибка сервера, попробуйте еще раз", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveData;
    }

    public MutableLiveData<FriendsRequestCancelBody> cancelRequest(Context context, FriendsRequestCancelRequest request){
        MutableLiveData<FriendsRequestCancelBody> liveData = new MutableLiveData<>();

        service.getRequestCancelFriends(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsRequestCancelBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FriendsRequestCancelBody friendsRequestDelBody) {
                        liveData.setValue(friendsRequestDelBody);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "Ошибка сервера, попробуйте еще раз", Toast.LENGTH_SHORT).show();
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
