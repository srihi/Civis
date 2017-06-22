package com.smarty.civis.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.smarty.civis.R;

public class AddActivity extends AppCompatActivity
{
    private String[] serviceTypes = {
            "Service Type1",
            "Service Type2",
            "Service Type3"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initActionBar();
        initServiceTypes();


    }

    /**
     * Init Action Bar [ Title and buttons ]
     */
    private void initActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.add_screen_activity_title));

        }
    }

    /**
     * Init Service Types
     */
    private void initServiceTypes()
    {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_add_form);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_spinner_service_type,
                R.id.tv_service_type_name, serviceTypes);
        spinner.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            this.finish();
            return true;
        }
        return false;
    }
}
