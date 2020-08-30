package com.example.blackjackgame.ui.adapter.tournament;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentTournamentContentPagerItemBinding;
import com.example.blackjackgame.databinding.FragmentTournamentContentPagerItemFondItemBinding;

public class TournamentFondAdapter extends RecyclerView.Adapter<TournamentFondAdapter.ViewHolder> {

    public TournamentFondAdapter(){}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentTournamentContentPagerItemFondItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tournament_content_pager_item_fond_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentTournamentContentPagerItemFondItemBinding binding;

        ViewHolder(FragmentTournamentContentPagerItemFondItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
