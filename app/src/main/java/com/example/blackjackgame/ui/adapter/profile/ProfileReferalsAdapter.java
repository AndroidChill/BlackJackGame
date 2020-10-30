package com.example.blackjackgame.ui.adapter.profile;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.ReferralLink;
import com.example.blackjackgame.databinding.FragmentProfileInfoRefBinding;
import com.example.blackjackgame.model.profile.Ref;

import java.util.ArrayList;
import java.util.List;

public class ProfileReferalsAdapter extends RecyclerView.Adapter<ProfileReferalsAdapter.ViewHolder> {

    private List<Ref> list = new ArrayList<>();
    private Context context;

    public ProfileReferalsAdapter(Context context){
        this.context = context;
    }

    public ProfileReferalsAdapter(List<Ref> list, Context context){
        this.list = list;
        this.context = context;
    }

    public void setList(List<Ref> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentProfileInfoRefBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_info_ref, parent, false);



        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String present = "Я выбрал тебя для участия в этой игре !!!\n" + list.get(position).getRef_url() + "\nНо тебе придется выполнить некоторые задания...";

        holder.binding.tvDesc.setText(list.get(position).getRef_text());

        holder.binding.ivShare.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, present);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            context.startActivity(shareIntent);
        });

        holder.binding.ivCopy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", list.get(position).getRef_url());
            clipboard.setPrimaryClip(clip);

            Toast.makeText(context, "Реферальная ссылка скопирована", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private FragmentProfileInfoRefBinding binding;

        public ViewHolder(FragmentProfileInfoRefBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }

}
