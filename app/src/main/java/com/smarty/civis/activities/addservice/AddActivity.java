package com.smarty.civis.activities.addservice;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.smarty.civis.R;

public class AddActivity extends AppCompatActivity
{
    private RecyclerView services_recyclerview;
    private ServicesTypesAdapter services_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initActionBar();
        initServicesRecyclerView();
        loadServicesTypes();


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
    private void initServicesRecyclerView()
    {
        services_recyclerview = (RecyclerView) findViewById(R.id.rv_add_form_services);
        services_adapter = new ServicesTypesAdapter(null);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        services_recyclerview.setAdapter(services_adapter);
        services_recyclerview.setLayoutManager(layoutManager);
        services_recyclerview.setHasFixedSize(true);
    }

    /**
     * set Adapter Data
     * @param data
     */
    private void setAdapterData(Cursor data)
    {
        services_adapter.setDataCursor(data);
    }

    /**
     * Load Service Types From DB
     */
    private void loadServicesTypes()
    {
        String columns[] = {"name"};
        MatrixCursor cursor = new MatrixCursor(columns);
        for(int i=0; i<10; i++)
            cursor.addRow(new Object[]{"Service Type "+(i+1)});
        setAdapterData(cursor);
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
