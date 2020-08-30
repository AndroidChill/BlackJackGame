package com.example.blackjackgame.ui.adapter.rating;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentRatingContentItemBinding;
import com.example.blackjackgame.model.rating.RatingUserList;
import com.example.blackjackgame.model.rating.ratingRich.RatingRich;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.rating.RatingItemOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;

import java.util.ArrayList;
import java.util.List;

public class RatingRichAdapter extends RecyclerView.Adapter<RatingRichAdapter.ViewHolder> {

    private List<RatingRich> list = new ArrayList<>();
    private RatingItemOnClick listener;

    public RatingRichAdapter(List<RatingRich> list, RatingItemOnClick listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentRatingContentItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rating_content_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position), position + 1);
        holder.itemView.setOnClickListener(v -> listener.onCLick(list.get(position).getUser_id()));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private FragmentRatingContentItemBinding binding;

        ViewHolder(FragmentRatingContentItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(RatingRich rating, int position){

            ConvertStringToImage.convert(binding.imageView4, rating.getAvatar());

            binding.name.setText(rating.getNick());
            binding.money.setText(String.valueOf(rating.getCoins()));
            binding.number.setText(String.valueOf(position));
        }

    }

}
