package com.example.blackjackgame.ui.fragment.news;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.blackjackgame.databinding.FragmentNewsFilterBinding;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.fragment.news.content.NewsContentFragment;
import com.example.blackjackgame.ui.fragment.news.content.NewsContentRFragment;

public class NewsFilterFragment extends Fragment {

    private FragmentNewsFilterBinding binding;
    private SharedPreferences sharedPreferences;

    public static NewsFilterFragment newInstance() {

        Bundle args = new Bundle();

        NewsFilterFragment fragment = new NewsFilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_filter, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        ((NavigationActivity)getActivity()).getSupportActionBar().hide();

        ((Toolbar) binding.toolbar2).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container_news, NewsContentRFragment.newInstance())
                        .commit();
            }
        });

        binding.agree.setOnClickListener(v -> {
            String str = "";

            if(binding.chip3.isChecked()){
                str += "1";
            }
            if(binding.chip4.isChecked()){
                str += "2";
            }
            if(binding.chip5.isChecked()){
                str += "3";
            }
            if(binding.chip5.isChecked()){
                str += "4";
            }
            if(binding.chip6.isChecked()){
                str += "5";
            }
            if(binding.chip7.isChecked()){
                str += "6";
            }

            if (!str.isEmpty()){
                sharedPreferences.edit().putString("type_news", str).apply();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container_news, NewsContentRFragment.newInstance())
                        .commit();
            }

        });

        return binding.getRoot();
    }
}
