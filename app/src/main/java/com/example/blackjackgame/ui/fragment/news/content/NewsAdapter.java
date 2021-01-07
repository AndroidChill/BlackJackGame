package com.example.blackjackgame.ui.fragment.news.content;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.blackjackgame.rModel.news.News;
import com.example.blackjackgame.ui.interfaceClick.INewsClick;
import com.example.blackjackgame.util.ConvertDate;
import com.example.blackjackgame.util.ConvertStringToImage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> list = new ArrayList<>();
    private Context context;
    private INewsClick listener;

    public NewsAdapter(Context context, INewsClick listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setList(List<News> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentNewsContentItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_content_item, parent, false);
        return new ViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private FragmentNewsContentItemBinding binding;
        private News news;
        private INewsClick listener;
        private int position;

        ViewHolder(FragmentNewsContentItemBinding binding, INewsClick listener){
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        private void bind(int position, News news){

            Log.d("tag", position + " : " + news.getType());

            this.position = position;

            this.news = news;

            binding.header.setText(news.getHeader());
            binding.description.setText(news.getText());
            binding.date.setText(String.valueOf(news.getTime()));


            DateFormat dataFormat = new SimpleDateFormat("dd.mm.yyyy");
            String str = dataFormat.format(new Date(news.getTime() * 1000));

            binding.date.setText(ConvertDate.convertTimeToDate(news.getTime()));

            if(news.getImage() == null){
                binding.imageView5.setVisibility(View.GONE);
            } else {
                if(news.getImage().isEmpty()){
                    binding.imageView5.setVisibility(View.GONE);
                }
            }

            if(news.getAvatar().isEmpty()){
//                binding.circleImageView2.setImageResource(R.drawable.default_info);
                binding.circleImageView2.setVisibility(View.GONE);
            }

            switch (news.getType()){
                case 1 :
                    binding.header.setVisibility(View.VISIBLE);
                    binding.description.setVisibility(View.VISIBLE);

                    if(news.getUrl().isEmpty()){
                        binding.url.setVisibility(View.GONE);
                    } else {
                        binding.url.setVisibility(View.VISIBLE);
                        binding.url.setText(news.getUrl());
                        binding.url.setOnClickListener(v -> {
                            jumpUrl(news.getUrl());
                        });
                    }
                    binding.secondTvDesc.setVisibility(View.GONE);
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.friendOtklonit.setVisibility(View.GONE);
                    binding.friendPrinyat.setVisibility(View.GONE);
                    binding.header.setVisibility(View.VISIBLE);
                    break;
                case 2 :
                    binding.llTournamentStart.setVisibility(View.VISIBLE);
                    binding.friendPrinyat.setVisibility(View.GONE);
                    binding.friendOtklonit.setVisibility(View.GONE);
                    binding.url.setVisibility(View.GONE);
                    @SuppressLint("DefaultLocale")
                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(news.getTime()),
                            TimeUnit.MILLISECONDS.toMinutes(news.getTime()) % TimeUnit.HOURS.toMinutes(1),
                            TimeUnit.MILLISECONDS.toSeconds(news.getTime()) % TimeUnit.MINUTES.toSeconds(1));
                    binding.secondTvTimer.setText(hms);

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
                    binding.friendOtklonit.setVisibility(View.VISIBLE);
                    binding.friendPrinyat.setVisibility(View.VISIBLE);
                    binding.friendPrinyat.setText("Принять");
                    binding.friendOtklonit.setText("Отклонить");
                    if(news.getType() == 3){
                        binding.friendPrinyat.setOnClickListener(v -> {
                            listener.onApplyGame(position, 3, news.getGameFriendsId());
                        });

                        binding.friendOtklonit.setOnClickListener(v -> {
                            listener.onCancelGame(position, 3, news.getGameFriendsId());
                        });
                    }
                    break;
                case 4 :
                    binding.circleImageView2.setVisibility(View.VISIBLE);
                    binding.url.setVisibility(View.GONE);
                    binding.secondTvDesc.setVisibility(View.GONE);
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.imageView5.setVisibility(View.GONE);
                    binding.description.setText(news.getNickname());
                    ConvertStringToImage.convert(binding.circleImageView2, news.getAvatar());
                    binding.friendOtklonit.setVisibility(View.VISIBLE);
                    binding.friendPrinyat.setVisibility(View.VISIBLE);
                    binding.friendPrinyat.setText("Принять");
                    binding.friendOtklonit.setText("Отклонить");
                    binding.friendPrinyat.setOnClickListener(v -> {
                        listener.onAddFriend(position,4, news.getUserId());
                    });

                    binding.friendOtklonit.setOnClickListener(v -> {
                        listener.onDelFriend(position, 4, news.getUserId());
                    });
                    break;
                case 5 :
                    binding.circleImageView2.setVisibility(View.VISIBLE);
                    binding.url.setVisibility(View.GONE);
                    binding.secondTvDesc.setVisibility(View.GONE);
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.friendPrinyat.setVisibility(View.GONE);
                    binding.friendOtklonit.setVisibility(View.GONE);
                    binding.imageView5.setVisibility(View.GONE);
                    binding.description.setText(news.getNickname());
                    ConvertStringToImage.convert(binding.circleImageView2, news.getAvatar());
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
                    binding.circleImageView2.setVisibility(View.VISIBLE);
                    Objects.requireNonNull(binding.url).setVisibility(View.GONE);
                    binding.secondTvDesc.setVisibility(View.GONE);
                    binding.secondTvTimer.setVisibility(View.GONE);
                    binding.friendPrinyat.setVisibility(View.GONE);
                    binding.friendOtklonit.setVisibility(View.GONE);
                    binding.imageView5.setVisibility(View.GONE);
                    binding.description.setText(news.getNickname());
                    ConvertStringToImage.convert(binding.circleImageView2, news.getAvatar());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + news.getType());
            }

    }

        private void jumpUrl(String url){
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(browserIntent);
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
                binding.secondTvDesc.setText("Турнир уже начался, поторопись принять участие");
                binding.secondTvTimer.setVisibility(View.GONE);
            }

            public void onTick(long millisUntilFinished)
            {
                @SuppressLint("DefaultLocale")
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % TimeUnit.MINUTES.toSeconds(1));
                binding.secondTvTimer.setText(hms);
            }

        }

    }

}
