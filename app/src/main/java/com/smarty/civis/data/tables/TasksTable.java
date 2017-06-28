package com.smarty.civis.data.tables;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.utils.DatabaseUtils;

import static android.R.attr.id;

/**
 * Created by mohammed on 6/26/17.
 */

public class TasksTable implements TableInterface
{
    public static final String PATH_TASKS = "tasks";

    public static final class Entry implements BaseColumns
    {
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

    private static UriMatcher sUriMatcher = buildUriMatcher();

    // use special cases for every table
    public static final int CODE_ALL_TASKS = 100;
    public static final int CODE_CERTAIN_TASK = 101;


    private static UriMatcher buildUriMatcher()
    {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CivisContract.AUTHORITY,PATH_TASKS,CODE_ALL_TASKS);
        uriMatcher.addURI(CivisContract.AUTHORITY,PATH_TASKS+"/#",CODE_CERTAIN_TASK);
        return uriMatcher;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TASKS_TABLE = "CREATE TABLE "+Entry.TABLE_NAME+" ("+
                Entry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Entry.COLUMN_TITLE+" TEXT NOT NULL, "+
                Entry.COLUMN_DESCRIPTION+" TEXT NOT NULL, "+
                Entry.COLUMN_JOB_TYPE+" STRING NOT NULL, "+
                Entry.COLUMN_REWARD+" FLOAT NOT NULL, "+
                Entry.COLUMN_IS_REQUEST+" INTEGER NOT NULL DEFAULT 1, "+
                Entry.COLUMN_CREATION_DATE+" LONG NOT NULL, "+
                Entry.COLUMN_DUE_DATE+" LONG NOT NULL, "+
                Entry.COLUMN_LOCATION+" TEXT NOT NULL, "+
                Entry.COLUMN_STATUS+" INTEGER NOT NULL DEFAULT 0, "+
                Entry.COLUMN_OWNER_ID+" INTEGER NOT NULL, "+
                Entry.COLUMN_TAKEN_BY_ID+" INTEGER NOT NULL DEFAULT -1"+
                ");";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Entry.TABLE_NAME);
    }

    @Override
    public Cursor query(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        Cursor returnedCursor = null;
        switch (sUriMatcher.match(uri))
        {
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
    public Uri insert(SQLiteDatabase db, Uri uri, ContentValues values)
    {
        Uri returnedUri = null;
        long id;
        switch (sUriMatcher.match(uri))
        {
            case CODE_ALL_TASKS:
                id = db.insert(Entry.TABLE_NAME,null,values);
                returnedUri = ContentUris.withAppendedId(uri,id);
                break;
        }
        return returnedUri;

    }

    @Override
    public int update(SQLiteDatabase db, Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        int numberOfRows = -1;
        switch (sUriMatcher.match(uri))
        {
            case CODE_CERTAIN_TASK:
                String id = uri.getPathSegments().get(1);
                numberOfRows = db.update(
                        Entry.TABLE_NAME, values, "_id=?", new String[]{id});
                break;
        }
        return numberOfRows;
    }

    @Override
    public int delete(SQLiteDatabase db, Uri uri, String selection, String[] selectionArgs)
    {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public String getType(Uri uri)
    {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
