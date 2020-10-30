package com.example.blackjackgame.viewmodel.profile;

import android.app.Application;
import android.widget.ImageView;

import androidx.core.util.Pair;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.blackjackgame.model.friend.getMonet.GiveMonetBody;
import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.model.profile.any.ProfileAnyBody;
import com.example.blackjackgame.model.profile.avatar.AvatarBody;
import com.example.blackjackgame.model.profile.changeData.ProfileChangeBody;
import com.example.blackjackgame.network.responce.stattics.CaptchaRequest;
import com.example.blackjackgame.model.statics.CaptchaBody;
import com.example.blackjackgame.model.statics.ReviewBody;
import com.example.blackjackgame.network.responce.friend.GiveMonetRequest;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.profile.any.ProfileAnyRequest;
import com.example.blackjackgame.network.responce.profile.avatar.AvatarChangeRequest;
import com.example.blackjackgame.network.responce.profile.change.ProfileChangeDataRequest;
import com.example.blackjackgame.network.responce.stattics.ReviewRequest;

import io.reactivex.annotations.NonNull;

public class ProfileViewModel extends AndroidViewModel {

    private ProfileRepository profileRepository;

    public ProfileViewModel(@NonNull Application application){
        super(application);

        profileRepository = ProfileRepository.getInstance();
    }

    public MutableLiveData<Pair<String, CaptchaBody>> checkCaptcha(CaptchaRequest captchaRequest){
        return profileRepository.checkCaptcha(captchaRequest);
    }

    public MutableLiveData<Pair<String, ReviewBody>> checkReview(ReviewRequest request){
        return profileRepository.checkReview(request);
    }

    public MutableLiveData<Pair<String, AvatarBody>> getAvatarList(AvatarChangeRequest request){
        return profileRepository.getAvatarList(request);
    }

    public MutableLiveData<ProfileChangeBody> changeData(ProfileChangeDataRequest request){
        return profileRepository.changeData(request);
    }

    public MutableLiveData<ProfileBody> getProfileData(ImageView view, DataProfileRequest request){
        return profileRepository.getProfileData(view, request);
    }

    public MutableLiveData<ProfileAnyBody> getProfileData(ProfileAnyRequest request){
        return profileRepository.getProfileAny(request);
    }

    public MutableLiveData<GiveMonetBody> giveMonet(GiveMonetRequest request){
        return profileRepository.giveMonet(request);
    }


}
