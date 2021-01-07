package com.example.blackjackgame.ui.fragment.historyCoins;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentHistoryCoinsBinding;
import com.example.blackjackgame.rModel.coins.CoinsBody;
import com.example.blackjackgame.rModel.historyCoins.HistoryCoins;
import com.example.blackjackgame.rModel.historyCoins.HistoryCoinsBody;
import com.example.blackjackgame.rNetwork.request.coins.CoinsRequest;
import com.example.blackjackgame.rNetwork.request.coinsHistory.CoinsHistoryRequest;
import com.example.blackjackgame.rViewModel.coins.CoinsFactory;
import com.example.blackjackgame.rViewModel.coins.CoinsViewModel;
import com.example.blackjackgame.rViewModel.coinsHistory.CoinsHistoryFactory;
import com.example.blackjackgame.rViewModel.coinsHistory.CoinsHistoryViewModel;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.historyCoins.HistoryCoinsAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.GetMonetDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.interfaceClick.CoinsClick;

import java.util.ArrayList;
import java.util.List;


public class HistoryCoinsFragment extends Fragment implements CoinsClick {

    private FragmentHistoryCoinsBinding binding;
    private CoinsViewModel viewModelCoins;
    private CoinsHistoryViewModel viewModel;
    private CoinsHistoryRequest request;
    private SharedPreferences sharedPreferences;
    private HistoryCoinsAdapter adapter;
    private HistoryCoinsAdapter adapterActive;
    private boolean showRv = false;
    List<HistoryCoins> list = new ArrayList<>();

    public static HistoryCoinsFragment newInstance() {

        Bundle args = new Bundle();

        HistoryCoinsFragment fragment = new HistoryCoinsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history_coins, container, false);

        setHasOptionsMenu(true);

        viewModelCoins = new ViewModelProvider(getViewModelStore(), new CoinsFactory()).get(CoinsViewModel.class);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        initRequest();
        viewModel = new ViewModelProvider(getViewModelStore(), new CoinsHistoryFactory(request)).get(CoinsHistoryViewModel.class);
        binding.setViewModel(viewModel);

        adapter = new HistoryCoinsAdapter(HistoryCoinsFragment.this::onClick);
        adapterActive = new HistoryCoinsAdapter(HistoryCoinsFragment.this::onClick);

        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView2.setAdapter(adapter);

        binding.activeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.activeRv.setAdapter(adapterActive);

        update();
        refresh();

        binding.showRv.setOnClickListener(v -> {
            showRv = !showRv;
            if(showRv){
                adapterActive.setList(list);
            } else {

                List<HistoryCoins> listTemp = new ArrayList<>();
                if (list.size() > 0)
                    listTemp.add(list.get(0));
                if (list.size() > 1)
                    listTemp.add(list.get(1));
                adapterActive.setList(listTemp);
            }
        });

        return binding.getRoot();
    }

    private void refresh(){
        binding.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRequest();
                update();
            }
        });
    }

    private void update(){
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<HistoryCoinsBody>() {
            @Override
            public void onChanged(HistoryCoinsBody historyCoinsBody) {
                if (historyCoinsBody.getToken() != null) {
                    if (historyCoinsBody.getToken().equals("error")) {
                        startBaseActivity();
                    }
                }

                if (historyCoinsBody.getStatus().equals("success")) {

                    //проверка на капчу
                    if (historyCoinsBody.getCaptchaImageUrl() != null) {
                        createCaptchaDialog(historyCoinsBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if (historyCoinsBody.getPopup() != null) {
                        if (historyCoinsBody.getPopup().equals("comment")) {
                            createReviewDialog();
                        }
                    }

                    int coins = 0;
                    int money = 0;

                    for(HistoryCoins item : historyCoinsBody.getCoinsHistory()){
                        coins += item.getCoins();
                        money += item.getMoney();
                    }

                    binding.coins.setText(String.valueOf(coins));
                    binding.money.setText(String.valueOf(money));

                    adapter.setList(historyCoinsBody.getCoinsHistory());

                    list = historyCoinsBody.getCoinsHistory();

                    if(showRv){
                        adapterActive.setList(list);
                    } else {

                        List<HistoryCoins> listTemp = new ArrayList<>();
                        if (list.size() > 0)
                            listTemp.add(list.get(0));
                        if (list.size() > 1)
                            listTemp.add(list.get(1));
                        adapterActive.setList(listTemp);
                    }

                } else {
                    Toast.makeText(getContext(), historyCoinsBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
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

    private void initRequest(){
        request = new CoinsHistoryRequest(
                "coins_history",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(int id) {

        viewModelCoins.getCoinsList(getContext(), initCoinsRequest(id)).observe(getViewLifecycleOwner(), new Observer<CoinsBody>() {
            @Override
            public void onChanged(CoinsBody coinsBody) {
                if (coinsBody.getToken() != null) {
                    if (coinsBody.getToken().equals("error")) {
                        startBaseActivity();
                    }
                }

                if (coinsBody.getStatus().equals("success")) {

                    //проверка на капчу
                    if (coinsBody.getCaptchaImageUrl() != null) {
                        createCaptchaDialog(coinsBody.getCaptchaImageUrl());
                    }

                    //проверка на отзывы
                    if (coinsBody.getPopup() != null) {
                        if (coinsBody.getPopup().equals("comment")) {
                            createReviewDialog();
                        }
                    }

                    GetMonetDialog dialog = new GetMonetDialog();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("model", coinsBody);
                    dialog.setArguments(bundle);
                    dialog.show(getFragmentManager(), "dialog");

                } else {
                    Toast.makeText(getContext(), coinsBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private CoinsRequest initCoinsRequest(int id){
        return new CoinsRequest(
                "coins",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", ""),
                String.valueOf(id)
        );
    }
}
