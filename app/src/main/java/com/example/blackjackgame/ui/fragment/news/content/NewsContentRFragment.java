package com.example.blackjackgame.ui.fragment.news.content;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentNewsContentRBinding;
import com.example.blackjackgame.rModel.friends.request.Friends;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestAddBody;
import com.example.blackjackgame.rModel.friends.request.FriendsRequestDelBody;
import com.example.blackjackgame.rModel.inviteGame.InviteGameApplyBody;
import com.example.blackjackgame.rModel.inviteGame.InviteGameCancelBody;
import com.example.blackjackgame.rModel.news.News;
import com.example.blackjackgame.rModel.news.NewsBody;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestAddRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestDelRequest;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestRequest;
import com.example.blackjackgame.rNetwork.request.inviteGame.InviteGameApplyRequest;
import com.example.blackjackgame.rNetwork.request.inviteGame.InviteGameCancelRequest;
import com.example.blackjackgame.rNetwork.request.news.NewsRequest;
import com.example.blackjackgame.rViewModel.friendsRequest.FriendsRequestFactory;
import com.example.blackjackgame.rViewModel.friendsRequest.FriendsRequestViewModel;
import com.example.blackjackgame.rViewModel.inviteGame.InviteGameFactory;
import com.example.blackjackgame.rViewModel.inviteGame.InviteGameViewModel;
import com.example.blackjackgame.rViewModel.news.NewsFactory;
import com.example.blackjackgame.rViewModel.news.NewsViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.fragment.news.NewsFilterFragment;
import com.example.blackjackgame.ui.interfaceClick.INewsClick;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewsContentRFragment extends Fragment implements INewsClick {

    private FragmentNewsContentRBinding binding;
    private NewsViewModel viewModel;
    private FriendsRequestViewModel viewModelFriendsRequest;
    private InviteGameViewModel viewModelInviteGame;
    private NewsRequest request;
    private SharedPreferences sharedPreferences;
    private NewsAdapter adapter;
    private List<News> newsList = new ArrayList<>();


    public static NewsContentRFragment newInstance() {

        Bundle args = new Bundle();

        NewsContentRFragment fragment = new NewsContentRFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_content_r, container, false);
        setHasOptionsMenu(true);
        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        adapter = new NewsAdapter(getContext(), this);

        initRequest();
        viewModel = new ViewModelProvider(getViewModelStore(), new NewsFactory(request)).get(NewsViewModel.class);
        viewModelInviteGame = new ViewModelProvider(getViewModelStore(), new InviteGameFactory()).get(InviteGameViewModel.class);
        viewModelFriendsRequest = new ViewModelProvider(getViewModelStore(), new FriendsRequestFactory(initMainRequest())).get(FriendsRequestViewModel.class);

        binding.setViewModel(viewModel);

        initRv();

        refresh();
        updateUI();

        return binding.getRoot();
    }

    private void initRv(){
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(adapter);
    }

    public void refresh() {
        binding.btnRetry.setOnClickListener(v -> {
            initRequest();
            viewModel.update(request);
            updateUI();
        });
    }

    public void updateUI() {
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<NewsBody>() {
            @Override
            public void onChanged(NewsBody newsBody) {
                if (newsBody.getToken() != null) {
                    if (newsBody.getToken().equals("error")) {
                        startBaseActivity();
                    }
                }

                if (newsBody.getStatus().equals("success")) {

                    //проверка на капчу
                    if (newsBody.getCaptchaImageUrl() != null) {
                        createCaptchaDialog(newsBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if (newsBody.getPopup() != null) {
                        if (newsBody.getPopup().equals("comment")) {
                            createReviewDialog();
                        }
                    }

                    newsList = newsBody.getNewsList();
                    adapter.setList(newsList);

                } else {
                    Toast.makeText(getContext(), newsBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initRequest() {
        request = new NewsRequest(
                "news",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }

    private void startBaseActivity() {
        startActivity(new Intent(getContext(), MainActivity.class));
        ((NavigationActivity) getActivity()).finish();
    }

    //создание диалога с капчей
    private void createCaptchaDialog(String url) {
        CaptchaDialog.createCaptchaDialog(
                getContext(),
                getLayoutInflater(),
                url,
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

    //создание диалога с отзывами
    private void createReviewDialog() {
        ReviewDialogHelper.buildReview(
                getContext(),
                getLayoutInflater(),
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_news_category, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container_news, NewsFilterFragment.newInstance())
                        .commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        ((NavigationActivity) getActivity()).getSupportActionBar().show();
        super.onResume();
    }

    @Override
    public void onClick(News news) {

    }

    @Override
    public void onAddFriend(int position, int type, int userId) {
        viewModelFriendsRequest.addRequest(getContext(), initAddRequest(userId)).observe(getViewLifecycleOwner(), new Observer<FriendsRequestAddBody>() {
            @Override
            public void onChanged(FriendsRequestAddBody friendsRequestAddBody) {
                if (friendsRequestAddBody.getToken() != null) {
                    if (friendsRequestAddBody.getToken().equals("error")) {
                        startBaseActivity();
                    }
                }

                if (friendsRequestAddBody.getStatus().equals("success")) {

                    //проверка на капчу
                    if (friendsRequestAddBody.getCaptchaImageUrl() != null) {
                        createCaptchaDialog(friendsRequestAddBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if (friendsRequestAddBody.getPopup() != null) {
                        if (friendsRequestAddBody.getPopup().equals("comment")) {
                            createReviewDialog();
                        }
                    }

                    // удаление новости с другом
//                    for (Iterator<News> it = newsList.iterator(); it.hasNext();){
//                        News news = it.next();
//                        if(news.getUserId() == userId & type == 4){
//                            it.remove();
//                        }
//                    }
                    newsList.remove(position);

                    adapter.setList(newsList);

                    Toast.makeText(getContext(), "Пользователь добавлен в друзья", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), friendsRequestAddBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDelFriend(int position, int type, int userId) {
        viewModelFriendsRequest.delRequest(getContext(), initDelRequest(userId)).observe(getViewLifecycleOwner(), new Observer<FriendsRequestDelBody>() {
            @Override
            public void onChanged(FriendsRequestDelBody friendsRequestDelBody) {
                if (friendsRequestDelBody.getToken() != null) {
                    if (friendsRequestDelBody.getToken().equals("error")) {
                        startBaseActivity();
                    }
                }

                if (friendsRequestDelBody.getStatus().equals("success")) {

                    //проверка на капчу
                    if (friendsRequestDelBody.getCaptchaImageUrl() != null) {
                        createCaptchaDialog(friendsRequestDelBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if (friendsRequestDelBody.getPopup() != null) {
                        if (friendsRequestDelBody.getPopup().equals("comment")) {
                            createReviewDialog();
                        }
                    }

                    // удаление новости с другом
//                    for (Iterator<News> it = newsList.iterator(); it.hasNext();){
//                        News news = it.next();
//                        if(news.getUserId() == userId & type == 4){
//                            it.remove();
//                        }
//                    }


                    newsList.remove(position);
                    adapter.setList(newsList);

                    Toast.makeText(getContext(), "Заявка пользователя отклонена", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), friendsRequestDelBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onApplyGame(int position, int type, int gameId) {
        viewModelInviteGame.applyGame(getContext(), initApplyGame(gameId)).observe(getViewLifecycleOwner(), new Observer<InviteGameApplyBody>() {
            @Override
            public void onChanged(InviteGameApplyBody inviteGameApplyBody) {
                if (inviteGameApplyBody.getToken() != null) {
                    if (inviteGameApplyBody.getToken().equals("error")) {
                        startBaseActivity();
                    }
                }

                if (inviteGameApplyBody.getStatus().equals("success")) {

                    //проверка на капчу
                    if (inviteGameApplyBody.getCaptchaImageUrl() != null) {
                        createCaptchaDialog(inviteGameApplyBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if (inviteGameApplyBody.getPopup() != null) {
                        if (inviteGameApplyBody.getPopup().equals("comment")) {
                            createReviewDialog();
                        }
                    }

                    // удаление новости с игрой
//                    for (Iterator<News> it = newsList.iterator(); it.hasNext();){
//                        News news = it.next();
//                        if(news.getGameFriendsId() == gameId & type == 3){
//                            it.remove();
//                        }
//                    }

                    newsList.remove(position);

                    adapter.setList(newsList);

                    Toast.makeText(getContext(), "Иммитация начала игры", Toast.LENGTH_SHORT).show();
//                    updateUI();
                } else {
                    Toast.makeText(getContext(), inviteGameApplyBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCancelGame(int position, int type, int gameId) {
        viewModelInviteGame.cancelGame(getContext(), initCancelGame(gameId)).observe(getViewLifecycleOwner(), new Observer<InviteGameCancelBody>() {
            @Override
            public void onChanged(InviteGameCancelBody inviteGameCancelBody) {
                if (inviteGameCancelBody.getToken() != null) {
                    if (inviteGameCancelBody.getToken().equals("error")) {
                        startBaseActivity();
                    }
                }

                if (inviteGameCancelBody.getStatus().equals("success")) {

                    //проверка на капчу
                    if (inviteGameCancelBody.getCaptchaImageUrl() != null) {
                        createCaptchaDialog(inviteGameCancelBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if (inviteGameCancelBody.getPopup() != null) {
                        if (inviteGameCancelBody.getPopup().equals("comment")) {
                            createReviewDialog();
                        }
                    }

                    // удаление новости с игрой
//                    for (Iterator<News> it = newsList.iterator(); it.hasNext();){
//                        News news = it.next();
//                        if(news.getGameFriendsId() == gameId & type == 3){
//                            it.remove();
//                        }
//                    }

                    newsList.remove(position);

                    adapter.setList(newsList);

                    Toast.makeText(getContext(), "Иммитация отказа от игры", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), inviteGameCancelBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onTransferMonet() {

    }

    @Override
    public void onTransferMoney() {

    }

    private FriendsRequestRequest initMainRequest() {
        return new FriendsRequestRequest(
                "friends_request_add",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }

    private FriendsRequestDelRequest initDelRequest(int userId) {
        return new FriendsRequestDelRequest(
                "friends_request_add",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                userId
        );
    }

    private FriendsRequestAddRequest initAddRequest(int userId) {
        return new FriendsRequestAddRequest(
                "friends_request_add",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                userId
        );
    }

    private InviteGameApplyRequest initApplyGame(int gameId) {
        return new InviteGameApplyRequest(
                "action_invite_game_apply",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                gameId
        );
    }


    private InviteGameCancelRequest initCancelGame(int gameId) {
        return new InviteGameCancelRequest(
                "action_invite_game_cancel",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                gameId
        );
    }

}
