package com.example.blackjackgame.ui.fragment.tournament.content;

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
import com.example.blackjackgame.databinding.FragmentTournamentContentBinding;
import com.example.blackjackgame.ui.fragment.tournament.content.pager.TournamentContentPagerFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TournamentContentFragment extends Fragment {

    private FragmentTournamentContentBinding binding;

    private String[] title = {
            " Быстрая игра ",
            " Садись и играй "
    };

    public static TournamentContentFragment newInstance() {

        Bundle args = new Bundle();

        TournamentContentFragment fragment = new TournamentContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tournament_content, container, false);

        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(getActivity()));

        binding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, ((tab, position) -> {
            tab.setText(title[position]);
        })).attach();

        //пошаговый открытыми /поочередный
        //динамический фиксированный
        //возможность вывода реальных денег
        //или по времени или до выигрышей

        return binding.getRoot();
    }

    class ViewPagerFragmentAdapter extends FragmentStateAdapter{

        public ViewPagerFragmentAdapter(FragmentActivity fragmentActivity){
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return TournamentContentPagerFragment.newInstance();
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
