package com.example.blackjackgame.ui.fragment.friends.content.pager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.FragmentFriendsContentAllBinding;
import com.example.blackjackgame.model.friend.Friend;
import com.example.blackjackgame.model.friend.Global.FriendGlobalBody;
import com.example.blackjackgame.network.responce.friend.FriendRequest;
import com.example.blackjackgame.network.responce.friend.FriendSearchRequest;
import com.example.blackjackgame.ui.activity.MainActivity;
import com.example.blackjackgame.ui.activity.NavigationActivity;
import com.example.blackjackgame.ui.adapter.friend.FriendsAllAdapter;
import com.example.blackjackgame.ui.dialog.UserInfoDialogFragment;
import com.example.blackjackgame.ui.interfaceClick.friend.MyFriendOnClick;
import com.example.blackjackgame.viewmodel.friend.FriendsFactory;
import com.example.blackjackgame.viewmodel.friend.FriendsViewModel;
import com.example.blackjackgame.viewmodel.profile.ProfileFactory;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;

import java.util.ArrayList;
import java.util.List;

public class FriendsContentAllFragment extends Fragment implements MyFriendOnClick {

    private FragmentFriendsContentAllBinding binding;
    private FriendsAllAdapter adapter;
    private FriendsViewModel viewModel;
    private SharedPreferences shared;
    private FriendsAllAdapter friendsAllAdapter;

    private List<Friend> friends = new ArrayList<>();

    private boolean activeMyFriends = true;
    private boolean activeGlobal = false;

    public static FriendsContentAllFragment newInstance() {

        Bundle args = new Bundle();

        FriendsContentAllFragment fragment = new FriendsContentAllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends_content_all, container, false);

        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        viewModel =  new ViewModelProvider(this, new FriendsFactory(getActivity().getApplication())).get(FriendsViewModel.class);

        FriendRequest request = new FriendRequest(
                "friends",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "null")
        );

        viewModel.getFriends(request).observe(getViewLifecycleOwner(), o -> {

            adapter = new FriendsAllAdapter(o.getFriends(), this::onClick);

            binding.recyclerViewMyFriend.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recyclerViewMyFriend.setAdapter(adapter);

            binding.btnSearch.setOnClickListener(v -> {

                binding.cvGlobal.setVisibility(View.VISIBLE);

                String search = binding.search.getText().toString();

                friends = o.getFriends();

                List<Friend> currentList = new ArrayList<>();
                List<Friend> currentGlobalList = new ArrayList<>();

                for(Friend friend : friends){
                    if(friend.getNick().contains(search)){
                        currentList.add(friend);
                    }
                }

                adapter = new FriendsAllAdapter(currentList, this::onClick);

                binding.recyclerViewMyFriend.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerViewMyFriend.setAdapter(adapter);

                FriendSearchRequest searchRequest = new FriendSearchRequest(
                        "friend_search",
                        Constant.app_ver,
                        Constant.ln,
                        shared.getString("token", "null"),
                        search
                );

                viewModel.getGlobalFriends(searchRequest).observe(getViewLifecycleOwner(), new Observer<FriendGlobalBody>() {
                    @Override
                    public void onChanged(FriendGlobalBody friendGlobalBody) {
                        if(friendGlobalBody.getStatus().equals(Constant.success)){
                            for(Friend friend : friendGlobalBody.getFriends()){
                                if(friend.getNick().contains(search)){
                                    currentGlobalList.add(friend);
                                }
                            }
                            friendsAllAdapter = new FriendsAllAdapter(currentGlobalList, FriendsContentAllFragment.this::onClick);

                            binding.recyclerViewGlobal.setLayoutManager(new LinearLayoutManager(getContext()));
                            binding.recyclerViewGlobal.setAdapter(friendsAllAdapter);
                        } else {
                            if(friendGlobalBody.getError_text().equals(Constant.failed_token)){
                                failedToken();
                            }
                            //TODO: обработка ошибки с сервера
                        }
                    }
                });
            });
        });

        activeCardMyFriend();

        return binding.getRoot();
    }

    private void activeCardMyFriend(){
        binding.cardMyfriend.setOnClickListener(v -> {
            if(!activeMyFriends){
                activeMyFriends = !activeMyFriends;

                binding.checkMyFriends.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

                binding.recyclerViewMyFriend.setVisibility(View.VISIBLE);

                final Context context = binding.recyclerViewMyFriend.getContext();
                final LayoutAnimationController controller =
                        AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right);

                binding.recyclerViewMyFriend.setLayoutAnimation(controller);
                binding.recyclerViewMyFriend.getAdapter().notifyDataSetChanged();
                binding.recyclerViewMyFriend.scheduleLayoutAnimation();
            } else {
                activeMyFriends = !activeMyFriends;
                binding.checkMyFriends.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                binding.recyclerViewMyFriend.setVisibility(View.GONE);
            }
        });

        binding.cvGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(activeGlobal){
                    activeGlobal = !activeGlobal;

                    binding.checkGlobal.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

                    binding.recyclerViewGlobal.setVisibility(View.VISIBLE);

                    final Context context = binding.recyclerViewGlobal.getContext();
                    final LayoutAnimationController controller =
                            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right);

                    binding.recyclerViewGlobal.setLayoutAnimation(controller);
                    binding.recyclerViewGlobal.scheduleLayoutAnimation();
                } else {
                    activeGlobal = !activeGlobal;
                    binding.recyclerViewGlobal.setVisibility(View.GONE);
                    binding.checkGlobal.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
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
        shared.edit().putBoolean(Constant.isSign, false).apply();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        ((NavigationActivity)getActivity()).finish();
    }
}
