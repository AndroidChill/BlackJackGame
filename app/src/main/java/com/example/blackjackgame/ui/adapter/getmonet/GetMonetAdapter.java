package com.example.blackjackgame.ui.adapter.getmonet;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentGetMonetContentItemBinding;
import com.example.blackjackgame.rModel.coinsGet.CoinsGet;
import com.example.blackjackgame.ui.interfaceClick.getmonet.GetMonetOnClick;

import java.util.ArrayList;
import java.util.List;

public class GetMonetAdapter extends RecyclerView.Adapter<GetMonetAdapter.ViewHolder> {

    private List<CoinsGet> list = new ArrayList<>();
    private GetMonetOnClick listener;

    public GetMonetAdapter(List<CoinsGet> list, GetMonetOnClick listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentGetMonetContentItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_monet_content_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        holder.itemView.setOnClickListener(v -> listener.onClick(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private FragmentGetMonetContentItemBinding binding;

        ViewHolder(FragmentGetMonetContentItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CoinsGet task){
            binding.textView32.setText(task.getText());
            binding.textView34.setText(String.valueOf(task.getCoins()));
        }

    }

}
