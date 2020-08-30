package com.example.blackjackgame.ui.fragment.tournament.content.pager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentTournamentContentPagerBinding;
import com.example.blackjackgame.ui.activity.TournamentListActivity;

public class TournamentContentPagerFragment extends Fragment {

    private FragmentTournamentContentPagerBinding binding;
    private SharedPreferences sharedPreferences;

    public static TournamentContentPagerFragment newInstance() {

        Bundle args = new Bundle();

        TournamentContentPagerFragment fragment = new TournamentContentPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tournament_content_pager, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        binding.silver.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), TournamentListActivity.class);
            startActivity(intent);


        });

        return binding.getRoot();
    }
}
