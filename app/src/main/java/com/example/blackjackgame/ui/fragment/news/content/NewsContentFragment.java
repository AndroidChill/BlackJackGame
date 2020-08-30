package com.example.blackjackgame.ui.fragment.news.content;

import android.os.Bundle;
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
import com.example.blackjackgame.databinding.FragmentNewsContentBinding;
import com.example.blackjackgame.ui.fragment.news.content.category.NewsCategoryContentFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class NewsContentFragment extends Fragment {

    FragmentNewsContentBinding binding;

    private String[] title = {" Игры ", " Обновления ", " Друзья "};

    public static NewsContentFragment newInstance() {

        Bundle args = new Bundle();

        NewsContentFragment fragment = new NewsContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_content,container, false);

        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(getActivity()));

        binding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab,position) -> tab.setText(title[position])).attach();

        return binding.getRoot();
    }

    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position){
                case 0:
                    Fragment fragment = NewsCategoryContentFragment.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putInt("page", 0);
                    fragment.setArguments(bundle);
                    return fragment;
                case 1:
                    Fragment fragment2 = NewsCategoryContentFragment.newInstance();
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("page", 1);
                    fragment2.setArguments(bundle2);
                    return fragment2;
                case 2:
                    Fragment fragment3 = NewsCategoryContentFragment.newInstance();
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("page", 2);
                    fragment3.setArguments(bundle3);
                    return fragment3;
            }
            return NewsCategoryContentFragment.newInstance();
        }

        @Override
        public int getItemCount() {
            return title.length;
        }

    }
}
