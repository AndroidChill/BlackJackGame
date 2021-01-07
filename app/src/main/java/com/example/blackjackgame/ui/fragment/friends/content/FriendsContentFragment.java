package com.example.blackjackgame.ui.fragment.friends.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentFriendsContentBinding;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsContentAllFragment;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsContentReferralsFragment;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsContentRequestFragment;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsNewRequestFriends;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsRequestFragment;
import com.google.android.material.tabs.TabLayoutMediator;

public class FriendsContentFragment extends Fragment {

    private FragmentFriendsContentBinding binding;

    int position = 0;
    int currentPosition = 0;

    public static FriendsContentFragment newInstance() {

        Bundle args = new Bundle();

        FriendsContentFragment fragment = new FriendsContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_content, container, false);

        binding.btn1.setOnClickListener(v -> {
            position = 0;
            vibor();
        });

        binding.btn2.setOnClickListener(v -> {
            position = 1;
            vibor();
        });

        binding.btn3.setOnClickListener(v -> {
            position = 2;
            vibor();
        });


        vibor();

        return binding.getRoot();
    }


    private void vibor(){
        switch (position){
            case 0:

                binding.btn1.setBackgroundResource(R.drawable.border_edit);
                binding.btn2.setBackgroundResource(R.drawable.vibor_button);
                binding.btn3.setBackgroundResource(R.drawable.vibor_button);

                getFragmentManager().beginTransaction()
                        .replace(R.id.container_friends_item, FriendsContentAllFragment.newInstance())
                        .commit();
                break;
            case 1:

                binding.btn2.setBackgroundResource(R.drawable.border_edit);
                binding.btn1.setBackgroundResource(R.drawable.vibor_button);
                binding.btn3.setBackgroundResource(R.drawable.vibor_button);

                getFragmentManager().beginTransaction()
                        .replace(R.id.container_friends_item, FriendsContentReferralsFragment.newInstance())
                        .commit();
                break;
            case 2:

                binding.btn3.setBackgroundResource(R.drawable.border_edit);
                binding.btn2.setBackgroundResource(R.drawable.vibor_button);
                binding.btn1.setBackgroundResource(R.drawable.vibor_button);

                getFragmentManager().beginTransaction()
                        .replace(R.id.container_friends_item, FriendsNewRequestFriends.newInstance())
                        .commit();
                break;
        }
    }


}
