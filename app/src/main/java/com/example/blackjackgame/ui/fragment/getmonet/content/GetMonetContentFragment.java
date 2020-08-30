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

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentGetMonetContentBinding;
import com.example.blackjackgame.model.getmonet.CoinsGet;
import com.example.blackjackgame.model.getmonet.GetMonetBody;
import com.example.blackjackgame.model.getmonet.finish.GetMonetFinishBody;
import com.example.blackjackgame.network.responce.getmonet.GetMonetFinishRequest;
import com.example.blackjackgame.network.responce.getmonet.GetMonetRequest;
import com.example.blackjackgame.ui.activity.AdActivity;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.getmonet.GetMonetAdapter;
import com.example.blackjackgame.ui.fragment.getmonet.content.info.GetTaskMonetFragment;
import com.example.blackjackgame.ui.interfaceClick.getmonet.GetMonetOnClick;
import com.example.blackjackgame.viewmodel.getmonet.GetMonetFactory;
import com.example.blackjackgame.viewmodel.getmonet.GetMonetViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class GetMonetContentFragment extends Fragment implements GetMonetOnClick {

    private FragmentGetMonetContentBinding binding;
    private GetMonetViewModel viewModel;

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

        viewModel = new ViewModelProvider(this, new GetMonetFactory(getActivity().getApplication())).get(GetMonetViewModel.class);
        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        GetMonetRequest request = new GetMonetRequest(
                "coins_get",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "null")
        );

        viewModel.getTasks(request).observe(getViewLifecycleOwner(), new Observer<GetMonetBody>() {
            @Override
            public void onChanged(GetMonetBody getMonetBody) {

                if(getMonetBody.getStatus().equals(Constant.success)){
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.recyclerView.setAdapter(new GetMonetAdapter(getMonetBody.getCoins_get(), GetMonetContentFragment.this::onClick));
                } else {
                    if(getMonetBody.getError_text().equals(Constant.failed_token)){
                        tokenFailed();
                    }
                    //TODO: обработка ошибки с сервера
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NavigationActivity)getActivity()).getSupportActionBar().show();

        //обработка выполненного задания

        if(sharedPreferences.getBoolean(Constant.isCompleteTask, false)){

            int idTask = sharedPreferences.getInt(Constant.idCompleteTask, 0);

            GetMonetFinishRequest request = new GetMonetFinishRequest(
                    "coins_get_finish",
                    Constant.app_ver,
                    Constant.ln,
                    sharedPreferences.getString("token", "null"),
                    idTask
            );

            viewModel.postFinishTask(request).observe(getViewLifecycleOwner(), new Observer<GetMonetFinishBody>() {
                @Override
                public void onChanged(GetMonetFinishBody getMonetFinishBody) {
                    if(getMonetFinishBody.getStatus().equals(Constant.success)){

                        Snackbar.make(binding.recyclerView, "Получено монет " + sharedPreferences.getLong("coins_task", 0), Snackbar.LENGTH_LONG)
                                .show();

                        sharedPreferences.edit().putBoolean(Constant.isCompleteTask, false).apply();
                    } else {
                        if(getMonetFinishBody.getError_text().equals(Constant.failed_token)){
                            tokenFailed();
                        }
                        //TODO: обработка ошибки с сервера
                    }
                }
            });
        }

    }

    @Override
    public void onClick(CoinsGet task) {
        sharedPreferences.edit().putLong("coins_task", task.getCoins()).apply();
        sharedPreferences.edit().putInt(Constant.idCompleteTask, (int)task.getCoins()).apply();

        Intent intent = new Intent(getContext(), AdActivity.class);
        startActivity(intent);
    }

    private void tokenFailed(){
        sharedPreferences.edit().putBoolean(Constant.isSign, false).apply();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        ((NavigationActivity)getActivity()).finish();
    }
}
