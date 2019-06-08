package com.example.walden;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavBar;

    //todo: use phone storage to store these data
    public static boolean[] turned_on = new boolean[] {false, false, false};
    public static boolean[] edit_mode = new boolean[] {false, false, false};  // walden, breaks, app restriction
    public static boolean[] display_mode = new boolean[] {false, false, false};
    public static int interval_waldenZone = 0;
    public static int hour_waldenZone = 0;
    public static int minute_waldenZone = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomNavigation();
        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setBottomNavigation() {
        bottomNavBar = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SetControlFragment()).commit();
        bottomNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                String selectedLabel = null;
                switch (menuItem.getItemId()) {
                    case R.id.title1:
                        selectedFragment = new SetControlFragment();
                        selectedLabel = "Set Control";
                        break;
                    case R.id.title2:
                        selectedFragment = new DailyChallengeFragment();
                        selectedLabel = "Daily Challenge";
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                getSupportActionBar().setTitle(selectedLabel);
                return true;
            }
        });
    }
}