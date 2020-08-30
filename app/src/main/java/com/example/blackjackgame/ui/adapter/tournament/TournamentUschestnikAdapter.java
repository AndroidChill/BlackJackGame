package com.example.blackjackgame.ui.adapter.tournament;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentTournamentContentPagerItemUchastnikItemBinding;

public class TournamentUschestnikAdapter extends RecyclerView.Adapter<TournamentUschestnikAdapter.ViewHolder> {

    public TournamentUschestnikAdapter(){}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentTournamentContentPagerItemUchastnikItemBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_tournament_content_pager_item_uchastnik_item,
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private FragmentTournamentContentPagerItemUchastnikItemBinding binding;

        public ViewHolder(FragmentTournamentContentPagerItemUchastnikItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
