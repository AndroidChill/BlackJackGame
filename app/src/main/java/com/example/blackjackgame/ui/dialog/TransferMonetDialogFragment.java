package com.example.blackjackgame.ui.dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.DialogTransferMonetBinding;
import com.example.blackjackgame.model.friend.getMonet.GiveMonetBody;
import com.example.blackjackgame.network.responce.friend.GiveMonetRequest;
import com.example.blackjackgame.viewmodel.profile.ProfileFactory;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;

public class TransferMonetDialogFragment extends DialogFragment {

    private DialogTransferMonetBinding binding;
    private ProfileViewModel viewModel;
    private SharedPreferences sharedPreferences;

    private int itogo = 0;

    public static TransferMonetDialogFragment newInstance() {

        Bundle args = new Bundle();

        TransferMonetDialogFragment fragment = new TransferMonetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_transfer_monet, container, false);

        viewModel = new ViewModelProvider(this, new ProfileFactory(getActivity().getApplication())).get(ProfileViewModel.class);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        onClickButton();

        binding.btnTransfer.setOnClickListener(v -> {
            int money = Integer.parseInt(binding.tvCount.getText().toString());
            if(money == 0){
                Toast.makeText(getContext(), "Введите число монет", Toast.LENGTH_SHORT).show();
            } else {
                GiveMonetRequest giveMonetRequest = new GiveMonetRequest(
                        "coins_transfer",
                        Constant.app_ver,
                        Constant.ln,
                        sharedPreferences.getString("token", "null"),
                        getArguments().getInt("id"),
                        money
                );

                viewModel.giveMonet(giveMonetRequest).observe(getViewLifecycleOwner(), new Observer<GiveMonetBody>() {
                    @Override
                    public void onChanged(GiveMonetBody giveMonetBody) {
                        if(giveMonetBody.getStatus().equals(Constant.success)){
                            dismiss();
                            Toast.makeText(getContext() , "Монеты успешно переведены", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return binding.getRoot();
    }

    public void onClickButton(){


        binding.button3.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            if(count > (long)(10000000)){
                Toast.makeText(getContext(), "Слишком большая сумма", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvCount.setText(String.valueOf(count * 10 +1));
                int current = Integer.parseInt(binding.tvCount.getText().toString());
                int commission = (int)Math.ceil(current * 0.01);
                itogo = current - commission;
                binding.commission.setText(String.valueOf(commission));
                binding.itogo.setText(String.valueOf(itogo));

            }
        });
        binding.button4.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            if(count > (long)(10000000)){
                Toast.makeText(getContext(), "Слишком большая сумма", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvCount.setText(String.valueOf(count * 10 +2));
                int current = Integer.parseInt(binding.tvCount.getText().toString());
                int commission = (int)Math.ceil(current * 0.01);
                itogo = current - commission;
                binding.commission.setText(String.valueOf(commission));
                binding.itogo.setText(String.valueOf(itogo));
            }
        });
        binding.button8.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            if(count > (long)(10000000)){
                Toast.makeText(getContext(), "Слишком большая сумма", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvCount.setText(String.valueOf(count * 10 + 3));
                int current = Integer.parseInt(binding.tvCount.getText().toString());
                int commission = (int)Math.ceil(current * 0.01);
                itogo = current - commission;
                binding.commission.setText(String.valueOf(commission));
                binding.itogo.setText(String.valueOf(itogo));
            }
        });
        binding.button6.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            if(count > (long)(10000000)){
                Toast.makeText(getContext(), "Слишком большая сумма", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvCount.setText(String.valueOf(count * 10 + 4));
                int current = Integer.parseInt(binding.tvCount.getText().toString());
                int commission = (int)Math.ceil(current * 0.01);
                itogo = current - commission;
                binding.commission.setText(String.valueOf(commission));
                binding.itogo.setText(String.valueOf(itogo));
            }
        });
        binding.button7.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            if(count > (long)(10000000)){
                Toast.makeText(getContext(), "Слишком большая сумма", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvCount.setText(String.valueOf(count * 10 + 5));
                int current = Integer.parseInt(binding.tvCount.getText().toString());
                int commission = (int)Math.ceil(current * 0.01);
                itogo = current - commission;
                binding.commission.setText(String.valueOf(commission));
                binding.itogo.setText(String.valueOf(itogo));
            }
        });
        binding.button11.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            if(count > (long)(10000000)){
                Toast.makeText(getContext(), "Слишком большая сумма", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvCount.setText(String.valueOf(count * 10 + 6));
                int current = Integer.parseInt(binding.tvCount.getText().toString());
                int commission = (int)Math.ceil(current * 0.01);
                itogo = current - commission;
                binding.commission.setText(String.valueOf(commission));
                binding.itogo.setText(String.valueOf(itogo));
            }
        });
        binding.button9.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            if(count > (long)(10000000)){
                Toast.makeText(getContext(), "Слишком большая сумма", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvCount.setText(String.valueOf(count * 10 + 7));
                int current = Integer.parseInt(binding.tvCount.getText().toString());
                int commission = (int)Math.ceil(current * 0.01);
                itogo = current - commission;
                binding.commission.setText(String.valueOf(commission));
                binding.itogo.setText(String.valueOf(itogo));
            }
        });
        binding.button10.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            if(count > (long)(10000000)){
                Toast.makeText(getContext(), "Слишком большая сумма", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvCount.setText(String.valueOf(count * 10 + 8));
                int current = Integer.parseInt(binding.tvCount.getText().toString());
                int commission = (int)Math.ceil(current * 0.01);
                itogo = current - commission;
                binding.commission.setText(String.valueOf(commission));
                binding.itogo.setText(String.valueOf(itogo));
            }
        });
        binding.button13.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            if(count > (long)(10000000)){
                Toast.makeText(getContext(), "Слишком большая сумма", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvCount.setText(String.valueOf(count * 10 + 9));
                int current = Integer.parseInt(binding.tvCount.getText().toString());
                int commission = (int)Math.ceil(current * 0.01);
                itogo = current - commission;
                binding.commission.setText(String.valueOf(commission));
                binding.itogo.setText(String.valueOf(itogo));
            }
        });
        binding.btnClear.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            binding.tvCount.setText(String.valueOf(0));
            int current = Integer.parseInt(binding.tvCount.getText().toString());
            int commission = (int)Math.ceil(current * 0.01);
            itogo = current - commission;
            binding.commission.setText(String.valueOf(commission));
            binding.itogo.setText(String.valueOf(itogo));
        });
        binding.button14.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            if(count > (long)(10000000)){
                Toast.makeText(getContext(), "Слишком большая сумма", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvCount.setText(String.valueOf(count * 10));
                int current = Integer.parseInt(binding.tvCount.getText().toString());
                int commission = (int)Math.ceil(current * 0.01);
                itogo = current - commission;
                binding.commission.setText(String.valueOf(commission));
                binding.itogo.setText(String.valueOf(itogo));
            }
        });
        binding.button15.setOnClickListener(v -> {
            long count = Long.parseLong(binding.tvCount.getText().toString());
            binding.tvCount.setText(String.valueOf((int)(Math.floor(count/10))));
            int current = Integer.parseInt(binding.tvCount.getText().toString());
            int commission = (int)Math.ceil(current * 0.01);
            itogo = current - commission;
            binding.commission.setText(String.valueOf(commission));
            binding.itogo.setText(String.valueOf(itogo));
        });
    }
}
