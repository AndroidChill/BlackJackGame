package com.example.blackjackgame.viewmodel.news;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.friend.FriendBody;
import com.example.blackjackgame.model.news.NewsBody;
import com.example.blackjackgame.network.responce.friend.FriendRequest;
import com.example.blackjackgame.network.responce.news.NewsRequest;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;

    public NewsViewModel(Application application){
        super(application);

        newsRepository = NewsRepository.getInstance();
    }

    public MutableLiveData<NewsBody> getNews(NewsRequest request){
        return newsRepository.getNews(request);
    }


}
