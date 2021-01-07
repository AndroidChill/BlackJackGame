package com.example.blackjackgame.rViewModel.news;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackjackgame.rModel.coinsGet.CoinsGetBody;
import com.example.blackjackgame.rModel.news.NewsBody;
import com.example.blackjackgame.rNetwork.request.coinsGet.CoinsGetRequest;
import com.example.blackjackgame.rNetwork.request.news.NewsRequest;
import com.example.blackjackgame.rViewModel.getMonet.GetMonetRepository;

public class NewsViewModel extends ViewModel {

    private NewsRepository repository;
    private MutableLiveData<NewsBody> liveData;

    public NewsViewModel(NewsRequest request){
        repository = NewsRepository.getInstance();
        liveData = repository.getNewsList(request);
    }

    public MutableLiveData<NewsBody> getLiveData() {
        return liveData;
    }

    public void update(NewsRequest request){
        liveData = repository.getNewsList(request);
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
