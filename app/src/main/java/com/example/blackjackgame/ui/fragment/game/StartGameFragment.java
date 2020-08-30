package com.example.blackjackgame.ui.fragment.game;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentStartGameBinding;
import com.example.blackjackgame.ui.adapter.startGame.StartGameFriendsAdapter;
import com.example.blackjackgame.ui.fragment.game.content.GameWaitingFragment;
import com.example.blackjackgame.ui.fragment.game.content.StartGameContentFragment;

public class StartGameFragment extends Fragment {

    private FragmentStartGameBinding binding;
    private SharedPreferences sharedPreferences;
    private int typeAds;

    public static StartGameFragment newInstance() {

        Bundle args = new Bundle();

        StartGameFragment fragment = new StartGameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_game, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        typeAds = sharedPreferences.getInt(Constant.typeAdsForLoadGame, 0);
        getFragmentManager().beginTransaction()
                .add(R.id.container_start_game, GameWaitingFragment.newInstance())
                .commit();



        return binding.getRoot();
    }
}
