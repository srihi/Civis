package com.smarty.civis.data;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.models.Task;

/* Process DB actions on a background thread */
public class TaskUpdateService extends IntentService {
    private static final String TAG = TaskUpdateService.class.getSimpleName();
    //Intent actions
    public static final String ACTION_INSERT_TASK = TAG + ".INSERT_TASK";
    public static final String ACTION_UPDATE_TASK = TAG + ".UPDATE_TASK";
    public static final String ACTION_DELETE_TASK = TAG + ".DELETE_TASK";

    public static final String ACTION_INSERT_USER = TAG + ".INSERT_USER";
    public static final String ACTION_UPDATE_USER = TAG + ".UPDATE_USER";
    public static final String ACTION_DELETE_USER = TAG + ".DELETE_USER";

    public static final String EXTRA_VALUES = TAG + ".ContentValues";

    public static void insertNewTask(Context context, ContentValues values) {
        Intent intent = new Intent(context, TaskUpdateService.class);
        intent.setAction(ACTION_INSERT_TASK);
        intent.putExtra(EXTRA_VALUES, values);
        context.startService(intent);
    }

    public static void updateTask(Context context, Uri uri, ContentValues values) {
        Intent intent = new Intent(context, TaskUpdateService.class);
        intent.setAction(ACTION_UPDATE_TASK);
        intent.setData(uri);
        intent.putExtra(EXTRA_VALUES, values);
        context.startService(intent);
    }

    void updateTaskStatus(Context context, Task task, int newStatus){
        Uri uri = ContentUris.withAppendedId(CivisContract.TASKS_CONTENT_URI, task.getId());
        ContentValues contentValues = new ContentValues();
        contentValues.put(TasksTable.Entry.COLUMN_STATUS, newStatus);
        updateTask(context, uri, contentValues);
    }

    public static void deleteTask(Context context, Uri uri) {
        Intent intent = new Intent(context, TaskUpdateService.class);
        intent.setAction(ACTION_DELETE_TASK);
        intent.setData(uri);
        context.startService(intent);
    }

    public static void insertNewUser(Context context, ContentValues values) {
        Intent intent = new Intent(context, TaskUpdateService.class);
        intent.setAction(ACTION_INSERT_USER);
        intent.putExtra(EXTRA_VALUES, values);
        context.startService(intent);
    }

    public static void updateUser(Context context, Uri uri, ContentValues values) {
        Intent intent = new Intent(context, TaskUpdateService.class);
        intent.setAction(ACTION_UPDATE_USER);
        intent.setData(uri);
        intent.putExtra(EXTRA_VALUES, values);
        context.startService(intent);
    }

    public static void deleteUser(Context context, Uri uri) {
        Intent intent = new Intent(context, TaskUpdateService.class);
        intent.setAction(ACTION_DELETE_USER);
        intent.setData(uri);
        context.startService(intent);
    }

    public TaskUpdateService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (ACTION_INSERT_TASK.equals(action)) {
            ContentValues values = intent.getParcelableExtra(EXTRA_VALUES);
            performInsertTask(values);
        } else if (ACTION_UPDATE_TASK.equals(action) || ACTION_UPDATE_USER.equals(action)) {
            ContentValues values = intent.getParcelableExtra(EXTRA_VALUES);
            performUpdate(intent.getData(), values);
        } else if (ACTION_DELETE_TASK.equals(action) || ACTION_DELETE_USER.equals(action)) {
            performDelete(intent.getData());
        }
        else if (ACTION_INSERT_USER.equals(action)) {
            ContentValues values = intent.getParcelableExtra(EXTRA_VALUES);
            performInsertUser(values);
        }
    }

    private void performInsertTask(ContentValues values) {
        if (getContentResolver().insert(CivisContract.TASKS_CONTENT_URI, values) != null) {
            Log.d(TAG, "Inserted new task");
        } else {
            Log.w(TAG, "Error inserting new task");
        }
    }

    private void performInsertUser(ContentValues values) {
        if (getContentResolver().insert(CivisContract.USERS_CONTENT_URI, values) != null) {
            Log.d(TAG, "Inserted new user");
        } else {
            Log.w(TAG, "Error inserting new user");
        }
    }

    private void performUpdate(Uri uri, ContentValues values) {
        int count = getContentResolver().update(uri, values, null, null);
        Log.d(TAG, "Updated " + count + " task items");
    }

    private void performDelete(Uri uri) {
        int count = getContentResolver().delete(uri, null, null);
        Log.d(TAG, "Deleted "+count+" tasks");
    }
}
