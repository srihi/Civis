package com.smarty.civis.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.widget.ScrollView;
import android.widget.TextView;

import com.smarty.civis.R;
import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.data.tables.UsersTable;
import com.smarty.civis.models.Task;
import com.smarty.civis.utils.ProjectionUtils;

import java.text.NumberFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String ARG_TASK = "mTask";

    private static final int LOADER_ID = 901905;

    @BindView(R.id.category)
    TextView category;
    @BindView(R.id.day)
    TextView day;
    @BindView(R.id.month)
    TextView month;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.details_title)
    TextView title;
    @BindView(R.id.details_description)
    TextView description;
    @BindView(R.id.details_user_name)
    TextView userName;
    @BindView(R.id.errorMessage)
    TextView errorMessage;
    @BindView(R.id.details_scrollview)
    ScrollView scrollView;
    @BindView(R.id.details_location)
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

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
        int id = task.getOwnerId();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        getSupportLoaderManager().initLoader(LOADER_ID, bundle, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        int ownerId = args.getInt("id");
        Uri queryUri = CivisContract.BASE_CONTENT_URI.buildUpon()
                .appendPath(UsersTable.PATH_USERS)
                .appendPath(Integer.toString(ownerId))
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


