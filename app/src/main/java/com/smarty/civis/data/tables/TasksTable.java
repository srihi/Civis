package com.smarty.civis.data.tables;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;

import com.smarty.civis.R;
import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.models.Task;

/**
 * Created by mohammed on 6/26/17.
 */

public class TasksTable implements TableInterface {

    public static final String PATH_TASKS = "tasks";

    // use special cases for every table
    public static final int CODE_ALL_TASKS = 100;
    public static final int CODE_CERTAIN_TASK = 101;

    private static UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CivisContract.AUTHORITY, PATH_TASKS, CODE_ALL_TASKS);
        uriMatcher.addURI(CivisContract.AUTHORITY, PATH_TASKS + "/#", CODE_CERTAIN_TASK);
        return uriMatcher;
    }

    @Override
    public void onCreate(SQLiteDatabase db, Context context) {
        final String CREATE_TASKS_TABLE = "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Entry.COLUMN_TITLE + " TEXT NOT NULL, " +
                Entry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                Entry.COLUMN_JOB_TYPE + " STRING NOT NULL, " +
                Entry.COLUMN_REWARD + " FLOAT NOT NULL, " +
                Entry.COLUMN_IS_REQUEST + " INTEGER NOT NULL DEFAULT 1, " +
                Entry.COLUMN_CREATION_DATE + " LONG NOT NULL, " +
                Entry.COLUMN_DUE_DATE + " LONG NOT NULL, " +
                Entry.COLUMN_LOCATION + " TEXT NOT NULL, " +
                Entry.COLUMN_STATUS + " INTEGER NOT NULL DEFAULT 0, " +
                Entry.COLUMN_OWNER_ID + " INTEGER NOT NULL, " +
                Entry.COLUMN_TAKEN_BY_ID + " INTEGER NOT NULL DEFAULT -1" +
                ");";
        db.execSQL(CREATE_TASKS_TABLE);

//        populate(db, context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Entry.TABLE_NAME);
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor returnedCursor = null;
        switch (sUriMatcher.match(uri)) {
            case CODE_ALL_TASKS:
                returnedCursor = db.query(Entry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case CODE_CERTAIN_TASK:
                selection = Entry._ID + "=?";
                selectionArgs = new String[]{uri.getPathSegments().get(1)};
                returnedCursor = db.query(Entry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }
        return returnedCursor;
    }

    @Override
    public Uri insert(SQLiteDatabase db, Uri uri, ContentValues values) {
        Uri returnedUri = null;
        long id;
        switch (sUriMatcher.match(uri)) {
            case CODE_ALL_TASKS:
                id = db.insert(Entry.TABLE_NAME, null, values);
                returnedUri = ContentUris.withAppendedId(uri, id);
                break;
        }
        return returnedUri;

    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int numberOfRows = -1;
        switch (sUriMatcher.match(uri)) {
            case CODE_CERTAIN_TASK:
                String id = uri.getPathSegments().get(1);
                numberOfRows = db.update(
                        Entry.TABLE_NAME, values, "_id=?", new String[]{id});
                break;
        }
        return numberOfRows;
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    private void populate(SQLiteDatabase db, Context context) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Long id = sharedPreferences.getLong(context.getString(R.string.init_user_id_key), -1);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(context.getString(R.string.init_user_id_key));
        editor.apply();

        ContentValues cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Pick up my prescription from chemist");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who can go to my chemist and pick up my prescription");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Aldgate 22");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 40.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Delivery");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 385);
        db.insert(Entry.TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Pick up my son from school");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who can pick up my son from school");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Aldgate 22");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 40.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Delivery");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 385);
        cv.put(TasksTable.Entry.COLUMN_STATUS, Task.RESERVED);
        db.insert(Entry.TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Replace my Windows 10 operating system with Linux Fedora");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "if you can help me with the installation of Linux Fedora that would be great");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Fetter Lane 98");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 30.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Computer service");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        cv.put(TasksTable.Entry.COLUMN_TAKEN_BY_ID, 385);
        cv.put(TasksTable.Entry.COLUMN_STATUS, Task.IN_PROGRESS);
        db.insert(Entry.TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "I Need help with translation from Japanese to English");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need help with translation from Japanese to English and you don't have to be professional translator");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Philpot Lane 23");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 100.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Translation");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        cv.put(TasksTable.Entry.COLUMN_STATUS, Task.EXPIRED);
        db.insert(Entry.TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me to get home safely");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I am an elderly person who wants to get home safely");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Holborn Viaduct 112");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 50.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Social");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 385);
        cv.put(TasksTable.Entry.COLUMN_STATUS, Task.DONE);
        db.insert(Entry.TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Replace my hard drive");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who can replace my old hard drive with a new one");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Paternoster Row 9");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 35.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Computer service");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        cv.put(TasksTable.Entry.COLUMN_STATUS, Task.PAID);
        db.insert(Entry.TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me move my stuff to my new apartment");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who is able to lift heavy objects and can help me to move to my new apartment");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Houndsditch 7");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 75.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Social");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        cv.put(TasksTable.Entry.COLUMN_STATUS, Task.RESERVED);
        db.insert(Entry.TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Buy me groceries");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I am a disabled person and I need help with buying groceries");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Leadenhall Street 43");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 20.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Delivery");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        db.insert(Entry.TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me choose and buy a new smartphone");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who is tech savvy and can help me choose the best possible smartphone for me");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Cripplegate 39");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 45.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Advices and Query");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        db.insert(Entry.TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me organize my CV");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who can advice me on how to write my CV in the best possible way");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Walbrook 18");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 25.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Advices and Query");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        db.insert(Entry.TABLE_NAME, null, cv);

    }

    public static final class Entry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "desc";
        public static final String COLUMN_JOB_TYPE = "job_type";
        public static final String COLUMN_REWARD = "reward";
        public static final String COLUMN_IS_REQUEST = "is_request";
        public static final String COLUMN_CREATION_DATE = "creation_date";
        public static final String COLUMN_DUE_DATE = "due_date";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_OWNER_ID = "owner_id";
        public static final String COLUMN_TAKEN_BY_ID = "taken_by_id";
    }
}
