package com.example.blackjackgame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.ActivityTournamentListBinding;
import com.example.blackjackgame.model.tournament.TournamentBody;
import com.example.blackjackgame.network.responce.tournament.TournamentListRequest;
import com.example.blackjackgame.ui.adapter.tournament.TournamentListAdapter;
import com.example.blackjackgame.viewmodel.tournament.TournamentFactory;
import com.example.blackjackgame.viewmodel.tournament.TournamentViewModel;

public class TournamentListActivity extends AppCompatActivity {

    private ActivityTournamentListBinding binding;
    private TournamentViewModel viewModel;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tournament_list);

        viewModel = new ViewModelProvider(this, new TournamentFactory(getApplication())).get(TournamentViewModel.class);

        sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);

        TournamentListRequest request = new TournamentListRequest(
                "tournament_menu",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "null"),
                1,
                1
        );

        binding.toolbar4.setNavigationOnClickListener(v -> {
            finish();
        });

        viewModel.getTournaments(request).observe(this, new Observer<TournamentBody>() {
            @Override
            public void onChanged(TournamentBody tournamentBody) {
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                binding.recyclerView.setAdapter(new TournamentListAdapter(tournamentBody.getTournament_menu(), getBaseContext()));
            }
        });
    }
}