package com.example.blackjackgame.ui.fragment.getmonet.content.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentGetMonetContentInfoBinding;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.fragment.getmonet.content.GetMonetContentFragment;

public class GetTaskMonetFragment extends Fragment {

    private FragmentGetMonetContentInfoBinding binding;

    public static GetTaskMonetFragment newInstance() {

        Bundle args = new Bundle();

        GetTaskMonetFragment fragment = new GetTaskMonetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_monet_content_info, container, false);

        ((NavigationActivity)getActivity()).getSupportActionBar().hide();

        binding.toolbar3.setNavigationOnClickListener(v ->
                getFragmentManager().beginTransaction().
                        replace(R.id.container_get_monet, GetMonetContentFragment.newInstance())
                        .commit());

        binding.header.setText(String.valueOf(getArguments().getInt("type")));
        binding.description.setText(getArguments().getString("text"));
        binding.money.setText(String.valueOf(getArguments().getLong("coins")));

        return binding.getRoot();
    }

//    private String getType(int type){
//        switch (type){
//            case
//        }
//    }
}
