package com.example.blackjackgame.rViewModel.friendsReferrals;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.friends.referrals.FriendsReferralsBody;
import com.example.blackjackgame.rNetwork.request.friends.referals.FriendsReferralsRequest;

public class FriendsReferralsViewModel extends ViewModel {

    private MutableLiveData<FriendsReferralsBody> liveData;
    private FriendsReferralsRepository repository;

    public FriendsReferralsViewModel(FriendsReferralsRequest request){
        repository = FriendsReferralsRepository.getInstance();
        liveData = repository.getReferralsFriends(request);
    }

    public MutableLiveData<FriendsReferralsBody> getLiveData() {
        return liveData;
    }

    public void update(FriendsReferralsRequest request){
        liveData = repository.getReferralsFriends(request);
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
