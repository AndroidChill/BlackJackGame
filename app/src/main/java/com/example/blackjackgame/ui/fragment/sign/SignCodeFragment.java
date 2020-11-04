package com.example.blackjackgame.ui.fragment.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentSignCodeBinding;
import com.example.blackjackgame.ui.activity.SignActivity;

public class SignCodeFragment extends Fragment {

    private FragmentSignCodeBinding binding;

    public static SignCodeFragment newInstance() {

        Bundle args = new Bundle();

        SignCodeFragment fragment = new SignCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_code, container, false);

        String k = (String) getArguments().getCharSequence("email");

        Toast.makeText(getContext(), k, Toast.LENGTH_SHORT).show();

        binding.btnCancel.setOnClickListener(v -> ((SignActivity)getActivity()).changeInEmailFragment());
        binding.btnDone.setOnClickListener(v -> sendData(binding.editText2 != null ? binding.editText2.getText().toString() : ""));

        return binding.getRoot();
    }

    private void sendData(String code){

    }
}
