package com.smarty.civis.data.content;

import android.database.Cursor;
import android.net.Uri;

/**
 * Created by mohammed on 6/26/17.
 */

public class CivisContract
{
    public static final String AUTHORITY = "com.smarty.civis";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);

    /* Helpers to retrieve column values */
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble( cursor.getColumnIndex(columnName) );
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }
}
