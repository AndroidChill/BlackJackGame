package com.example.blackjackgame.ui.adapter.friend;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentFriendsContentItemBinding;
import com.example.blackjackgame.model.friend.Friend;
import com.example.blackjackgame.model.friend.referrals.Referrals;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;

import java.util.ArrayList;
import java.util.List;

public class FriendsReferralsAdapter extends RecyclerView.Adapter<FriendsReferralsAdapter.ViewHolder> {

    private List<Referrals> list = new ArrayList<>();
    private MyFriendOnClick listener;

    public FriendsReferralsAdapter(List<Referrals> friends, MyFriendOnClick listener){
        this.list = friends;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentFriendsContentItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_content_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        holder.binding.cardView.setOnClickListener(v -> listener.onClick(list.get(position).getNick()));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentFriendsContentItemBinding binding;

        ViewHolder(FragmentFriendsContentItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Referrals referrals){
            ConvertStringToImage.convert(binding.image, referrals.getUser_avatar());
            binding.nick.setText(referrals.getNick());
        }

    }

}
