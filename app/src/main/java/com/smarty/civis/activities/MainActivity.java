package com.smarty.civis.activities;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.smarty.civis.R;
import com.smarty.civis.adapters.ListFragmentPagerAdapter;
import com.smarty.civis.data.TaskUpdateService;
import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.models.Task;
import com.smarty.civis.models.User;
import com.smarty.civis.viewmodel.CivisViewModel;

import static android.R.attr.id;
import static com.smarty.civis.models.Task.ACTIVE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ListFragmentPagerAdapter(getSupportFragmentManager(), this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        Task task = new Task("Help", "Babysit the kids", "Home", 15.3, true, System.currentTimeMillis() + 100000, "London", ACTIVE, 1, -1);

//        TaskUpdateService.insertNewTask(this, task.getContentValues());

        task.setId(1);
        updateTaskStatus(task, 4);
    }

    void updateTaskStatus(Task task, int newStatus){
        Uri uri = ContentUris.withAppendedId(CivisContract.TASKS_CONTENT_URI, task.getId());
        ContentValues contentValues = new ContentValues();
        contentValues.put(TasksTable.Entry.COLUMN_STATUS, newStatus);
        TaskUpdateService.updateTask(this, uri, contentValues);
        Log.i("==================", "Task status updated. Built URI: " + uri.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
