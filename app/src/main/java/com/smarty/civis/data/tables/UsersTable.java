package com.smarty.civis.data.tables;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.smarty.civis.data.content.CivisContract;

import static com.smarty.civis.data.tables.TasksTable.CODE_CERTAIN_TASK;


/**
 * Created by mohammed on 6/26/17.
 */

public class UsersTable implements TableInterface
{
    public static final String PATH_USERS = "users";

    public static final class Entry implements BaseColumns
    {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
    }

    private static UriMatcher sUriMatcher = buildUriMatcher();

    // use special cases for every table
    public static final int CODE_ALL_USERS = 200;
    public static final int CODE_CERTAIN_USER = 201;


    private static UriMatcher buildUriMatcher()
    {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CivisContract.AUTHORITY, PATH_USERS, CODE_ALL_USERS);
        uriMatcher.addURI(CivisContract.AUTHORITY, PATH_USERS+"/#", CODE_CERTAIN_USER);
        return uriMatcher;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String CREATE_USERS_TABLE = "CREATE TABLE "+ Entry.TABLE_NAME+" ("+
                Entry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Entry.COLUMN_FIRST_NAME+" TEXT NOT NULL, "+
                Entry.COLUMN_LAST_NAME+" TEXT NOT NULL, "+
                Entry.COLUMN_EMAIL+" TEXT NOT NULL, "+
                Entry.COLUMN_PHONE+" TEXT NOT NULL"+
                ");";
        db.execSQL(CREATE_USERS_TABLE);
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
            case CODE_CERTAIN_USER:
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
            case CODE_ALL_USERS:
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
            case CODE_CERTAIN_USER:
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
