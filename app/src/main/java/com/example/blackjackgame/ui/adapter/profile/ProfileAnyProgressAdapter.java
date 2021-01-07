package com.example.blackjackgame.ui.adapter.profile;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.LayoutProfileProgressItemBinding;
import com.example.blackjackgame.rModel.profileAny.Progress;
import com.example.blackjackgame.util.ConvertStringToImage;

import java.util.List;

public class ProfileAnyProgressAdapter extends RecyclerView.Adapter<ProfileAnyProgressAdapter.ViewHolder> {

    private List<Progress> progress;

    public ProfileAnyProgressAdapter(List<Progress> progress){
        this.progress = progress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutProfileProgressItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_profile_progress_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(progress.get(position));
    }

    @Override
    public int getItemCount() {
        return progress != null ? progress.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LayoutProfileProgressItemBinding binding;

        ViewHolder(LayoutProfileProgressItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Progress progress){
            ConvertStringToImage.convert(binding.item, progress.getIcon()
            );
            binding.amount.setText(String.valueOf(progress.getAmount()));
        }

    }

}
