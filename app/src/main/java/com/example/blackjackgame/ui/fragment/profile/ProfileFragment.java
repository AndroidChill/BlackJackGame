package com.example.blackjackgame.ui.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentProfileBinding;
import com.example.blackjackgame.ui.fragment.profile.content.ProfileContentFragment;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;

    public static Fragment newInstance() {

        Bundle args = new Bundle();

        Fragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        getActivity().getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container_profile, ProfileContentFragment.newInstance())
                .commit();

        return binding.getRoot();
    }
}
