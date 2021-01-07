package com.example.blackjackgame.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.CheckboxItemBinding;
import com.example.blackjackgame.rModel.coins.MoneyTransfer;
import com.example.blackjackgame.ui.interfaceClick.CheckClick;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.ViewHodler> {

    List<MoneyTransfer> list = new ArrayList<>();
    CheckClick listener;
    CheckBox last;
    MoneyTransfer lastModel;

    public CheckBoxAdapter(CheckClick listener){
        this.listener = listener;
    }

    public void setList(List<MoneyTransfer> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CheckboxItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.checkbox_item, parent, false);
        return new ViewHodler(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{

        private CheckboxItemBinding binding;

        public ViewHodler(CheckboxItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(MoneyTransfer model){
            binding.text.setText(model.getBank());

            binding.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        if(last != null){
                            listener.onClick(true, last, binding.check, model);
                            last = binding.check;
                            lastModel = model;
                        } else {
                            last = binding.check;
                            lastModel = model;
                            listener.onClick(false, null, last, model);
                        }

//                        binding.check.setEnabled(false);

                    }
                }
            });
        }
    }

}
