package com.example.blackjackgame.ui.adapter.startGame;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentFriendsContentItemBinding;
import com.example.blackjackgame.databinding.FragmentStartGameContentItemBinding;
import com.example.blackjackgame.model.friend.Friend;
import com.example.blackjackgame.rModel.friends.all.Friends;
import com.example.blackjackgame.ui.interfaceClick.startGame.FriendsOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;

import java.util.ArrayList;
import java.util.List;

public class StartGameFriendsAdapter extends RecyclerView.Adapter<StartGameFriendsAdapter.ViewHolder> {

    private List<Friends> friends;
    private FriendsOnClick listener;
    private boolean isSelected = false;

    public StartGameFriendsAdapter(List<Friends> friends, FriendsOnClick listener){
        this.friends = friends;
        this.listener = listener;
    }

    public StartGameFriendsAdapter(FriendsOnClick listener) {
        this.listener = listener;
    }

    public void setFriends(List<Friends> friends){
        this.friends = friends;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentStartGameContentItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_game_content_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(friends.get(position));
        holder.itemView.setOnClickListener(v -> listener.onClick(friends.get(position).getId()));

        holder.binding.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    listener.onCheckFriend(friends.get(position).getId());
                } else {
                    listener.onUncheckFriend(friends.get(position).getId());
                }

            }
        });

        if (!isSelected){
            holder.binding.check.setChecked(false);
        }
        else {
            holder.binding.check.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return friends != null ? friends.size() : 0 ;
    }

    public void allChecked(){

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private FragmentStartGameContentItemBinding binding;

        ViewHolder(FragmentStartGameContentItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Friends friend){
            binding.nick.setText(friend.getNick());
            ConvertStringToImage.convert(binding.image, friend.getAvatar());
        }

        void allCheck(){
            binding.check.setChecked(true);
        }

    }

}
