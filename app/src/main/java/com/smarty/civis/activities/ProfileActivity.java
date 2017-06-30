package com.smarty.civis.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.smarty.civis.R;
import com.smarty.civis.fragments.OfferedTasksFragment;
import com.smarty.civis.fragments.ProfileFragment;
import com.smarty.civis.fragments.RequestedTasksFragment;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (findViewById(R.id.fragment_container) != null) {
            // Check if we're being restored from a previous state
            if (savedInstanceState != null) {
                return;
            }

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ProfileFragment.newInstance())
                    .commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            boolean itemExists = true;
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.nav_profile:
                    fragment = ProfileFragment.newInstance();
                    break;
                case R.id.nav_jobs_requested:
                    fragment = RequestedTasksFragment.newInstance();
                    break;
                case R.id.nav_jobs_offered:
                    fragment = OfferedTasksFragment.newInstance();
                    break;
                default:
                    itemExists = false;
            }
            if (itemExists) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
            }
            return false;
        }
    };
}
