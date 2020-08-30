package com.example.blackjackgame.ui.fragment.friends.content.pager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentFriendsContentReferralsBinding;
import com.example.blackjackgame.model.friend.Friend;
import com.example.blackjackgame.model.friend.referrals.ReferralsBody;
import com.example.blackjackgame.network.responce.friend.FriendReferalsRequest;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.friend.FriendsReferralsAdapter;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.viewmodel.friend.FriendsFactory;
import com.example.blackjackgame.viewmodel.friend.FriendsViewModel;

public class FriendsContentReferralsFragment extends Fragment implements MyFriendOnClick {

    private FragmentFriendsContentReferralsBinding binding;
    private FriendsReferralsAdapter adapter;
    private FriendsViewModel viewModel;
    private SharedPreferences sharedPreferences;

    public static FriendsContentReferralsFragment newInstance() {

        Bundle args = new Bundle();

        FriendsContentReferralsFragment fragment = new FriendsContentReferralsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_content_referrals, container, false);

        viewModel = new ViewModelProvider(this, new FriendsFactory(getActivity().getApplication())).get(FriendsViewModel.class);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        FriendReferalsRequest request = new FriendReferalsRequest(
                "referals",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "null")
        );

        viewModel.getReferalsFriends(request).observe(getViewLifecycleOwner(), new Observer<ReferralsBody>() {
            @Override
            public void onChanged(ReferralsBody referralsBody) {

                if(referralsBody.getStatus().equals(Constant.success)){
                    adapter = new FriendsReferralsAdapter(referralsBody.getReferals(), FriendsContentReferralsFragment.this::onClick);

                    binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.rv.setAdapter(adapter);
                } else {
                    if(referralsBody.getError_text().equals(Constant.failed_token)){
                        failedToken();
                    }//TODO: обработка ошибки с сервера
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onClick(String name) {
        UserInfoDialogFragment dialog = new UserInfoDialogFragment();
        Bundle bundle = new Bundle();
        dialog.setArguments(bundle);
        bundle.putString("nick_user", name);
        dialog.show(getFragmentManager(), "dialog");
    }

    private void failedToken(){
        //обнуляем вход
        sharedPreferences.edit().putBoolean(Constant.isSign, false).apply();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        ((NavigationActivity)getActivity()).finish();
    }

}
