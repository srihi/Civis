package com.smarty.civis.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.smarty.civis.R;
import com.smarty.civis.fragments.ProfileFragment;

public class ProfileActivity extends AppCompatActivity {

    private TextView mTextMessage;
//    final Fragment profileFragment = ProfileFragment/.newInstance("1", "2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (findViewById(R.id.fragment_container) != null) {
            // Check if we're being restored from a previous state
            if (savedInstanceState != null) {
                return;
            }

            ProfileFragment profileFragment = new ProfileFragment();

            // Pass the Intent's extras to the fragment as arguments
            profileFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, profileFragment).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_profile:
                    ProfileFragment profileFragment = new ProfileFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, profileFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.nav_jobs_performed:
                    mTextMessage.setText(R.string.title_performed);
                    return true;
                case R.id.nav_jobs_offered:
                    mTextMessage.setText(R.string.title_offered);
                    return true;
            }
            return false;
        }

    };

}
