package com.example.blackjackgame.ui.adapter.friend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentFriendsContentItemBinding;
import com.example.blackjackgame.model.friend.Friend;
import com.example.blackjackgame.model.friend.referrals.Referrals;
import com.example.blackjackgame.rModel.friends.referrals.Friends;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;

import java.util.ArrayList;
import java.util.List;

public class FriendsReferralsAdapter extends RecyclerView.Adapter<FriendsReferralsAdapter.ViewHolder> {

    private List<Friends> list = new ArrayList<>();
    private MyFriendOnClick listener;

    public FriendsReferralsAdapter(List<Friends> friends, MyFriendOnClick listener){
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
        holder.binding.main.setOnClickListener(v -> listener.onClick(list.get(position).getId()));
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

        void bind(Friends referrals){
            binding.cancelRequest.setVisibility(View.GONE);
            ConvertStringToImage.convert(binding.image, referrals.getAvatar());
            binding.nick.setText(referrals.getNick());
        }

    }

}
