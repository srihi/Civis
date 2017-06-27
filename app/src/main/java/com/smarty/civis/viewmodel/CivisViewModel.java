package com.smarty.civis.viewmodel;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.data.tables.UsersTable;
import com.smarty.civis.models.User;
import com.smarty.civis.utils.ProjectionUtils;

import java.util.Date;

/**
 * Created by mohammed on 6/27/17.
 */

public class CivisViewModel
{
    private static CivisViewModel instance;
    private Context context;

    private CivisViewModel(Context context)
    {
        this.context = context;
    }

    public static CivisViewModel getInstance(Context context)
    {
        if(instance == null)
            instance = new CivisViewModel(context);
        return instance;
    }

    public int saveUser(User user) throws Exception
    {
        Uri uri = CivisContract.BASE_CONTENT_URI.buildUpon()
                .appendPath(UsersTable.PATH_USERS)
                .build();
        ContentValues values = new ContentValues();
        values.put(UsersTable.Entry.COLUMN_FIRST_NAME, user.getFirstName());
        values.put(UsersTable.Entry.COLUMN_LAST_NAME, user.getLastName());
        values.put(UsersTable.Entry.COLUMN_EMAIL, user.getEmail());
        values.put(UsersTable.Entry.COLUMN_PHONE, user.getPhone());

        Uri insertedUri = this.context.getContentResolver().insert(uri, values);
        return (int) ContentUris.parseId(insertedUri);
    }

    public User getUser(int id) throws Exception
    {
        Uri uri = CivisContract.BASE_CONTENT_URI.buildUpon()
                .appendPath(UsersTable.PATH_USERS)
                .appendPath(Integer.toString(id))
                .build();
        Cursor c = this.context.getContentResolver().query(uri, ProjectionUtils.USER_PROJECTION,null,null,null);
        if(c == null)
            throw new NullPointerException();

        if(c.getCount() != 1)
            throw new Exception("User not found!");

        c.moveToFirst();
        User user = new User();
        user.setId(c.getInt(ProjectionUtils.INDEX_USER_ID));
        user.setEmail(c.getString(ProjectionUtils.INDEX_USER_EMAIL));
        user.setFirstName(c.getString(ProjectionUtils.INDEX_USER_FIRST_NAME));
        user.setLastName(c.getString(ProjectionUtils.INDEX_USER_LAST_NAME));
        user.setPhone(c.getString(ProjectionUtils.INDEX_USER_PHONE));

        c.close();
        return user;
    }


}
