package com.example.blackjackgame.ui.fragment.rules;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentRulesBinding;

public class RulesFragment extends Fragment {

    private FragmentRulesBinding binding;

    public static RulesFragment newInstance() {

        Bundle args = new Bundle();

        RulesFragment fragment = new RulesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rules, container, false);

        return binding.getRoot();
    }
}
