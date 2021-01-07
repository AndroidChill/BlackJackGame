package com.example.blackjackgame.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.AvatarChangeItemBinding;
import com.example.blackjackgame.rModel.Avatar;
import com.example.blackjackgame.ui.interfaceClick.ChangeAvatarClick;
import com.example.blackjackgame.util.ConvertStringToImage;

import java.util.ArrayList;
import java.util.List;

public class AvatarChangeAdapter extends RecyclerView.Adapter<AvatarChangeAdapter.ViewHolder> {

    private List<Avatar> avatars = new ArrayList<>();
    private int positionSelect = 0;
    private Context context;
    private TextView textView;
    private ChangeAvatarClick listener;

    public AvatarChangeAdapter(Context context, TextView textView, ChangeAvatarClick listener) {
        this.context = context;
        this.textView = textView;
        this.listener = listener;
    }

    public AvatarChangeAdapter(Context context, TextView textView){
        this.context = context;
        this.textView = textView;
    }

    public void setAvatars(List<Avatar> avatars) {
        this.avatars = avatars;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AvatarChangeItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.avatar_change_item, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(positionSelect != position){
            holder.binding.layout3.setForeground(context.getDrawable(R.color.dark_back));
        } else{
            holder.binding.layout3.setForeground(context.getDrawable(R.color.white_back));
        }
        holder.binding.layout3.setOnClickListener(v -> {
            if (!listener.onClick(avatars.get(position))){
                Toast.makeText(context, "Недостаточно монет", Toast.LENGTH_SHORT).show();
            } else {
                positionSelect = position;
                textView.setText(String.valueOf(avatars.get(position).getCoast()));
                notifyDataSetChanged();
            }

        });

        holder.bind(avatars.get(position));
    }

    @Override
    public int getItemCount() {
        return avatars.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private AvatarChangeItemBinding binding;

        public ViewHolder(AvatarChangeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Avatar avatar){
            ConvertStringToImage.convert(binding.avatar3, avatar.getImage());
            binding.coins.setText(String.valueOf(avatar.getCoast()));
        }
    }

}
