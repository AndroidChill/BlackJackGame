package com.example.blackjackgame.ui.adapter.friend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentFriendsContentItemBinding;
import com.example.blackjackgame.databinding.FragmentFriendsRequestItemBinding;
import com.example.blackjackgame.model.friend.request.output.FriendsZapros;
import com.example.blackjackgame.rModel.friends.request.Friends;
import com.example.blackjackgame.rNetwork.request.friends.request.FriendsRequestDelRequest;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.friend.FriendRequestOnClick;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.example.blackjackgame.viewmodel.friend.FriendsViewModel;

import java.util.ArrayList;
import java.util.List;

public class FriendsRequestAdapter extends RecyclerView.Adapter<FriendsRequestAdapter.ViewHolder> {

    private List<Friends> list;
    private FriendRequestOnClick listener;
    private FragmentManager fragmentManager;

    public FriendsRequestAdapter(FriendRequestOnClick listener, FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
        this.listener = listener;
        list = new ArrayList<>();
    }

    public void setList(List<Friends> list) {
        this.list = list;
        notifyDataSetChanged();
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

        holder.binding.main.setOnClickListener(v -> listener.showUser(list.get(position).getId()));
        holder.binding.done.setOnClickListener(v -> listener.onClick("add", (list.get(position).getId())));
        holder.binding.cancel.setOnClickListener(v -> listener.onClick("close", (list.get(position).getId())));
        holder.binding.cancelRequest.setOnClickListener(v -> listener.onClick("cancel", (list.get(position).getId())));
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

        void bind(Friends request){

            if(request.getType() == 0){
                binding.done.setVisibility(View.VISIBLE);
                binding.cancel.setVisibility(View.VISIBLE);
                binding.cancelRequest.setVisibility(View.GONE);

            } else {
                binding.done.setVisibility(View.GONE);
                binding.cancel.setVisibility(View.GONE);
                binding.cancelRequest.setVisibility(View.VISIBLE);
            }

            binding.nick.setText(request.getNick());
            ConvertStringToImage.convert(binding.image, request.getAvatar());

        }

    }

}
