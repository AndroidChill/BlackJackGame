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
import com.example.blackjackgame.ui.fragment.rating.content.pager.RatingContentCustomFragment;
import com.example.blackjackgame.ui.fragment.rating.content.pager.RatingContentLuckFragment;
import com.example.blackjackgame.ui.fragment.rating.content.pager.RatingContentRichFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RatingContentFragment extends Fragment {

    private FragmentRatingContentBinding binding;
    private SharedPreferences sharedPreferences;

    private String[] titles = {
            " Удаливые ",
            " Богачи ",
            " Рейтинг "
    };

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

        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(getActivity()));

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab,position) -> tab.setText(titles[position])).attach();

        binding.hint.hideHint.setOnClickListener(v -> {
            binding.constraintLayout4.setVisibility(View.GONE);
        });

        binding.hint.neverHint.setOnClickListener(v -> {
            binding.constraintLayout4.setVisibility(View.GONE);
            sharedPreferences.edit().putBoolean(Constant.isShowHintRating, false).apply();
        });

        return binding.getRoot();
    }

    class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position){
                case 0 :
                    return RatingContentLuckFragment.newInstance();
                case 1 :
                    return RatingContentRichFragment.newInstance();
            }
            return RatingContentCustomFragment.newInstance();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!sharedPreferences.getBoolean(Constant.isShowHintRating, true)){
            binding.constraintLayout4.setVisibility(View.GONE);
        }
    }

}
