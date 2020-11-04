package com.example.blackjackgame.ui.fragment.getmonet.content;

import android.content.Context;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentGetMonetContentBinding;
import com.example.blackjackgame.model.getmonet.GetMonetBody;
import com.example.blackjackgame.model.profile.ProfileBody;
import com.example.blackjackgame.network.responce.getmonet.GetMonetRequest;
import com.example.blackjackgame.ui.dialog.CaptchaDialog;
import com.example.blackjackgame.ui.dialog.ReviewDialogHelper;
import com.example.blackjackgame.viewmodel.rightGetMonet.GetMonetFactory;
import com.example.blackjackgame.viewmodel.rightGetMonet.GetMonetViewModel;

import org.jetbrains.annotations.NotNull;

public class RightGetMonetContentFragment extends Fragment {

    private FragmentGetMonetContentBinding binding;
    private GetMonetViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private GetMonetRequest request;

    public static RightGetMonetContentFragment newInstance() {

        Bundle args = new Bundle();

        RightGetMonetContentFragment fragment = new RightGetMonetContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_monet_content, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        request = initRequest();

        viewModel = new ViewModelProvider(this, new GetMonetFactory(request)).get(GetMonetViewModel.class);

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        return binding.getRoot();
    }

    private GetMonetRequest initRequest(){
        return new GetMonetRequest(
                "coins_get",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("shared", null)
        );
    }

    private void refresh(){
        request = initRequest();
        viewModel.update(request);
        updateUI();
        binding.refresh.setRefreshing(false);
    }

    private void updateUI(){
        viewModel.getLiveData().observe(this, new Observer<GetMonetBody>() {
            @Override
            public void onChanged(GetMonetBody getMonetBody) {
                if(getMonetBody.getStatus().equals("error")){
                    Toast.makeText(getContext(), getMonetBody.getError_text(), Toast.LENGTH_SHORT).show();
                } else {
                    if(getMonetBody.getToken().equals("error")){
                        //auf
                    }
                    if(getMonetBody.getImage_captcha_url() != null){
                        createCaptchaDialog(getMonetBody.getImage_captcha_url());
                    }
                    if(getMonetBody.getPopup() != null){
                        if(getMonetBody.getPopup().equals("comment")){
                            createReviewDialog();
                        }
                    }
                }
            }
        });
    }

    //создание диалога с капчей
    private void createCaptchaDialog(String imageUrl){
        CaptchaDialog.createCaptchaDialog(
                getContext(),
                getLayoutInflater(),
                imageUrl,
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
}
