package com.example.blackjackgame.ui.adapter.friend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentFriendsRequestItemBinding;
import com.example.blackjackgame.model.friend.request.output.FriendsZapros;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.friend.FriendRequestOnClick;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.example.blackjackgame.viewmodel.friend.FriendsViewModel;

import java.util.List;

public class FriendsRequestAdapter extends RecyclerView.Adapter<FriendsRequestAdapter.ViewHolder> {

    private List<FriendsZapros> list;
    private FriendRequestOnClick listener;
    private FragmentManager fragmentManager;

    public FriendsRequestAdapter(List<FriendsZapros> list, FriendRequestOnClick listener, FragmentManager fragmentManager){
        this.list = list;
        this.listener = listener;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentFriendsRequestItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_request_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        holder.binding.add.setOnClickListener(v -> listener.onClick("add", (list.get(position).getId())));
        holder.binding.close.setOnClickListener(v -> listener.onClick("close", (list.get(position).getId())));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentFriendsRequestItemBinding binding;

        ViewHolder(FragmentFriendsRequestItemBinding binding){
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(FriendsZapros request){

            binding.circleImageView.setOnClickListener(v -> {
                Bundle args = new Bundle();
                args.putInt("id", (int)request.getId());

                UserInfoDialogFragment dialog = new UserInfoDialogFragment();
                dialog.setArguments(args);
                dialog.show(fragmentManager, "dialog");
            });

            binding.nickname.setOnClickListener(v -> {
                Bundle args = new Bundle();
                args.putInt("id", (int)request.getId());

                UserInfoDialogFragment dialog = new UserInfoDialogFragment();
                dialog.setArguments(args);
                dialog.show(fragmentManager, "dialog");
            });

            binding.nickname.setText(request.getNick());
            ConvertStringToImage.convert(binding.circleImageView, request.getUser_avatar());
        }

    }

}
