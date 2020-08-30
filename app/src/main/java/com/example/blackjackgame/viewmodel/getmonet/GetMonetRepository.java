package com.example.blackjackgame.viewmodel.getmonet;

import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.friend.getMonet.GiveMonetBody;
import com.example.blackjackgame.model.getmonet.GetMonetBody;
import com.example.blackjackgame.model.getmonet.finish.GetMonetFinishBody;
import com.example.blackjackgame.network.ApiFactory;
import com.example.blackjackgame.network.ApiService;
import com.example.blackjackgame.network.responce.friend.GiveMonetRequest;
import com.example.blackjackgame.network.responce.getmonet.GetMonetFinishRequest;
import com.example.blackjackgame.network.responce.getmonet.GetMonetRequest;
import com.example.blackjackgame.viewmodel.profile.ProfileRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GetMonetRepository {

    private static GetMonetRepository instance;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    private GetMonetRepository(){
        apiService = ApiFactory.getInstance().getJSONApi();
        compositeDisposable = new CompositeDisposable();
    }

    public static GetMonetRepository getInstance(){
        if(instance == null){
            instance = new GetMonetRepository();
        }
        return instance;
    }

    //получение монет
    public MutableLiveData<GetMonetBody> getTasks(GetMonetRequest request){
        MutableLiveData<GetMonetBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.getTasks(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

    //задание выполнено
    public MutableLiveData<GetMonetFinishBody> postFinishTask(GetMonetFinishRequest request){
        MutableLiveData<GetMonetFinishBody> liveData = new MutableLiveData<>();

        compositeDisposable.add(apiService.postFinishTask(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, Throwable::printStackTrace));

        return liveData;
    }

}
