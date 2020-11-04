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
import com.example.blackjackgame.databinding.FragmentSignEmailBinding;
import com.example.blackjackgame.ui.activity.SignActivity;

public class SignEmailFragment extends Fragment {

    private FragmentSignEmailBinding binding;

    public static SignEmailFragment newInstance() {

        Bundle args = new Bundle();

        SignEmailFragment fragment = new SignEmailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_email, container, false);

        binding.btnSendEmail.setOnClickListener(v -> {

            if(isValidEmailAddress(binding.email.getText().toString())){
                ((SignActivity)getActivity()).changeInCodeFragment(binding.email.getText().toString());
            } else {
                Toast.makeText(getContext(), "Введите корректный email...", Toast.LENGTH_SHORT).show();
            }


        });

        return binding.getRoot();
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
