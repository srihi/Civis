package com.smarty.civis.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.smarty.civis.R;
import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.data.tables.UsersTable;
import com.smarty.civis.models.Task;
import com.smarty.civis.utils.PrefUtils;
import com.smarty.civis.utils.ProjectionUtils;

import java.util.Date;

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String ARG_TASK = "mTask";

    private static final int LOADER_ID = 901905;

    private int userId;

    private int taskId;

    TextView title;

    TextView description;

    TextView location;

    TextView category;

    TextView day;

    TextView month;

    TextView price;

    TextView userName;

    ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title = (TextView) findViewById(R.id.details_title);
        description = (TextView) findViewById(R.id.details_description);
        location = (TextView) findViewById(R.id.details_location);
        category = (TextView) findViewById(R.id.category);
        day = (TextView) findViewById(R.id.day);
        month = (TextView) findViewById(R.id.month);
        price = (TextView) findViewById(R.id.price);
        userName = (TextView) findViewById(R.id.details_user_name);
        scrollView = (ScrollView) findViewById(R.id.details_scrollview);


        Intent intent = getIntent();
        if (intent.hasExtra(ARG_TASK)) {
            loadData((Task) intent.getParcelableExtra(ARG_TASK));
        }
    }

    private void loadData(Task task) {
        price.setText("$" + task.getReward());
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        Date date = new Date(task.getEndTime());
        String _day = (String) DateFormat.format("dd", date);
        String _monthString = (String) DateFormat.format("MMM", date);
        day.setText(_day);
        month.setText(_monthString);
        category.setText(task.getJobType());
        location.setText(task.getLocation());
        userId = task.getOwnerId();
        taskId = task.getId();
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

    }

    public void button(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TasksTable.Entry.COLUMN_STATUS, Task.RESERVED);
        contentValues.put(TasksTable.Entry.COLUMN_TAKEN_BY_ID, PrefUtils.getUserId(this));
        Uri queryUri = CivisContract.BASE_CONTENT_URI.buildUpon()
                .appendPath(TasksTable.PATH_TASKS)
                .appendPath(Integer.toString(taskId))
                .build();
        getContentResolver().update(queryUri, contentValues, null, null);
        finish();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri queryUri = CivisContract.BASE_CONTENT_URI.buildUpon()
                .appendPath(UsersTable.PATH_USERS)
                .appendPath(Integer.toString(userId))
                .build();
        switch (id) {
            case LOADER_ID:
                return new CursorLoader(this,
                        queryUri,
                        null,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader not implemented: " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()) {
            String firstName = data.getString(ProjectionUtils.INDEX_USER_FIRST_NAME);
            String lastName = data.getString(ProjectionUtils.INDEX_USER_LAST_NAME);
            userName.setText(firstName + " " + lastName);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}


