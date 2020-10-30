package com.example.blackjackgame.ui.adapter.tournament;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentTournamentContentPagerItemBinding;
import com.example.blackjackgame.databinding.FragmentTournamentPagerListItemBinding;
import com.example.blackjackgame.model.tournament.Tournament;

import java.util.ArrayList;
import java.util.List;

public class TournamentListAdapter extends RecyclerView.Adapter<TournamentListAdapter.ViewHolder> {

    private List<Tournament> tournaments = new ArrayList<>();
    private Context context;

    public TournamentListAdapter(List<Tournament> tournaments, Context context){
        this.tournaments = tournaments;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentTournamentPagerListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tournament_pager_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return tournaments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentTournamentPagerListItemBinding binding;
        private boolean isActiveUchastnik = false;
        private boolean isActiveFond = false;

        public ViewHolder(FragmentTournamentPagerListItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(){
            binding.imageView6.setOnClickListener(v -> {
                if(!isActiveUchastnik){
                    binding.imageView6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    isActiveUchastnik = !isActiveUchastnik;
                    binding.rvUchastnik.setVisibility(View.VISIBLE);
                } else {
                    binding.rvUchastnik.setVisibility(View.GONE);
                    binding.imageView6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    isActiveUchastnik = !isActiveUchastnik;
                }

                binding.rvUchastnik.setLayoutManager(new LinearLayoutManager(context));
                binding.rvUchastnik.setAdapter(new TournamentUschestnikAdapter());
            });

            binding.imageView16.setOnClickListener(v -> {
                if(!isActiveFond){
                    binding.imageView16.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    isActiveFond = !isActiveFond;
                    binding.rvFond.setVisibility(View.VISIBLE);
                } else {
                    binding.rvFond.setVisibility(View.GONE);
                    binding.imageView16.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    isActiveFond = !isActiveFond;
                }

                binding.rvFond.setLayoutManager(new LinearLayoutManager(context));
                binding.rvFond.setAdapter(new TournamentFondAdapter());
                binding.rvUchastnik.setLayoutManager(new LinearLayoutManager(context));
                binding.rvUchastnik.setAdapter(new TournamentUschestnikAdapter());
            });

            binding.textView22.setOnClickListener(v -> {
                if(!isActiveUchastnik){
                    binding.imageView6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    isActiveUchastnik = !isActiveUchastnik;
                    binding.rvUchastnik.setVisibility(View.VISIBLE);
                } else {
                    binding.rvUchastnik.setVisibility(View.GONE);
                    binding.imageView6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    isActiveUchastnik = !isActiveUchastnik;
                }

                binding.rvUchastnik.setLayoutManager(new LinearLayoutManager(context));
                binding.rvUchastnik.setAdapter(new TournamentUschestnikAdapter());
            });
            binding.textView38.setOnClickListener(v -> {
                if(!isActiveFond){
                    binding.imageView16.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    isActiveFond = !isActiveFond;
                    binding.rvFond.setVisibility(View.VISIBLE);
                } else {
                    binding.rvFond.setVisibility(View.GONE);
                    binding.imageView16.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    isActiveFond = !isActiveFond;
                }

                binding.rvFond.setLayoutManager(new LinearLayoutManager(context));
                binding.rvFond.setAdapter(new TournamentFondAdapter());
                binding.rvUchastnik.setLayoutManager(new LinearLayoutManager(context));
                binding.rvUchastnik.setAdapter(new TournamentUschestnikAdapter());
            });

        }

    }

}
