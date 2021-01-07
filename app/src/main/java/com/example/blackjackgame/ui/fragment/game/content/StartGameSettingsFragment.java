package com.example.blackjackgame.ui.fragment.game.content;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentStartGameContentBinding;
import com.example.blackjackgame.network.responce.game.GamePrivateUser;
import com.example.blackjackgame.rModel.friends.all.Friends;
import com.example.blackjackgame.rModel.friends.all.FriendsAllBody;
import com.example.blackjackgame.rModel.profileAny.ProfileAnyBody;
import com.example.blackjackgame.rModel.startGame.StartGameBody;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsAllRequest;
import com.example.blackjackgame.rNetwork.request.friends.all.FriendsSearchRequest;
import com.example.blackjackgame.rNetwork.request.profileAny.ProfileAnyRequest;
import com.example.blackjackgame.rNetwork.request.startGame.StartGameRequest;
import com.example.blackjackgame.rViewModel.friendsAll.FriendsAllFactory;
import com.example.blackjackgame.rViewModel.friendsAll.FriendsAllViewModel;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyFactory;
import com.example.blackjackgame.rViewModel.profileAny.ProfileAnyViewModel;
import com.example.blackjackgame.rViewModel.startGame.StartGameFactory;
import com.example.blackjackgame.rViewModel.startGame.StartGameViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.friend.FriendsAllAdapter;
import com.example.blackjackgame.ui.adapter.startGame.StartGameFriendsAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsContentAllFragment;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.ui.interfaceClick.startGame.FriendsOnClick;
import com.google.android.material.chip.ChipGroup;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class StartGameSettingsFragment extends Fragment implements FriendsOnClick {

    private FragmentStartGameContentBinding binding;
    private SharedPreferences sharedPreferences;
    private FriendsAllViewModel viewModelFriends;
    private FriendsAllRequest requestFriends;
    private StartGameViewModel viewModelGame;
    private StartGameRequest requestGame;

    private ProfileAnyViewModel profileAnyViewModel;

    private StartGameFriendsAdapter adapter;
    private List<Friends> friendsList = new ArrayList<>();
    private List<Integer> betList;
    private int countPlayer = 2;
    private int typeGame  = 0;

    private boolean privateGame = false;

    private List<GamePrivateUser> checkedUser;
    private StaggeredGridLayoutManager _sGridLayoutManager;

    public static StartGameSettingsFragment newInstance() {

        Bundle args = new Bundle();

        StartGameSettingsFragment fragment = new StartGameSettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_game_content, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        initRequestFriends();
        initRequestGame();
        viewModelFriends = new ViewModelProvider(getViewModelStore(), new FriendsAllFactory(requestFriends)).get(FriendsAllViewModel.class);
        viewModelGame = new ViewModelProvider(getViewModelStore(), new StartGameFactory(requestGame)).get(StartGameViewModel.class);

        checkedUser = new ArrayList<>();

        intiCountPlayer();
        hideFriends();

        binding.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                privateGame = isChecked;
                if(isChecked){
                    showFriends();
                } else {
                    hideFriends();
                }
            }
        });

        binding.chipOpen.setOnClickListener(v -> {
            binding.chipOpen.setBackgroundResource(R.drawable.border_edit);
//            binding.chipOpen.setTextColor(R.color.black);
            binding.chipTime.setBackgroundResource(R.drawable.border_edit_dark);
//            binding.chipTime.setTextColor(R.color.white);
            binding.chipWin.setBackgroundResource(R.drawable.border_edit_dark);
//            binding.chipWin.setTextColor(R.color.white);

            typeGame = 0;
        });


        binding.chipWin.setOnClickListener(v -> {
            binding.chipOpen.setBackgroundResource(R.drawable.border_edit_dark);
//            binding.chipOpen.setTextColor(R.color.white);
            binding.chipTime.setBackgroundResource(R.drawable.border_edit_dark);
//            binding.chipTime.setTextColor(R.color.white);
            binding.chipWin.setBackgroundResource(R.drawable.border_edit);
//            binding.chipWin.setTextColor(R.color.black);

            typeGame = 1;
        });

        binding.chipTime.setOnClickListener(v -> {
            binding.chipOpen.setBackgroundResource(R.drawable.border_edit_dark);
//            binding.chipOpen.setTextColor(R.color.white);
            binding.chipTime.setBackgroundResource(R.drawable.border_edit);
//            binding.chipTime.setTextColor(R.color.black);
            binding.chipWin.setBackgroundResource(R.drawable.border_edit_dark);
//            binding.chipWin.setTextColor(R.color.white);

            typeGame = 2;
        });

        binding.startGame.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container_start_game, GameWaitingFragment.newInstance())
                    .commit();
        });

        binding.setViewModel(viewModelGame);
        binding.setViewModelFriends(viewModelFriends);

        _sGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);

        binding.recyclerView.setLayoutManager(_sGridLayoutManager);

        adapter = new StartGameFriendsAdapter(StartGameSettingsFragment.this);
        binding.recyclerView.setAdapter(adapter);

        updateUI();
        initRequestFriends();
        viewModelFriends.update(requestFriends);
        updateFriendsList("");
        initSearchFriends();
        initSeekBar();

        return binding.getRoot();
    }

    private void hideFriends(){
        binding.etSearch.setVisibility(View.GONE);
        binding.search.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.GONE);
        binding.button5.setVisibility(View.GONE);
        binding.btn.setVisibility(View.GONE);
        binding.view6.setVisibility(View.GONE);
        binding.view61.setVisibility(View.GONE);
    }

    private void showFriends(){
        binding.etSearch.setVisibility(View.VISIBLE);
        binding.search.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.VISIBLE);
        binding.button5.setVisibility(View.VISIBLE);
        binding.btn.setVisibility(View.VISIBLE);
        binding.view6.setVisibility(View.VISIBLE);
        binding.view61.setVisibility(View.VISIBLE);
    }

    private void initSearchFriends(){
        binding.search.setOnClickListener(v -> {
            String search = binding.etSearch.getText().toString();
            if(friendsList != null){
                updateFriendsList(search);
            } else {
                Toast.makeText(getContext(), "Ошибка сервера, данные не получены\nПопробуйте еще раз. У тебя нет друзей", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(){
        viewModelGame.getLiveData().observe(getViewLifecycleOwner(), new Observer<StartGameBody>() {
            @Override
            public void onChanged(StartGameBody startGameBody) {
                if(startGameBody.getToken() != null){
                    if(startGameBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(startGameBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(startGameBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(startGameBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(startGameBody.getPopup() != null){
                        if(startGameBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    if(startGameBody.getGameSettings().getCoins() != null){
                        betList = startGameBody.getGameSettings().getCoins();
                        binding.seekBar2.setMax(betList.size() - 1);

                        binding.minMoney.setText(String.valueOf(betList.get(0)));
                        binding.maxMoney.setText(String.valueOf(betList.get(betList.size()-1)));
                    }

                } else {
                    Toast.makeText(getContext(), startGameBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateFriendsList(String search){
        viewModelFriends.getLiveData().observe(getViewLifecycleOwner(), new Observer<FriendsAllBody>() {
            @Override
            public void onChanged(FriendsAllBody friendsAllBody) {
                if(friendsAllBody.getToken() != null){
                    if(friendsAllBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(friendsAllBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(friendsAllBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(friendsAllBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(friendsAllBody.getPopup() != null){
                        if(friendsAllBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    List<Friends> currentFriends = new ArrayList<>();

                    if(friendsAllBody.getFriends() != null){
                        friendsList = friendsAllBody.getFriends();

                        if(friendsList != null)
                            for(Friends friend : friendsList){
                                if(friend.getNick().contains(search)){
                                    currentFriends.add(friend);
                                }
                            }

                        adapter.setFriends(currentFriends);
                    }

                } else {
                    Toast.makeText(getContext(), friendsAllBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initSeekBar(){

        binding.seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < betList.size()){
                    binding.cvTvCount.setText(String.valueOf(betList.get(progress)));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void intiCountPlayer(){

        binding.kol2.setOnClickListener(v -> {
            binding.kol2.setBackgroundResource(R.drawable.border_edit);
            binding.kol3.setBackgroundResource(R.drawable.border_edit_dark);
            binding.kol4.setBackgroundResource(R.drawable.border_edit_dark);
            binding.kol5.setBackgroundResource(R.drawable.border_edit_dark);
        });

        binding.kol3.setOnClickListener(v -> {
            binding.kol2.setBackgroundResource(R.drawable.border_edit_dark);
            binding.kol3.setBackgroundResource(R.drawable.border_edit);
            binding.kol4.setBackgroundResource(R.drawable.border_edit_dark);
            binding.kol5.setBackgroundResource(R.drawable.border_edit_dark);
        });

        binding.kol4.setOnClickListener(v -> {
            binding.kol2.setBackgroundResource(R.drawable.border_edit_dark);
            binding.kol3.setBackgroundResource(R.drawable.border_edit_dark);
            binding.kol4.setBackgroundResource(R.drawable.border_edit);
            binding.kol5.setBackgroundResource(R.drawable.border_edit_dark);
        });

        binding.kol5.setOnClickListener(v -> {
            binding.kol2.setBackgroundResource(R.drawable.border_edit_dark);
            binding.kol3.setBackgroundResource(R.drawable.border_edit_dark);
            binding.kol4.setBackgroundResource(R.drawable.border_edit_dark);
            binding.kol5.setBackgroundResource(R.drawable.border_edit);
        });
    }

    private void startBaseActivity(){
        startActivity(new Intent(getContext(), MainActivity.class));
        ((NavigationActivity)getActivity()).finish();
    }

    //создание диалога с капчей
    private void createCaptchaDialog(String url){
        CaptchaDialog.createCaptchaDialog(
                getContext(),
                getLayoutInflater(),
                url,
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

    //создание диалога с отзывами
    private void createReviewDialog(){
        ReviewDialogHelper.buildReview(
                getContext(),
                getLayoutInflater(),
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

    private void initRequestGame(){
        requestGame = new StartGameRequest(
                "game_setting",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }

    private void initRequestFriends(){
        requestFriends =  new FriendsAllRequest(
                "friends",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }


    @Override
    public void onClick(int id) {
        profileAnyViewModel = new ViewModelProvider(getViewModelStore(), new ProfileAnyFactory(request(id))).get(ProfileAnyViewModel.class);
        profileAnyViewModel.update(request(id));
        profileAnyViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<ProfileAnyBody>() {
            @Override
            public void onChanged(ProfileAnyBody profileAnyBody) {
                if(profileAnyBody.getToken() != null){
                    if(profileAnyBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(profileAnyBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(profileAnyBody.getCaptchaImageUrl() != null){
                        createCaptchaDialog(profileAnyBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if(profileAnyBody.getPopup() != null){
                        if(profileAnyBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    if(profileAnyBody.getProfileAny() != null){
                        String json = new Gson().toJson(profileAnyBody.getProfileAny());
                        UserInfoDialogFragment dialog = new UserInfoDialogFragment();
                        Bundle bundle = new Bundle();
                        dialog.setArguments(bundle);
                        bundle.putString("model", json);
                        bundle.putString("show", "true");
                        dialog.show(getFragmentManager(), "dialog");
                    }

                } else {
                    Toast.makeText(getContext(), profileAnyBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ProfileAnyRequest request(int userId){
        return new ProfileAnyRequest(
                "profile_any",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                userId
        );
    }

    @Override
    public void onCheckFriend(int id) {
        checkedUser.add(new GamePrivateUser(id));
    }

    @Override
    public void onUncheckFriend(int id) {
        for(int i = 0; i < checkedUser.size(); i++){
            if(checkedUser.get(i).getId() == id){
                checkedUser.remove(i);
            }
        }
    }
}
