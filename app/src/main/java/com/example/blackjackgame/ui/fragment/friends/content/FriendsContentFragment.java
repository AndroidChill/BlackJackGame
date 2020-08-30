package com.example.blackjackgame.ui.fragment.friends.content;

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
import com.example.blackjackgame.databinding.FragmentFriendsContentBinding;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsContentAllFragment;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsContentReferralsFragment;
import com.example.blackjackgame.ui.fragment.friends.content.pager.FriendsContentRequestFragment;
import com.google.android.material.tabs.TabLayoutMediator;

public class FriendsContentFragment extends Fragment {

    private FragmentFriendsContentBinding binding;

    private String[] titles = {
            " Друзья ",
            " Рефералы ",
            " Заявки "
    };

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

        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(getActivity()));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, ((tab, position) -> {

            tab.setText(titles[position]);
        })).attach();

        return binding.getRoot();
    }

    class ViewPagerFragmentAdapter extends FragmentStateAdapter{

        ViewPagerFragmentAdapter(FragmentActivity fragmentActivity){
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0 :
                    return FriendsContentAllFragment.newInstance();
                case 1 :
                    return FriendsContentReferralsFragment.newInstance();
                case 2 :
                    return FriendsContentRequestFragment.newInstance();
            }
            return FriendsContentAllFragment.newInstance();
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

}
