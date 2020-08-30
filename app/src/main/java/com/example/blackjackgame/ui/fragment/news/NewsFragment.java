package com.example.blackjackgame.ui.fragment.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentNewsBinding;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.fragment.news.content.NewsContentFragment;
import com.example.blackjackgame.ui.fragment.news.content.NewsContentRFragment;

import java.util.Objects;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    public static NewsFragment newInstance() {

        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);

        getFragmentManager().beginTransaction()
                .add(R.id.container_news, NewsContentRFragment.newInstance())
                .commit();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((NavigationActivity) getActivity()).getSupportActionBar()).show();
    }
}
