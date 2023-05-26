package com.patnacollege.sims;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeNavigationActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    private Fragment homeFragment;
    private Fragment scheduleFragment;
    private Fragment assignmentFragment;
    private Fragment courseFragment;

    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);


        ImageButton menuBtn = findViewById(R.id.menu_button);
        menuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeNavigationActivity.this, OptionsActivity.class);
            startActivity(intent);
        });


        navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    switchFragment(homeFragment);
                    return true;
                } else if (item.getItemId() == R.id.action_schedule) {
                    switchFragment(scheduleFragment);
                    return true;
                } else if (item.getItemId() == R.id.action_assignment) {
                    switchFragment(assignmentFragment);
                    return true;
                } else if (item.getItemId() == R.id.action_materials) {
                    switchFragment(courseFragment);
                    return true;
                }
                return false;
            }
        });

        homeFragment = new HomeFragment();
        scheduleFragment = new ScheduleFragment();
        assignmentFragment = new AssignmentFragment();
        courseFragment = new CourseFragment();

        // Set the initial active fragment
        activeFragment = homeFragment;
        switchFragment(activeFragment);
    }
    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment);
        }

        if (activeFragment != null && activeFragment != fragment) {
            fragmentTransaction.hide(activeFragment);
        }
        fragmentTransaction.commit();
        activeFragment = fragment;
    }
}