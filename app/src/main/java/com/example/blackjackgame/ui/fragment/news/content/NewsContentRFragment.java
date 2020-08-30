package com.example.blackjackgame.ui.fragment.news.content;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentNewsContentRBinding;
import com.example.blackjackgame.model.news.NewsBody;
import com.example.blackjackgame.network.responce.news.NewsRequest;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.fragment.news.NewsFilterFragment;
import com.example.blackjackgame.viewmodel.news.NewsFactory;
import com.example.blackjackgame.viewmodel.news.NewsViewModel;

public class NewsContentRFragment extends Fragment {

    private FragmentNewsContentRBinding binding;
    private NewsViewModel viewModel;
    private SharedPreferences sharedPreferences;

    public static NewsContentRFragment newInstance() {

        Bundle args = new Bundle();

        NewsContentRFragment fragment = new NewsContentRFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_content_r, container, false);

        setHasOptionsMenu(true);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        viewModel = new ViewModelProvider(this, new NewsFactory(getActivity().getApplication())).get(NewsViewModel.class);

        NewsRequest request = new NewsRequest(
                "news",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "null"),
                sharedPreferences.getString("type_news", "1234567")
        );

        viewModel.getNews(request).observe(getViewLifecycleOwner(), new Observer<NewsBody>() {
            @Override
            public void onChanged(NewsBody newsBody) {
                binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rv.setAdapter(new NewsAdapter(newsBody.getNews()));
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_news_category, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container_news, NewsFilterFragment.newInstance())
                        .commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        ((NavigationActivity) getActivity()).getSupportActionBar().show();
        super.onResume();
    }
}
