package com.example.blackjackgame.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.example.blackjackgame.R;
import com.example.blackjackgame.databinding.DialogReviewBinding;
import com.example.blackjackgame.model.statics.Review;
import com.example.blackjackgame.model.statics.ReviewBody;
import com.example.blackjackgame.network.responce.stattics.ReviewRequest;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;
import com.example.blackjackgame.viewmodel.review.ReviewFactory;
import com.example.blackjackgame.viewmodel.review.ReviewViewModel;
import com.google.gson.annotations.SerializedName;

public class ReviewDialogHelper {

    private static int countStar = 0;
    private static Dialog dialog;
    private static ReviewViewModel viewModel;

    public static void buildReview(Context context, LayoutInflater inflater, ViewModelStore store, LifecycleOwner owner){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false);

        viewModel = new ViewModelProvider(store, new ReviewFactory()).get(ReviewViewModel.class);

        DialogReviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_review, null, false);
        builder.setView(binding.getRoot());

        initClick(binding);
        clickCancel(binding, owner);
        clickSend(binding, owner, context);

        builder.create();
        dialog = builder.show();
    }

    private static void initClick(DialogReviewBinding binding){
        binding.iv1.setOnClickListener(v -> {
            binding.iv1.setImageResource(R.drawable.star_full);
            binding.iv2.setImageResource(R.drawable.star_empty);
            binding.iv3.setImageResource(R.drawable.star_empty);
            binding.iv4.setImageResource(R.drawable.star_empty);
            binding.iv5.setImageResource(R.drawable.star_empty);
            countStar = 1;

            binding.btnToPm.setVisibility(View.GONE);
            binding.btnLater.setVisibility(View.GONE);
            binding.tvReviewPrehead.setVisibility(View.VISIBLE);
            binding.cvReview.setVisibility(View.VISIBLE);
            binding.btnSend.setVisibility(View.VISIBLE);
        });

        binding.iv2.setOnClickListener(v -> {
            binding.iv1.setImageResource(R.drawable.star_full);
            binding.iv2.setImageResource(R.drawable.star_full);
            binding.iv3.setImageResource(R.drawable.star_empty);
            binding.iv4.setImageResource(R.drawable.star_empty);
            binding.iv5.setImageResource(R.drawable.star_empty);
            countStar = 2;

            binding.btnToPm.setVisibility(View.GONE);
            binding.btnLater.setVisibility(View.GONE);
            binding.tvReviewPrehead.setVisibility(View.VISIBLE);
            binding.cvReview.setVisibility(View.VISIBLE);
            binding.btnSend.setVisibility(View.VISIBLE);
        });

        binding.iv3.setOnClickListener(v -> {
            binding.iv1.setImageResource(R.drawable.star_full);
            binding.iv2.setImageResource(R.drawable.star_full);
            binding.iv3.setImageResource(R.drawable.star_full);
            binding.iv4.setImageResource(R.drawable.star_empty);
            binding.iv5.setImageResource(R.drawable.star_empty);
            countStar = 3;

            binding.btnToPm.setVisibility(View.GONE);
            binding.btnLater.setVisibility(View.GONE);
            binding.tvReviewPrehead.setVisibility(View.VISIBLE);
            binding.cvReview.setVisibility(View.VISIBLE);
            binding.btnSend.setVisibility(View.VISIBLE);
        });

        binding.iv4.setOnClickListener(v -> {
            binding.iv1.setImageResource(R.drawable.star_full);
            binding.iv2.setImageResource(R.drawable.star_full);
            binding.iv3.setImageResource(R.drawable.star_full);
            binding.iv4.setImageResource(R.drawable.star_full);
            binding.iv5.setImageResource(R.drawable.star_empty);
            countStar = 4;

            binding.btnToPm.setVisibility(View.GONE);
            binding.btnLater.setVisibility(View.GONE);
            binding.tvReviewPrehead.setVisibility(View.VISIBLE);
            binding.cvReview.setVisibility(View.VISIBLE);
            binding.btnSend.setVisibility(View.VISIBLE);
        });

        binding.iv5.setOnClickListener(v -> {
            binding.iv1.setImageResource(R.drawable.star_full);
            binding.iv2.setImageResource(R.drawable.star_full);
            binding.iv3.setImageResource(R.drawable.star_full);
            binding.iv4.setImageResource(R.drawable.star_full);
            binding.iv5.setImageResource(R.drawable.star_full);
            countStar = 5;
            binding.btnToPm.setVisibility(View.VISIBLE);
        });
    }

    private static void clickCancel(DialogReviewBinding binding, LifecycleOwner owner){
        binding.btnLater.setOnClickListener(v -> {
            ReviewRequest request = new ReviewRequest(
                    "comment",
                    new Review(
                            String.valueOf(0),
                            "",
                            "0")
            );
            viewModel.getLiveData(request).observe(owner, observer -> {
            });
        });

    }

    private static void clickSend(DialogReviewBinding binding, LifecycleOwner owner, Context context){
        binding.btnSend.setOnClickListener(v -> {

            ReviewRequest request = new ReviewRequest(
                    "comment",
                    new Review(
                            String.valueOf(countStar),
                            binding.etReview.getText().toString(),
                            "1")
            );

            viewModel.getLiveData(request).observe(owner, observer -> {
                if(observer.getSuccess().equals("success")){
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Ошибка сервера", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

}
