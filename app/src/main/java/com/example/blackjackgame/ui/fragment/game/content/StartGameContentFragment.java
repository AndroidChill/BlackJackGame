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
import com.example.blackjackgame.ui.activity.Game2Activity;
import com.example.blackjackgame.ui.activity.Game3Activity;
import com.example.blackjackgame.ui.activity.Game4Activity;
import com.example.blackjackgame.ui.activity.GameFiveActivity;
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
    private int countPlayer = 5;
    private int idCountStavka = 0;
    private int[] countStavka = { 100, 200, 400, 800, 1200, 2400, 4000, 8000, 15000, 20000, 25000, 50000, 600000, 1000000};

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






        return binding.getRoot();
    }

    private void checkPrivate(){
        //проверка, игра приватная или нет
//        binding.typeGame.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(!b){
////                    binding.typeGame.startGame.setVisibility(View.GONE);
//                    binding.clFriend.setVisibility(View.GONE);
//                } else {
////                    binding.typeGame.startGame.setVisibility(View.VISIBLE);
//                    binding.clFriend.setVisibility(View.VISIBLE);
//                }
//            }
//        });
    }

    private void initSeekbar(){
//        binding.countPlayer.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                countPlayer = progress + 2;
//                Log.d("player", "onProgressChanged: " + progress);
//                binding.countPlayer.tvCount.setText(countPlayer + "/5");
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//
//        binding.countStavka.seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                binding.countStavka.tvCount.setText(countStavka[progress] + "/" + 1000000);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
    }

    private void choiceTypeGame(){
//        binding.typeGame.startGame.setOnClickListener(v -> {
//            switch(countPlayer){
//                case 2:
//                    Intent intent2 = new Intent(getContext(), Game2Activity.class);
//                    startActivity(intent2);
//                    break;
//                case 3:
//                    Intent intent3 = new Intent(getContext(), Game3Activity.class);
//                    startActivity(intent3);
//                    break;
//                case 4:
//                    Intent intent4 = new Intent(getContext(), Game4Activity.class);
//                    startActivity(intent4);
//                    break;
//                case 5:
//                    Intent intent5 = new Intent(getContext(), GameFiveActivity.class);
//                    startActivity(intent5);
//                    break;
//            }
//        });
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
