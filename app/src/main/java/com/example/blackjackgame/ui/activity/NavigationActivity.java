package com.example.blackjackgame.ui.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.blackjackgame.R;
import com.example.blackjackgame.data.Constant;
import com.example.blackjackgame.databinding.ActivityNavigationBinding;
import com.example.blackjackgame.databinding.NavHeaderMainBinding;
import com.example.blackjackgame.rModel.profile.ProfileBody;
import com.example.blackjackgame.rNetwork.request.profile.ProfileRequest;
import com.example.blackjackgame.rViewModel.profile.ProfileFactory;
import com.example.blackjackgame.rViewModel.profile.ProfileViewModel;
import com.example.blackjackgame.ui.fragment.profile.ProfileFragment;
import com.example.blackjackgame.ui.fragment.profile.content.ProfileContentFragment;
import com.example.blackjackgame.util.ConvertStringToImage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NavigationActivity extends AppCompatActivity {

    private ActivityNavigationBinding binding;
    private ProfileViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private AppBarConfiguration mAppBarConfiguration;
    public static FragmentManager fragmentManager;
    private NavHeaderMainBinding _bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("БлаБлабЛа");
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);
        viewModel = new ViewModelProvider(getViewModelStore(), new ProfileFactory(initRequest())).get(ProfileViewModel.class);

        fragmentManager = getSupportFragmentManager();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID card_as a set of Ids because each
        // menu should be considered card_as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_news,
                R.id.nav_profile,
                R.id.nav_rating,
                R.id.nav_friends,
                R.id.nav_get_monet,
                R.id.nav_rules,
                R.id.nav_tournament,
                R.id.nav_start_game,
                R.id.nav_history_coins
        )
                .setDrawerLayout(drawer)
                .build();

        _bind = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header_main, binding
                .navView, false);
        binding.navView.addHeaderView(_bind.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        refresh();
        updateUI();

    }

    private void refresh(){
        _bind.refresh.setOnClickListener(v -> {
            viewModel.update(initRequest());
            updateUI();
        });
    }

    private void updateUI(){
        viewModel.getLiveData().observe(this, new Observer<ProfileBody>() {
            @Override
            public void onChanged(ProfileBody profileBody) {
                if(profileBody.getStatus().equals("success")){
                    _bind.userId.setText(String.valueOf(profileBody.getProfile().getId()));
                    _bind.userNickname.setText(profileBody.getProfile().getNickname());
                    _bind.coins.setText(String.valueOf(profileBody.getProfile().getCoins()));
                    ConvertStringToImage.convert(_bind.avatar, profileBody.getProfile().getAvatar());
                } else {
                    Toast.makeText(NavigationActivity.this, profileBody.getStatusText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ProfileRequest initRequest(){
        return new ProfileRequest(
                "profile",
                Constant.app_ver,
                Constant.ln,
                sharedPreferences.getString("token", "")
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_rules:
                Log.d("tag", "onOptionsItemSelected: 123");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
