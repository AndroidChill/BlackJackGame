package com.example.blackjackgame.ui.fragment.news.content.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentNewsContentPagerBinding;
import com.example.blackjackgame.model.sign.News;
import com.example.blackjackgame.model.sign.NewsGuest;
import com.example.blackjackgame.ui.adapter.NewsContentAdapter;
import com.example.blackjackgame.viewmodel.sign.SignViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsCategoryContentFragment extends Fragment {

    private FragmentNewsContentPagerBinding binding;
    private SignViewModel viewModel;
    private List<News> list = new ArrayList<>();

    public static NewsCategoryContentFragment newInstance() {

        Bundle args = new Bundle();

        NewsCategoryContentFragment fragment = new NewsCategoryContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_content_pager, container, false);

        int k = getArguments().getInt("page");

        viewModel = ViewModelProviders.of(this).get(SignViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.signGuest().observe(getViewLifecycleOwner(), new Observer<NewsGuest>() {
            @Override
            public void onChanged(NewsGuest newsGuest) {

                List<News> news = new ArrayList<>();

                for(News n : newsGuest.getNews()){
                    if(n.getType() == k){
                        news.add(n);
                    }
                }

                NewsContentAdapter adapter = new NewsContentAdapter(news);
                binding.recyclerView.setAdapter(adapter);
                Log.d("tag", "onChanged: " + newsGuest.getNews().size());
                Log.d("tag", "onChanged: " + newsGuest.getToken());
            }
        });

        return binding.getRoot();
    }
}
