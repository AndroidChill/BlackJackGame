package com.example.blackjackgame.ui.fragment.friends.content.pager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentFriendsContentRequestBinding;
import com.example.blackjackgame.model.friend.request.output.FriendsZapros;
import com.example.blackjackgame.model.friend.request.add.FriendsAddBody;
import com.example.blackjackgame.model.friend.request.del.FriendsDelBody;
import com.example.blackjackgame.model.friend.request.output.FriendsZaprosBody;
import com.example.blackjackgame.network.responce.friend.FriendsZaprosRequest;
import com.example.blackjackgame.network.responce.friend.request.FriendsAddRequest;
import com.example.blackjackgame.network.responce.friend.request.FriendsDelRequest;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.friend.FriendsRequestAdapter;
import com.example.blackjackgame.ui.interfaceClick.friend.FriendRequestOnClick;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.viewmodel.friend.FriendsFactory;
import com.example.blackjackgame.viewmodel.friend.FriendsViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FriendsContentRequestFragment extends Fragment implements FriendRequestOnClick {

    private FragmentFriendsContentRequestBinding binding;
    private FriendsViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private FriendsRequestAdapter adapter;

    private int type = 2;

    public static FriendsContentRequestFragment newInstance() {

        Bundle args = new Bundle();

        FriendsContentRequestFragment fragment = new FriendsContentRequestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_content_request, container, false);
    //
    //        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
    //
    //        viewModel = new ViewModelProvider(this, new FriendsFactory(getActivity().getApplication())).get(FriendsViewModel.class);
    //
    //        FriendsZaprosRequest request = new FriendsZaprosRequest(
    //                "friends_request",
    //                Constant.app_ver,
    //                Constant.ln,
    //                sharedPreferences.getString("token", "null")
    //        );
    //
    //        binding.tv2.setOnClickListener(v -> {
    //            PopupMenu popupMenu = new PopupMenu(getContext(), v);
    //            popupMenu.inflate(R.menu.menu_friends_request);
    //
    //            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
    //                @Override
    //                public boolean onMenuItemClick(MenuItem item) {
    //                    switch (item.getItemId()) {
    //                        case R.id.menu1:
    //                            binding.btn.setText("Входящие");
    //                            viewModel.getRequestFriends(request).observe(getViewLifecycleOwner(), new Observer<FriendsZaprosBody>() {
    //                                @Override
    //                                public void onChanged(FriendsZaprosBody friendsZaprosBody) {
    //
    //                                    if(friendsZaprosBody.getStatus().equals(Constant.success)){
    //                                        List<FriendsZapros> actualList = new ArrayList<>();
    //
    //                                        for(FriendsZapros zapros : friendsZaprosBody.getFriends_request()){
    //                                            if(type == zapros.getType()){
    //                                                actualList.add(zapros);
    //                                            } else {
    //                                                if(type == 2){
    //                                                    actualList.add(zapros);
    //                                                }
    //                                            }
    //                                        }
    //
    //                                        adapter = new FriendsRequestAdapter(actualList, FriendsContentRequestFragment.this::onClick, getFragmentManager());
    //
    //                                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
    //                                        binding.rv.setAdapter(adapter);
    //                                    } else {
    //
    //                                        if(friendsZaprosBody.getError_text().equals(Constant.failed_token)){
    //                                            failedToken();
    //                                        }
    //                                        //TODO: обработка ошибки сервера
    //                                    }
    //                                }
    //                            });
    //                            type = 0;
    //                            return true;
    //                        case R.id.menu2:
    //                            binding.btn.setText("Исходящие");
    //                            viewModel.getRequestFriends(request).observe(getViewLifecycleOwner(), new Observer<FriendsZaprosBody>() {
    //                                @Override
    //                                public void onChanged(FriendsZaprosBody friendsZaprosBody) {
    //
    //                                    if(friendsZaprosBody.getStatus().equals(Constant.success)){
    //                                        List<FriendsZapros> actualList = new ArrayList<>();
    //
    //                                        for(FriendsZapros zapros : friendsZaprosBody.getFriends_request()){
    //                                            if(type == zapros.getType()){
    //                                                actualList.add(zapros);
    //                                            } else {
    //                                                if(type == 2){
    //                                                    actualList.add(zapros);
    //                                                }
    //                                            }
    //                                        }
    //
    //                                        adapter = new FriendsRequestAdapter(actualList, FriendsContentRequestFragment.this::onClick,getFragmentManager());
    //
    //                                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
    //                                        binding.rv.setAdapter(adapter);
    //                                    } else {
    //                                        if(friendsZaprosBody.getError_text().equals(Constant.failed_token)){
    //                                            failedToken();
    //                                        }
    //                                        //TODO: обработка ошибки с сервера
    //                                    }
    //                                }
    //                            });
    //                            type = 1;
    //                            return true;
    //                        case R.id.menu3:
    //                            binding.btn.setText("Все заявки");
    //                            type = 2;
    //                            viewModel.getRequestFriends(request).observe(getViewLifecycleOwner(), new Observer<FriendsZaprosBody>() {
    //                                @Override
    //                                public void onChanged(FriendsZaprosBody friendsZaprosBody) {
    //
    //                                    if(friendsZaprosBody.getStatus().equals(Constant.success)){
    //                                        List<FriendsZapros> actualList = new ArrayList<>();
    //
    //                                        for(FriendsZapros zapros : friendsZaprosBody.getFriends_request()){
    //                                            if(type == zapros.getType()){
    //                                                actualList.add(zapros);
    //                                            } else {
    //                                                if(type == 2){
    //                                                    actualList.add(zapros);
    //                                                }
    //                                            }
    //                                        }
    //
    //                                        adapter = new FriendsRequestAdapter(actualList, FriendsContentRequestFragment.this::onClick, getFragmentManager());
    //
    //                                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
    //                                        binding.rv.setAdapter(adapter);
    //                                    } else {
    //                                        if(friendsZaprosBody.getError_text().equals(Constant.failed_token)){
    //                                            failedToken();
    //                                        }
    //                                        //TODO: обработка ошибки с сервера
    //                                    }
    //
    //                                }
    //                            });
    //                            return true;
    //                        default:
    //                            return false;
    //                    }
    //                }
    //            });
    //
    //            popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
    //                @Override
    //                public void onDismiss(PopupMenu menu) {
    //
    //                }
    //            });
    //
    //            popupMenu.show();
    //
    //        });
    //
    //        viewModel.getRequestFriends(request).observe(getViewLifecycleOwner(), new Observer<FriendsZaprosBody>() {
    //            @Override
    //            public void onChanged(FriendsZaprosBody friendsZaprosBody) {
    //
    //                if(friendsZaprosBody.getStatus().equals(Constant.success)){
    //                    List<FriendsZapros> actualList = new ArrayList<>();
    //
    //                    for(FriendsZapros zapros : friendsZaprosBody.getFriends_request()){
    //                        if(type == zapros.getType()){
    //                            actualList.add(zapros);
    //                        } else {
    //                            if(type == 2){
    //                                actualList.add(zapros);
    //                            }
    //                        }
    //                    }
    //
    //                    adapter = new FriendsRequestAdapter(actualList, FriendsContentRequestFragment.this::onClick, getFragmentManager());
    //
    //                    binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
    //                    binding.rv.setAdapter(adapter);
    //                } else {
    //                    if(friendsZaprosBody.getError_text().equals(Constant.failed_token)){
    //                        failedToken();
    //                    }
    //                    //TODO: обработка ошибки с сервера
    //                }
    //            }
    //        });

        return binding.getRoot();
    }

    private void failedToken(){
        //обнуляем вход
        sharedPreferences.edit().putBoolean(Constant.isSign, false).apply();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        ((NavigationActivity)getActivity()).finish();
    }

    @Override
    public void onClick(String type, long id) {
        switch(type) {
            case "add" :

                FriendsAddRequest friendsAddRequest = new FriendsAddRequest(
                        "friends_request_add",
                        Constant.app_ver,
                        Constant.ln,
                        sharedPreferences.getString("token", "null"),
                        id
                );

                viewModel.addFriend(friendsAddRequest).observe(getViewLifecycleOwner(), new Observer<FriendsAddBody>() {
                    @Override
                    public void onChanged(FriendsAddBody friendsAddBody) {
                        if(friendsAddBody.getStatus().equals(Constant.error)){
                            Snackbar.make(binding.main, friendsAddBody.getError_text(), BaseTransientBottomBar.LENGTH_LONG).show();
                        } else {
                            if(friendsAddBody.getStatus().equals(Constant.success)){
                                Snackbar.make(binding.main, "Пользователь успешно добавлен в друзья", BaseTransientBottomBar.LENGTH_INDEFINITE).show();
                            }
                        }
                    }
                });

                break;

            case "close" :

                FriendsDelRequest friendsDelRequest = new FriendsDelRequest(
                        "friends_request_del",
                        Constant.app_ver,
                        Constant.ln,
                        sharedPreferences.getString("token", "null"),
                        id
                );

                viewModel.delFriend(friendsDelRequest).observe(getViewLifecycleOwner(), new Observer<FriendsDelBody>() {
                    @Override
                    public void onChanged(FriendsDelBody friendsDelBody) {
                        if(friendsDelBody.getStatus().equals(Constant.success)){
                            Snackbar.make(binding.main, "Текущая заявка отклонена", BaseTransientBottomBar.LENGTH_LONG).show();
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void showUser(int id) {

    }
}
