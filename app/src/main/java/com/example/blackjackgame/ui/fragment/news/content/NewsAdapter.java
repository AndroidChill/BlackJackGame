package com.example.blackjackgame.ui.fragment.news.content;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.FragmentNewsContentItemBinding;
import com.example.blackjackgame.model.news.News;
import com.example.blackjackgame.util.ConvertDate;
import com.example.blackjackgame.util.ConvertStringToImage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> list = new ArrayList<>();

    public NewsAdapter(List<News> list){
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentNewsContentItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_content_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private FragmentNewsContentItemBinding binding;

        ViewHolder(FragmentNewsContentItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(News news){

            Log.d("tag", "bind: " + news.getType());

            binding.header.setText(news.getHeader());
            binding.description.setText(news.getText());
            binding.date.setText(String.valueOf(news.getTime()));

            DateFormat dataFormat = new SimpleDateFormat("dd.mm.yyyy");
            String str = dataFormat.format(new Date(news.getTime() * 1000));

            binding.date.setText(ConvertDate.convertTimeToDate(news.getTime()));

            switch (news.getType()){
                case 1 :
                    Log.d("tag", "bind: " + news.getUrl());
                    binding.header.setVisibility(View.VISIBLE);
                    binding.description.setVisibility(View.VISIBLE);
                    binding.url.setVisibility(View.VISIBLE);
                    binding.url.setText(news.getUrl());
                    binding.secondTvDesc.setVisibility(View.GONE);
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.friendOtklonit.setVisibility(View.GONE);
                    binding.friendPrinyat.setVisibility(View.GONE);
                    binding.header.setVisibility(View.VISIBLE);
                    break;
                case 2 :
                    binding.friendPrinyat.setVisibility(View.GONE);
                    binding.friendOtklonit.setVisibility(View.GONE);
                    binding.url.setVisibility(View.GONE);
                    binding.secondTvTimer.setText(String.valueOf(news.getTime_game()));

                    MyTimer timer = new MyTimer(10000, 1);
                    timer.start();

                    break;
                case 3 :
                    binding.url.setVisibility(View.GONE);
                    binding.description.setText(news.getText());
                    binding.header.setVisibility(View.VISIBLE);
                    binding.imageView5.setVisibility(View.GONE);
                    binding.description.setVisibility(View.VISIBLE);
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.secondTvDesc.setVisibility(View.GONE);
                    break;
                case 4 :
                    binding.url.setVisibility(View.GONE);
                    binding.secondTvDesc.setVisibility(View.GONE);
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.imageView5.setVisibility(View.GONE);
                    binding.description.setText(news.getUser_nickname());
                    ConvertStringToImage.convert(binding.circleImageView2, news.getUser_avatar());
                    break;
                case 5 :
                    binding.url.setVisibility(View.GONE);
                    binding.secondTvDesc.setVisibility(View.GONE);
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.friendPrinyat.setVisibility(View.GONE);
                    binding.friendOtklonit.setVisibility(View.GONE);
                    binding.imageView5.setVisibility(View.GONE);
                    binding.description.setText(news.getUser_nickname());
                    ConvertStringToImage.convert(binding.circleImageView2, news.getUser_avatar());
                    break;
                case 6 :
                    binding.url.setVisibility(View.GONE);
                    binding.secondTvDesc.setVisibility(View.GONE);
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.friendPrinyat.setVisibility(View.GONE);
                    binding.friendOtklonit.setVisibility(View.GONE);
                    break;
                case 7 :
                    binding.imageView5.setVisibility(View.GONE);
                    binding.url.setVisibility(View.GONE);
                    binding.description.setText(news.getText());
                    binding.friendOtklonit.setText("Монеты");
                    binding.friendPrinyat.setText("Рубли");
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.secondTvDesc.setVisibility(View.GONE);
                    break;
                case 8 :
                    binding.url.setVisibility(View.GONE);
                    binding.secondTvDesc.setVisibility(View.GONE);
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.friendPrinyat.setVisibility(View.GONE);
                    binding.friendOtklonit.setVisibility(View.GONE);
                    binding.imageView5.setVisibility(View.GONE);
                    binding.description.setText(news.getUser_nickname());
                    ConvertStringToImage.convert(binding.circleImageView2, news.getUser_avatar());
            }



    }

        class MyTimer extends CountDownTimer
        {

            public MyTimer(long millisInFuture, long countDownInterval)
            {
                super(millisInFuture, countDownInterval);
            }

            @Override
            public void onFinish()
            {
                // Do something...
            }

            public void onTick(long millisUntilFinished)
            {
                binding.secondTvTimer.setText(String.valueOf(millisUntilFinished));
            }

        }

    }

}
