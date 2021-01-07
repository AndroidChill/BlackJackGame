package com.example.blackjackgame.rViewModel.friendsAll;

import android.content.Context;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.friends.all.FriendsAllBody;
import com.example.blackjackgame.rModel.friends.all.FriendsCreateBody;
import com.example.blackjackgame.rModel.friends.all.FriendsSearchBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestBody;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsCreateRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsSearchRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestRequest;

import java.util.Observable;

public class FriendsAllViewModel extends ViewModel {

    private MutableLiveData<FriendsAllBody> liveData;
    private FriendsAllRepository repository;

    public FriendsAllViewModel(FriendsAllRequest request){
        repository = FriendsAllRepository.getInstance();
        liveData = repository.getAllFriends(request);
    }

    public MutableLiveData<FriendsAllBody> getLiveData() {
        return liveData;
    }

    public MutableLiveData<FriendsSearchBody> getSearch(FriendsSearchRequest request){
        return repository.getSearchFriends(request);
    }

    public MutableLiveData<FriendsCreateBody> createSearch(Context context, FriendsCreateRequest request){
        return repository.createRequest(context, request);
    }

    public void update(FriendsAllRequest request){
        liveData = repository.getAllFriends(request);
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
