package com.example.blackjackgame.rViewModel.friendsRequest;

import android.content.Context;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

public class FriendsRequestViewModel extends ViewModel {

    private MutableLiveData<FriendsRequestBody> liveData;
    private FriendsRequestRepository repository;

    public FriendsRequestViewModel(FriendsRequestRequest request){

        repository = FriendsRequestRepository.getInstance();
        liveData = repository.getRequestFriends(request);

    }

    public MutableLiveData<FriendsRequestBody> getLiveData() {
        return liveData;
    }

    public void update(FriendsRequestRequest requestRequest){
        liveData = repository.getRequestFriends(requestRequest);
    }

    public MutableLiveData<FriendsCreateBody> createSearch(Context context, FriendsCreateRequest request){
        return repository.createRequest(context, request);
    }

    public MutableLiveData<FriendsRequestAddBody> addRequest(Context context, FriendsRequestAddRequest request){
        return repository.addRequest(context, request);
    }

    public MutableLiveData<FriendsRequestDelBody> delRequest(Context context, FriendsRequestDelRequest request){
        return repository.delRequest(context, request);
    }

    public MutableLiveData<FriendsRequestCancelBody> cancelRequest(Context context, FriendsRequestCancelRequest request){
        return repository.cancelRequest(context, request);
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
