package com.example.blackjackgame.ui.adapter.rating;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentRatingContentItemBinding;
import com.example.blackjackgame.model.rating.RatingUserList;
import com.example.blackjackgame.model.rating.ratingLuck.RatingUserItem;
import com.example.blackjackgame.rModel.ratingLucky.RatingOtherUser;
import com.example.blackjackgame.rModel.ratingLucky.RatingUser;
import com.example.blackjackgame.ui.interfaceClick.rating.RatingItemOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;

import java.util.List;

public class RatingLuckAdapter extends RecyclerView.Adapter<RatingLuckAdapter.ViewHolder>{

    private List<RatingOtherUser> list;
    private RatingItemOnClick listener;

    public RatingLuckAdapter(List<RatingOtherUser> list, RatingItemOnClick listener){
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
//        holder.bind(null, position + 1);
        holder.itemView.setOnClickListener(v -> listener.onCLick(list.get(position).getId()));
    }

    @Override
    public int getItemCount() {
//        return list.size();
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private FragmentRatingContentItemBinding binding;

        ViewHolder(FragmentRatingContentItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(RatingOtherUser rating, int position){
//
            ConvertStringToImage.convert(binding.imageView4, rating.getAvatar());

            binding.imageView7.setImageResource(R.drawable.money);

            binding.name.setText(rating.getNick());
            binding.money.setText(String.valueOf(rating.getRating()));
            binding.number.setText(String.valueOf(position));
        }

    }

}
