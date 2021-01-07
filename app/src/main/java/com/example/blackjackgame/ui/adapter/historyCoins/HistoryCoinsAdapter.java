package com.example.blackjackgame.ui.adapter.historyCoins;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentHistoryCoinsBinding;
import com.example.blackjackgame.databinding.FragmentHistoryCoinsItemBinding;
import com.example.blackjackgame.rModel.historyCoins.HistoryCoins;
import com.example.blackjackgame.ui.interfaceClick.CoinsClick;

import java.util.ArrayList;
import java.util.List;

public class HistoryCoinsAdapter extends RecyclerView.Adapter<HistoryCoinsAdapter.ViewHolder> {

    List<HistoryCoins> list = new ArrayList<>();
    CoinsClick listener;


    public HistoryCoinsAdapter(CoinsClick listener){
        this.listener = listener;
    }

    public HistoryCoinsAdapter(List<HistoryCoins> list) {
        this.list = list;
    }

    public void setList(List<HistoryCoins> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentHistoryCoinsItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history_coins_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentHistoryCoinsItemBinding binding;

        public ViewHolder(FragmentHistoryCoinsItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(HistoryCoins item){
            binding.setModel(item);

            binding.main.setOnClickListener(v -> {
                listener.onClick(item.getId());
            });

            if(item.getType() != 1){
                if(item.getCoins() > 0){
                    binding.ivCount2.setVisibility(View.GONE);
                    binding.count2.setVisibility(View.GONE);
                    binding.ivCount.setImageResource(R.drawable.money);
                    binding.count.setText(String.valueOf(item.getCoins()));
                } else {
                    if(item.getMoney() > 0){
                        binding.ivCount.setImageResource(R.drawable.ic_rubl_1);
                        binding.count.setText(String.valueOf(item.getMoney()));
                    }
                }
            } else {
                binding.count.setText(String.valueOf(item.getMoney()));
                binding.count2.setText(String.valueOf(item.getCoins()));
            }



        }

    }

}
