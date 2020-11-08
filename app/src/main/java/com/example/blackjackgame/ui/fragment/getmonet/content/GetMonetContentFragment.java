package com.example.blackjackgame.ui.fragment.getmonet.content;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentGetMonetContentBinding;
import com.example.blackjackgame.model.getmonet.GetMonetBody;
import com.example.blackjackgame.model.getmonet.finish.GetMonetFinishBody;
import com.example.blackjackgame.network.responce.getmonet.GetMonetFinishRequest;
import com.example.blackjackgame.network.responce.getmonet.GetMonetRequest;
import com.example.blackjackgame.rModel.coinsGet.CoinsGet;
import com.example.blackjackgame.rModel.coinsGet.CoinsGetBody;
import com.example.blackjackgame.rNetwork.request.coinsGet.CoinsGetRequest;
import com.example.blackjackgame.rViewModel.getMonet.GetMonetFactory;
import com.example.blackjackgame.rViewModel.getMonet.GetMonetViewModel;
import com.example.blackjackgame.ui.activity.AdActivity;
import com.example.blackjackgame.ui.activity.InfoGetMonetActivity;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.getmonet.GetMonetAdapter;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.ui.fragment.getmonet.content.info.GetTaskMonetFragment;
import com.example.blackjackgame.ui.interfaceClick.getmonet.GetMonetOnClick;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class GetMonetContentFragment extends Fragment implements GetMonetOnClick {

    private FragmentGetMonetContentBinding binding;
    private GetMonetViewModel viewModel;
    private CoinsGetRequest request;
    private GetMonetAdapter adapter;

    private SharedPreferences sharedPreferences;

    public static GetMonetContentFragment newInstance() {

        Bundle args = new Bundle();

        GetMonetContentFragment fragment = new GetMonetContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_monet_content, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        initRequest();
        viewModel = new ViewModelProvider(getViewModelStore(), new GetMonetFactory(request)).get(GetMonetViewModel.class);

        binding.setViewModel(viewModel);

        refresh();
        updateUI();
        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void refresh(){
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRequest();
                viewModel.update(request);
                updateUI();
                binding.refresh.setRefreshing(false);
            }
        });
    }

    private void updateUI(){
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<CoinsGetBody>() {
            @Override
            public void onChanged(CoinsGetBody coinsGetBody) {
                //проверка на токен
                if(coinsGetBody.getToken() != null){
                    if(coinsGetBody.getToken().equals("error")){
                        startBaseActivity();
                    }
                }

                if(coinsGetBody.getStatus().equals("success")){

                    //проверка на капчу
                    if(coinsGetBody.getImageCaptchaUrl() != null){
                        createCaptchaDialog(coinsGetBody.getImageCaptchaUrl());
                    }

                    //проверка на отзывы
                    if(coinsGetBody.getPopup() != null){
                        if(coinsGetBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }

                    adapter = new GetMonetAdapter(coinsGetBody.getCoinsGets(), GetMonetContentFragment.this::onClick);
                    binding.recyclerView.setAdapter(adapter);

            } else {
                    Toast.makeText(getContext(), coinsGetBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
        }});
    }

    private void startBaseActivity(){
        startActivity(new Intent(getContext(), MainActivity.class));
        ((NavigationActivity)getActivity()).finish();
    }

    //создание диалога с капчей
    private void createCaptchaDialog(String url){
        CaptchaDialog.createCaptchaDialog(
                getContext(),
                getLayoutInflater(),
                url,
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

    //создание диалога с отзывами
    private void createReviewDialog(){
        ReviewDialogHelper.buildReview(
                getContext(),
                getLayoutInflater(),
                getViewModelStore(),
                getViewLifecycleOwner()
        );
    }

    private void initRequest(){
        request = new CoinsGetRequest(
                "coins_get",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }

    @Override
    public void onClick(CoinsGet task) {
        Intent intent = new Intent(getContext(), InfoGetMonetActivity.class);
        Bundle args = new Bundle();
        args.putParcelable("task", task);
        intent.putExtras(args);
        startActivity(intent);
    }
}
