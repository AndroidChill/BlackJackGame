package com.example.blackjackgame.ui.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentNewsContentPagerRvItemBinding;
import com.example.blackjackgame.model.sign.News;

import java.util.ArrayList;
import java.util.List;

public class NewsContentAdapter extends RecyclerView.Adapter<NewsContentAdapter.ViewHolder> {

    List<News> news = new ArrayList<>();

    public NewsContentAdapter(List<News> news){
        this.news = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentNewsContentPagerRvItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_content_pager_rv_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        FragmentNewsContentPagerRvItemBinding binding;

        public ViewHolder(FragmentNewsContentPagerRvItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(News news){
            binding.title.setText(news.getHeader());
            binding.time.setText(String.valueOf(news.getTime()));
        }

    }

}
