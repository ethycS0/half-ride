package com.arjun.halfride.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.arjun.halfride.Fragments.ChatFragment;
import com.arjun.halfride.Fragments.HomeFragment;
import com.arjun.halfride.Fragments.NotificationFragment;
import com.arjun.halfride.Fragments.ProfileFragment;
import com.arjun.halfride.R;
import com.arjun.halfride.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    NotificationFragment notificationFragment =  new NotificationFragment();
    ChatFragment chatFragment = new ChatFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    private ListView listview;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    return true;
                } else if (itemId == R.id.notification) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, notificationFragment).commit();
                    return true;
                } else if (itemId == R.id.chat) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).commit();
                    return true;
                } else if (itemId == R.id.profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                    return true;
                }


                return false;
            }
        });

    }


}
