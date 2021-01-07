package com.example.blackjackgame.ui.dialog;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.DialogProfileEditAvatarBinding;
import com.example.blackjackgame.model.profile.avatar.AvatarBody;
import com.example.blackjackgame.network.responce.profile.DataProfileRequest;
import com.example.blackjackgame.network.responce.profile.avatar.AvatarChangeRequest;
import com.example.blackjackgame.rModel.Avatar;
import com.example.blackjackgame.rViewModel.profile.ProfileFactory;
import com.example.blackjackgame.rViewModel.profile.ProfileViewModel;
import com.example.blackjackgame.ui.activity.ProfileEditActivity;
import com.example.blackjackgame.ui.adapter.AvatarChangeAdapter;
import com.example.blackjackgame.ui.interfaceClick.ChangeAvatarClick;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileFactory;
import com.example.blackjackgame.viewmodel.rigthProfile.RightProfileViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProfileChangePhotoDialogFragment extends DialogFragment implements ChangeAvatarClick {

    private DialogProfileEditAvatarBinding binding;
    private AvatarChangeAdapter adapter;

    private ProfileViewModel viewModel;

    private String select = "";

    private SharedPreferences shared;
    static ImageView view;

    public static ProfileChangePhotoDialogFragment newInstance() {

        Bundle args = new Bundle();

        ProfileChangePhotoDialogFragment fragment = new ProfileChangePhotoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_profile_edit_avatar, container, false);

        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        adapter = new AvatarChangeAdapter(getContext(), binding.coinsItog, this::onClick);
        Log.d("tag", "onCreateView: adapter");
        List<Avatar> avatars = ((ProfileEditActivity)getActivity()).getAvatar();
        Log.d("tag", "onCreateView: list");
        adapter.setAvatars(avatars);
        Log.d("tag", "onCreateView: setAvatars");
        StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerView3.setLayoutManager(_sGridLayoutManager);
        binding.recyclerView3.setAdapter(adapter);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileChangePhotoDialogFragment.this.dismiss();
            }
        });

        return binding.getRoot();
    }

    @Override
    public boolean onClick(Avatar avatar) {
        return ((ProfileEditActivity)getActivity()).setAvatar(avatar);
    }
}
