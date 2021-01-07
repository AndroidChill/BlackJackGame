package com.example.blackjackgame.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.DialogGetMonetBinding;
import com.example.blackjackgame.rModel.coins.CoinsBody;
import com.example.blackjackgame.rModel.coins.MoneyTransfer;
import com.example.blackjackgame.rModel.historyCoins.HistoryCoins;
import com.example.blackjackgame.rModel.moneyTransfer.MoneyTransferBody;
import com.example.blackjackgame.rNetwork.request.coins.CoinsRequest;
import com.example.blackjackgame.rNetwork.request.moneyTransfer.MoneyTransferRequest;
import com.example.blackjackgame.rViewModel.coinsTransfer.MoneyTransferFactory;
import com.example.blackjackgame.rViewModel.coinsTransfer.MoneyTransferViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.CheckBoxAdapter;
import com.example.blackjackgame.ui.interfaceClick.CheckClick;

import java.util.ArrayList;
import java.util.List;

import static com.example.blackjackgame.ui.dialog.ProfileChangePhotoDialogFragment.view;

public class GetMonetDialog extends DialogFragment implements CheckClick {

    private DialogGetMonetBinding binding;
    private MoneyTransferViewModel viewModelTransfer;
    private CoinsBody model;
    private SharedPreferences sharedPreferences;
    private CheckBoxAdapter adapter;

    private int type = 0;

    private boolean typeR = true;

    public static GetMonetDialog newInstance() {

        Bundle args = new Bundle();

        GetMonetDialog fragment = new GetMonetDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_get_monet, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        viewModelTransfer = new ViewModelProvider(getViewModelStore(), new MoneyTransferFactory()).get(MoneyTransferViewModel.class);

        model = getArguments().getParcelable("model");
        binding.setModel(model);
        Toast.makeText(getContext(), model.getCoinsInfo().getHeader(), Toast.LENGTH_SHORT).show();

        final RadioButton[] rb = new RadioButton[model.getMoneyTransfer().size()];
        for(int i=0; i<model.getMoneyTransfer().size(); i++){
            rb[i]  = new RadioButton(getContext());
            rb[i].setText(model.getMoneyTransfer().get(i).getBank());
            rb[i].setId(i + 1000);
            rb[i].setTextColor(Color.WHITE);
            binding.rv.addView(rb[i]);
        }

        binding.rv.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                binding.caseRubl.setText(model.getMoneyTransfer().get(checkedId-1000).getInfo());
                binding.caseRubl.setVisibility(View.VISIBLE);
                binding.editText5.setVisibility(View.VISIBLE);
                binding.btn.setVisibility(View.VISIBLE);
            }
        });

        actionText();

        initCase();
        sendData();
        return binding.getRoot();
    }

    private void actionText(){
        binding.editText5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                       binding.rv.setVisibility(View.GONE);
                       binding.caseTitleSposob.setVisibility(View.GONE);
                       binding.btn.setVisibility(View.GONE);
                       binding.btnApply.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.btnApply.setOnClickListener(v -> {
            binding.rv.setVisibility(View.VISIBLE);
            binding.caseTitleSposob.setVisibility(View.VISIBLE);
            binding.btn.setVisibility(View.VISIBLE);
            binding.btnApply.setVisibility(View.GONE);
            binding.editText5.clearFocus();
        });

    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void removePhoneKeypad() {
        InputMethodManager inputManager = (InputMethodManager) binding.getRoot()
                .getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        IBinder binder = view.getWindowToken();
        inputManager.hideSoftInputFromWindow(binder,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void sendData(){

        binding.btn.setOnClickListener(v -> {
            viewModelTransfer.getLiveData(getContext(), initTransferRequest()).observe(getViewLifecycleOwner(), new Observer<MoneyTransferBody>() {
                @Override
                public void onChanged(MoneyTransferBody moneyTransferBody) {
                    if (moneyTransferBody.getToken() != null) {
                        if (moneyTransferBody.getToken().equals("error")) {
                            startBaseActivity();
                        }
                    }

                    if (moneyTransferBody.getStatus().equals("success")) {

                        //проверка на капчу
                        if (moneyTransferBody.getCaptchaImageUrl() != null) {
                            createCaptchaDialog(moneyTransferBody.getCaptchaImageUrl());
                        }

                        //проверка на отзывы
                        if (moneyTransferBody.getPopup() != null) {
                            if (moneyTransferBody.getPopup().equals("comment")) {
                                createReviewDialog();
                            }
                        }

                        binding.main.setPadding(32,32,32,32);
                        binding.content.setVisibility(View.GONE);
                        binding.success.setVisibility(View.VISIBLE);
                        Handler newHandler = new Handler();
                                newHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onDestroy();
                                Log.d("tag", "run: ");
                            }
                        }, 5000);


                    } else {
                        binding.errorMessage.setVisibility(View.VISIBLE);
                        binding.errorMessage.setText(moneyTransferBody.getStatusText());
                    }
                }
            });
        });

    }

    private MoneyTransferRequest initTransferRequest(){
        return new MoneyTransferRequest(
                "money_transfer",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                model.getCoinsInfo().getId(),
                type == 1 ? "Webmoney" : "Yandex",
                binding.editText5.getText().toString()
        );
    }


    private void initCase(){
        binding.case1.setOnClickListener(v -> {

            typeR = true;

            binding.case2.setBackgroundResource(R.drawable.border_edit_dark);
            binding.case1.setBackgroundResource(R.drawable.border_edit);
            binding.rv.setVisibility(View.VISIBLE);
            binding.caseRubl.setVisibility(View.VISIBLE);
            binding.editText5.setVisibility(View.VISIBLE);
            binding.caseTitleSposob.setVisibility(View.VISIBLE);
            binding.caseRubl.setVisibility(View.VISIBLE);
            binding.errorMessage.setVisibility(View.GONE);
        });

        binding.case2.setOnClickListener(v -> {
            binding.case1.setBackgroundResource(R.drawable.border_edit_dark);
            binding.case2.setBackgroundResource(R.drawable.border_edit);
            binding.rv.setVisibility(View.GONE);
            binding.caseRubl.setVisibility(View.GONE);
            binding.editText5.setVisibility(View.GONE);
            binding.errorMessage.setVisibility(View.GONE);
            binding.caseTitleSposob.setVisibility(View.GONE);
            binding.btn.setVisibility(View.VISIBLE);
        });
    }

    private void startBaseActivity() {
        startActivity(new Intent(getContext(), MainActivity.class));
        ((NavigationActivity) getActivity()).finish();
    }

    //создание диалога с капчей
    private void createCaptchaDialog(String url) {
        CaptchaDialog.createCaptchaDialog(
                getContext(),
                getLayoutInflater(),
                url,
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

    //создание диалога с отзывами
    private void createReviewDialog() {
        ReviewDialogHelper.buildReview(
                getContext(),
                getLayoutInflater(),
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

    private int id = -1;

    @Override
    public void onClick(Boolean active, CheckBox last, CheckBox current, MoneyTransfer model) {
        if(active){
            last.setChecked(false);
        }

        id = current.getId();
        if(current == last){
            id = -1;
        }

        binding.caseRubl.setText(model.getInfo());

        binding.caseRubl.setVisibility(View.VISIBLE);
        binding.editText5.setVisibility(View.VISIBLE);
        binding.btn.setVisibility(View.VISIBLE);
    }
}
