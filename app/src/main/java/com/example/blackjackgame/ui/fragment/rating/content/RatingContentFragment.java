package com.example.blackjackgame.ui.fragment.rating.content;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentRatingContentBinding;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsContentAllFragment;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsContentReferralsFragment;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsRequestFragment;
import com.example.blackjackgame.ui.fragment.rating.content.pager.RatingContentCustomFragment;
import com.example.blackjackgame.ui.fragment.rating.content.pager.RatingContentLuckFragment;
import com.example.blackjackgame.ui.fragment.rating.content.pager.RatingContentRichFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RatingContentFragment extends Fragment {

    private FragmentRatingContentBinding binding;
    private SharedPreferences sharedPreferences;

    private int type = 2;

    private final int NEVER_SHOW_HINT = 0;
    private final int HIDE_HINT = 1;
    private final int SHOW_HINT = 2;

    private int position;

    public static RatingContentFragment newInstance() {

        Bundle args = new Bundle();

        RatingContentFragment fragment = new RatingContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rating_content, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        binding.hint.neverHint.setOnClickListener(v -> {
            sharedPreferences.edit().putInt(Constant.isShowHintRating, NEVER_SHOW_HINT).apply();
            binding.constraintLayout4.setVisibility(View.GONE);
        });

        binding.hint.hideHint.setOnClickListener(v -> {
            sharedPreferences.edit().putInt(Constant.isShowHintRating, HIDE_HINT).apply();
            binding.constraintLayout4.setVisibility(View.GONE);
        });

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
                        .replace(R.id.container_rating_item, RatingContentLuckFragment.newInstance())
                        .commit();
                break;
            case 1:

                binding.btn2.setBackgroundResource(R.drawable.border_edit);
                binding.btn1.setBackgroundResource(R.drawable.vibor_button);
                binding.btn3.setBackgroundResource(R.drawable.vibor_button);

                getFragmentManager().beginTransaction()
                        .replace(R.id.container_rating_item, RatingContentRichFragment.newInstance())
                        .commit();
                break;
            case 2:

                binding.btn3.setBackgroundResource(R.drawable.border_edit);
                binding.btn2.setBackgroundResource(R.drawable.vibor_button);
                binding.btn1.setBackgroundResource(R.drawable.vibor_button);

                getFragmentManager().beginTransaction()
                        .replace(R.id.container_rating_item, RatingContentCustomFragment.newInstance())
                        .commit();
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if(sharedPreferences.getInt(Constant.isShowHintRating, 2) != 0){
            sharedPreferences.edit().putInt(Constant.isShowHintRating, SHOW_HINT).apply();
            binding.constraintLayout4.setVisibility(View.VISIBLE);
        } else {
            binding.constraintLayout4.setVisibility(View.GONE);
        }
    }

}
