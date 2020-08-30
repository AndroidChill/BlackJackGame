package com.example.blackjackgame.ui.fragment.tournament;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentTournamentBinding;
import com.example.blackjackgame.ui.fragment.tournament.content.TournamentContentFragment;
import com.example.blackjackgame.ui.fragment.tournament.content.pager.TournamentContentPagerListFragment;

public class TournamentFragment extends Fragment {

    private FragmentTournamentBinding binding;
    private SharedPreferences sharedPreferences;

    public static TournamentFragment newInstance() {

        Bundle args = new Bundle();

        TournamentFragment fragment = new TournamentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tournament, container, false);

        setHasOptionsMenu(true);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        getFragmentManager().beginTransaction()
                .add(R.id.container_tournament, TournamentContentFragment.newInstance())
                .commit();


        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }
}
