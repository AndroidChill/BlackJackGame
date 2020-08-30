package com.example.blackjackgame.network;

import com.example.blackjackgame.model.friend.FriendBody;
import com.example.blackjackgame.model.friend.Global.FriendGlobalBody;
import com.example.blackjackgame.model.friend.getMonet.GiveMonetBody;
import com.example.blackjackgame.model.friend.referrals.ReferralsBody;
import com.example.blackjackgame.model.friend.request.add.FriendsAddBody;
import com.example.blackjackgame.model.friend.request.del.FriendsDelBody;
import com.example.blackjackgame.model.friend.request.output.FriendsZaprosBody;
import com.example.blackjackgame.model.getmonet.GetMonetBody;
import com.example.blackjackgame.model.getmonet.finish.GetMonetFinishBody;
import com.example.blackjackgame.model.news.News;
import com.example.blackjackgame.model.news.NewsBody;
import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.model.profile.any.ProfileAnyBody;
import com.example.blackjackgame.model.profile.avatar.AvatarBody;
import com.example.blackjackgame.model.profile.changeData.ProfileChangeBody;
import com.example.blackjackgame.model.rating.RatingCustom;
import com.example.blackjackgame.model.rating.ratingLuck.RatingLuckBody;
import com.example.blackjackgame.model.rating.ratingRich.RatingRichBody;
import com.example.blackjackgame.model.sign.NewsGuest;
import com.example.blackjackgame.model.tournament.TournamentBody;
import com.example.blackjackgame.network.responce.friend.FriendReferalsRequest;
import com.example.blackjackgame.network.responce.friend.FriendRequest;
import com.example.blackjackgame.network.responce.friend.FriendSearchRequest;
import com.example.blackjackgame.network.responce.friend.FriendsZaprosRequest;
import com.example.blackjackgame.network.responce.friend.GiveMonetRequest;
import com.example.blackjackgame.network.responce.friend.request.FriendsAddRequest;
import com.example.blackjackgame.network.responce.friend.request.FriendsDelRequest;
import com.example.blackjackgame.network.responce.getmonet.GetMonetFinishRequest;
import com.example.blackjackgame.network.responce.getmonet.GetMonetRequest;
import com.example.blackjackgame.network.responce.news.NewsRequest;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.profile.any.ProfileAnyRequest;
import com.example.blackjackgame.network.responce.profile.avatar.AvatarChangeRequest;
import com.example.blackjackgame.network.responce.profile.change.ProfileChangeDataRequest;
import com.example.blackjackgame.network.responce.rating.RatingCoinsRequest;
import com.example.blackjackgame.network.responce.rating.RatingRequest;
import com.example.blackjackgame.network.responce.rating.RatingRichRequest;
import com.example.blackjackgame.network.responce.sign.SignGuestRequest;
import com.example.blackjackgame.network.responce.tournament.TournamentListRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    /*
    * ВХОД
    * */
    @POST("json")
    Observable<NewsGuest> getData(@Body SignGuestRequest request);


    /*
    * НОВОСТИ
    * */

    //получаем список новостей
    @POST("json")
    Observable<NewsBody> getNews(@Body NewsRequest request);

    /*
    * ПРОФИЛЬ
    * */

    //получаем профиль пользователя
    @POST("json")
    Observable<ProfileBody> getProfile(@Body DataProfileRequest request);

    //изменяем данные профиля
    @POST("json")
    Observable<ProfileChangeBody> changeDataProfile(@Body ProfileChangeDataRequest request);

    //список доступных аватарок
    @POST("json")
    Observable<AvatarBody> getAvatarList(@Body AvatarChangeRequest request);

    //получаем профиль какого-то пользователя
    @POST("json")
    Observable<ProfileAnyBody> getProfileAny(@Body ProfileAnyRequest request);

    //отправляем монеты пользователю
    @POST("json")
    Observable<GiveMonetBody> giveMonet(@Body GiveMonetRequest request);

    /*
    * РЕЙТИНГ
    * */
    @POST("json")
    Observable<RatingRichBody> getRating(@Body RatingRichRequest request);

    @POST("json")
    Observable<RatingLuckBody> getRating(@Body RatingCoinsRequest request);

    @POST("json")
    Observable<RatingCustom> getRating(@Body RatingRequest request);

    /*
    * ДРУЗЬЯ
    * */

    //мои друзья
    @POST("json")
    Observable<FriendBody> getMyFriend(@Body FriendRequest request);

    //поиск по всем друзьям
    @POST("json")
    Observable<FriendGlobalBody> getGlobalFriend(@Body FriendSearchRequest request);

    //получение всех рефералов
    @POST("json")
    Observable<ReferralsBody> getReferals(@Body FriendReferalsRequest request);

    //получение всех запросов
    @POST("json")
    Observable<FriendsZaprosBody> getRequest(@Body FriendsZaprosRequest request);

    //добавление друга
    @POST("json")
    Observable<FriendsAddBody> addFriend(@Body FriendsAddRequest request);

    //удаление/отмена заявки друга
    @POST("json")
    Observable<FriendsDelBody> delFriend(@Body FriendsDelRequest request);

    /*
    * Получение монет
    * */

    //получение всех заданий
    @POST("json")
    Observable<GetMonetBody> getTasks(@Body GetMonetRequest request);

    //выполнение заданий
    @POST("json")
    Observable<GetMonetFinishBody> postFinishTask(@Body GetMonetFinishRequest request);

    /*
    * Турниры
    * */

    //получение всех турниров
    @POST("json")
    Observable<TournamentBody> getTournaments(@Body TournamentListRequest request);

//    @POST("/api/game/debug.php")
//    Observable<NewsSignIn> getData(@Body RequestSignIn request);
//
//    @POST("/api/game/debug.php")
//    Observable<ProfileBody> getProfile(@Body ProfileRequest request);


}
