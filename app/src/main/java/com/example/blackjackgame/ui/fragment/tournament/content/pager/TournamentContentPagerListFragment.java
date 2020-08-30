package com.example.blackjackgame.ui.fragment.tournament.content.pager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentTournamentContentPagerListBinding;
import com.example.blackjackgame.model.tournament.Tournament;
import com.example.blackjackgame.model.tournament.TournamentBody;
import com.example.blackjackgame.network.responce.tournament.TournamentListRequest;
import com.example.blackjackgame.ui.adapter.tournament.TournamentListAdapter;
import com.example.blackjackgame.viewmodel.tournament.TournamentFactory;
import com.example.blackjackgame.viewmodel.tournament.TournamentViewModel;

public class TournamentContentPagerListFragment extends Fragment {

    private FragmentTournamentContentPagerListBinding binding;
    private TournamentViewModel viewModel;
    private SharedPreferences sharedPreferences;

    public static TournamentContentPagerListFragment newInstance() {

        Bundle args = new Bundle();

        TournamentContentPagerListFragment fragment = new TournamentContentPagerListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tournament_content_pager_list, container, false);

        viewModel = new ViewModelProvider(this, new TournamentFactory(getActivity().getApplication())).get(TournamentViewModel.class);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        TournamentListRequest request = new TournamentListRequest(
                "tournament_menu",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "null"),
                1,
                1
        );

        viewModel.getTournaments(request).observe(getViewLifecycleOwner(), new Observer<TournamentBody>() {
            @Override
            public void onChanged(TournamentBody tournamentBody) {
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerView.setAdapter(new TournamentListAdapter(tournamentBody.getTournament_menu(), getContext()));
            }
        });

        return binding.getRoot();
    }
}
