package com.example.blackjackgame.ui.fragment.game.content;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentStartGameContentBinding;
import com.example.blackjackgame.model.friend.Friend;
import com.example.blackjackgame.model.friend.FriendBody;
import com.example.blackjackgame.network.responce.friend.FriendRequest;
import com.example.blackjackgame.network.responce.friend.FriendSearchRequest;
import com.example.blackjackgame.network.responce.friend.FriendsZaprosRequest;
import com.example.blackjackgame.network.responce.game.GamePrivateUser;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.adapter.startGame.StartGameFriendsAdapter;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.startGame.FriendsOnClick;
import com.example.blackjackgame.viewmodel.friend.FriendsFactory;
import com.example.blackjackgame.viewmodel.friend.FriendsViewModel;

import java.util.ArrayList;
import java.util.List;

public class StartGameContentFragment extends Fragment implements FriendsOnClick {

    private FragmentStartGameContentBinding binding;
    private FriendsViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private StartGameFriendsAdapter adapter;
    private List<Friend> friends;
    private List<Friend> currentFriends;

    private boolean isCheckedAllFriends = false;

    private List<GamePrivateUser> checkedUsers = new ArrayList<>();

    public static StartGameContentFragment newInstance() {

        Bundle args = new Bundle();

        StartGameContentFragment fragment = new StartGameContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_game_content, container, false);

        viewModel = new ViewModelProvider(this, new FriendsFactory(getActivity().getApplication())).get(FriendsViewModel.class);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        FriendRequest request = new FriendRequest(
                "friends",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "null")
        );



        //заполнение информации с друзьями
        viewModel.getFriends(request).observe(getViewLifecycleOwner(), new Observer<FriendBody>() {
            @Override
            public void onChanged(FriendBody friendBody) {
                if(friendBody.getStatus().equals(Constant.success)){

                    friends = friendBody.getFriends();
                    adapter = new StartGameFriendsAdapter(friends, StartGameContentFragment.this);

                    binding.friend.recyclerViewMyFriend.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.friend.recyclerViewMyFriend.setAdapter(adapter);



                } else {
                    if(friendBody.getError_text().equals(Constant.failed_token)){
                        failedToken();
                    }
                    //TODO: обработать ошибку с сервера
                }
            }
        });

        binding.friend.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(adapter != null){
                    if(!isCheckedAllFriends){
                        adapter.selectAll();
                        binding.friend.materialButton.setText("Отменить все");
                    } else {
                        adapter.unselectall();
                        binding.friend.materialButton.setText("Выбрать всех");
                    }

                    isCheckedAllFriends = !isCheckedAllFriends;

                }

            }
        });

        //поиск друзей
        binding.friend.btnSearch.setOnClickListener(v -> {

            String search = binding.friend.search.getText().toString();

            currentFriends = new ArrayList<>();

            //заполнение информации с друзьями
            viewModel.getFriends(request).observe(getViewLifecycleOwner(), new Observer<FriendBody>() {
                @Override
                public void onChanged(FriendBody friendBody) {
                    if(friendBody.getStatus().equals(Constant.success)){

                        //выборка по имени
                        for(Friend friend : friendBody.getFriends()){
                            if(friend.getNick().contains(search)){
                                currentFriends.add(friend);
                            }
                        }

                        adapter = new StartGameFriendsAdapter(currentFriends, StartGameContentFragment.this);

                        binding.friend.recyclerViewMyFriend.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.friend.recyclerViewMyFriend.setAdapter(adapter);

                    } else {
                        if(friendBody.getError_text().equals(Constant.failed_token)){
                            failedToken();
                        }
                        //TODO: обработать ошибку с сервера
                    }
                }
            });
        });

        //проверка, игра приватная или нет
        binding.typeGame.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b){
//                    binding.typeGame.startGame.setVisibility(View.GONE);
                    binding.clFriend.setVisibility(View.GONE);
                } else {
//                    binding.typeGame.startGame.setVisibility(View.VISIBLE);
                    binding.clFriend.setVisibility(View.VISIBLE);
                }
            }
        });

        return binding.getRoot();
    }

    private void failedToken(){
        sharedPreferences.edit().putBoolean(Constant.isSign, false).apply();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(int id) {
        UserInfoDialogFragment dialog = new UserInfoDialogFragment();

        Bundle args = new Bundle();
        args.putInt("id", id);
        dialog.setArguments(args);

        dialog.show(getFragmentManager(), "dialog");
    }

    @Override
    public void onCheckFriend(int id) {
        checkedUsers.add(new GamePrivateUser(id));
    }

    @Override
    public void onUncheckFriend(int id) {

        for(int i = 0; i < checkedUsers.size(); i++){
            if(checkedUsers.get(i).getId() == id){
                checkedUsers.remove(i);
            }
        }
    }
}
