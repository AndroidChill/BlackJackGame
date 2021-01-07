package com.example.blackjackgame.rViewModel.inviteGame;

import android.content.Context;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.inviteGame.InviteGameApplyBody;
import com.example.blackjackgame.rModel.inviteGame.InviteGameCancelBody;
import com.example.blackjackgame.rModel.news.NewsBody;
import com.example.blackjackgame.rNetwork.request.inviteGame.InviteGameApplyRequest;
import com.example.blackjackgame.rNetwork.request.inviteGame.InviteGameCancelRequest;
import com.example.blackjackgame.rNetwork.request.news.NewsRequest;
import com.example.blackjackgame.rViewModel.news.NewsRepository;

public class InviteGameViewModel extends ViewModel {

    private InviteGameRepository repository;

    public InviteGameViewModel(){
        repository = InviteGameRepository.getInstance();
    }

    public MutableLiveData<InviteGameApplyBody> applyGame(Context context, InviteGameApplyRequest request) {
        return repository.applyGame(context, request);
    }

    public MutableLiveData<InviteGameCancelBody> cancelGame(Context context, InviteGameCancelRequest request) {
        return repository.cancelGame(context, request);
    }

}
