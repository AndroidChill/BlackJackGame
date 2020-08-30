package com.example.blackjackgame.ui.fragment.getmonet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentGetMonetBinding;
import com.example.blackjackgame.ui.fragment.getmonet.content.GetMonetContentFragment;

public class GetMonetFragment extends Fragment {

    private FragmentGetMonetBinding binding;

    public static GetMonetFragment newInstance() {

        Bundle args = new Bundle();

        GetMonetFragment fragment = new GetMonetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_monet, container, false);

        getFragmentManager().beginTransaction()
                .add(R.id.container_get_monet, GetMonetContentFragment.newInstance())
                .commit();

        return binding.getRoot();
    }
}
