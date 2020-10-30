package com.example.blackjackgame.ui.dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
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

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.DialogProfileEditAvatarBinding;
import com.example.blackjackgame.model.profile.avatar.Avatar;
import com.example.blackjackgame.model.profile.avatar.AvatarBody;
import com.example.blackjackgame.network.responce.profile.avatar.AvatarChangeRequest;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.example.blackjackgame.viewmodel.profile.ProfileFactory;
import com.example.blackjackgame.viewmodel.profile.ProfileViewModel;

public class ProfileChangePhotoDialogFragment extends DialogFragment {

    private DialogProfileEditAvatarBinding binding;

    private ProfileViewModel viewModel;

    private String select = "";

    private int[] coast = new int[3];
    private int[] images = new int[3];

    private SharedPreferences shared;
    static ImageView view;

    public static ProfileChangePhotoDialogFragment newInstance(ImageView view) {

        Bundle args = new Bundle();

        ProfileChangePhotoDialogFragment.view = view;

        ProfileChangePhotoDialogFragment fragment = new ProfileChangePhotoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_profile_edit_avatar, container, false);

        shared = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        viewModel = new ViewModelProvider(this, new ProfileFactory(getActivity().getApplication())).get(ProfileViewModel.class);

        AvatarChangeRequest request = new AvatarChangeRequest(
                "avatar_change",
                Constant.app_ver,
                Constant.ln,
                shared.getString("token", "null")
        );

        viewModel.getAvatarList(request).observe(this, o -> {
            Toast.makeText(getContext(), o.first, Toast.LENGTH_SHORT).show();
            if(o.first.equals("Error")){
                Toast.makeText(getContext(), "Нет соединения", Toast.LENGTH_SHORT).show();
            }
        });

//        viewModel.getAvatarList(request).second.observe(this, o -> {
//            binding.coins1.setText(String.valueOf(o.getAvatar().get(0).getCoast()));
//            binding.coins2.setText(String.valueOf(o.getAvatar().get(1).getCoast()));
//            binding.coins.setText(String.valueOf(o.getAvatar().get(2).getCoast()));
//
//            for(int i = 0; i < 3; i++){
//                coast[i] = o.getAvatar().get(i).getCoast();
//
//                if(o.getAvatar().get(i).getImage().equals("avatar1.png")){
//                    images[i] = R.drawable.avatar1;
//                } else {
//                    if(o.getAvatar().get(i).getImage().equals("avatar2.png")){
//                        images[i] = R.drawable.avatar2;
//                    } else {
//                        images[i] = R.drawable.avatar3;
//                    }
//                }
//
//            }
//
//            binding.avatar1.setImageResource(images[0]);
//            binding.avatar2.setImageResource(images[1]);
//            binding.avatar3.setImageResource(images[2]);
//
//            binding.coinsItog.setText(String.valueOf(coast[0]));
//        });




        binding.layout1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                binding.layout2.setForeground(getResources().getDrawable(R.color.dark_back));
                binding.layout3.setForeground(getResources().getDrawable(R.color.dark_back));
                binding.layout1.setForeground(getResources().getDrawable(R.color.white_back));

                binding.coinsItog.setText(String.valueOf(coast[0]));


                select = "avatar1.png";


            }
        });

        binding.layout2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                binding.layout1.setForeground(getResources().getDrawable(R.color.dark_back));
                binding.layout3.setForeground(getResources().getDrawable(R.color.dark_back));
                binding.layout2.setForeground(getResources().getDrawable(R.color.white_back));

                binding.coinsItog.setText(String.valueOf(coast[1]));

                select = "avatar2.png";
}
        });

        binding.layout3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                binding.layout2.setForeground(getResources().getDrawable(R.color.dark_back));
                binding.layout1.setForeground(getResources().getDrawable(R.color.dark_back));
                binding.layout3.setForeground(getResources().getDrawable(R.color.white_back));

                binding.coinsItog.setText(String.valueOf(coast[2]));

                select = "avatar3.png";

            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shared.edit().putBoolean("isEditImage", true).apply();
                shared.edit().putString("selectImage", select).apply();
                onDismiss(getDialog());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ConvertStringToImage.convert(ProfileChangePhotoDialogFragment.view, select);
        shared.edit().putString("selectImage", select).commit();
    }
}
