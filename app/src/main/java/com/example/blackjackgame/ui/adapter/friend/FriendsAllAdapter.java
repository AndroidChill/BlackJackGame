package com.example.blackjackgame.ui.adapter.friend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentFriendsContentItemBinding;
import com.example.blackjackgame.rModel.friends.all.Friends;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;

import java.util.List;

public class FriendsAllAdapter extends RecyclerView.Adapter<FriendsAllAdapter.ViewHolder> {

    private List<Friends> friends;
    private MyFriendOnClick listener;

    public FriendsAllAdapter(List<Friends> friends, MyFriendOnClick listener){
        this.friends = friends;
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
        holder.bind(friends.get(position));
    }

    @Override
    public int getItemCount() {
        return friends != null ? friends.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private FragmentFriendsContentItemBinding binding;

        ViewHolder(FragmentFriendsContentItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Friends friend){
            binding.cancelRequest.setVisibility(View.GONE);
            ConvertStringToImage.convert(binding.image, friend.getAvatar());
            binding.nick.setText(friend.getNick());

            binding.main.setOnClickListener(v -> listener.onClick(friend.getId()));
        }

    }

}
