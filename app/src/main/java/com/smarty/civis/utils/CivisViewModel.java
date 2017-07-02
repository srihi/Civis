package com.smarty.civis.utils;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.smarty.civis.R;
import com.smarty.civis.data.TaskUpdateService;
import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.data.tables.UsersTable;
import com.smarty.civis.models.Task;
import com.smarty.civis.models.User;

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

    /**
     * Save User to Database
     * @param user
     * @return User ID
     * @throws Exception
     */
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

    /**
     * Get User From Database
     * @param id
     * @return
     * @throws Exception
     */
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


    public static void populateDb(Context context, int uid) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Long id = sharedPreferences.getLong(context.getString(R.string.init_user_id_key), 1);
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
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, uid);
        TaskUpdateService.insertNewTask(context, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Pick up my son from school");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who can pick up my son from school");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Aldgate 22");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 40.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Delivery");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, uid);
        cv.put(TasksTable.Entry.COLUMN_STATUS, Task.RESERVED);
        TaskUpdateService.insertNewTask(context, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Replace my Windows 10 operating system with Linux Fedora");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "if you can help me with the installation of Linux Fedora that would be great");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Fetter Lane 98");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 30.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Computer service");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        cv.put(TasksTable.Entry.COLUMN_TAKEN_BY_ID, uid);
        cv.put(TasksTable.Entry.COLUMN_STATUS, Task.IN_PROGRESS);
        TaskUpdateService.insertNewTask(context, cv);

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
        TaskUpdateService.insertNewTask(context, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me to get home safely");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I am an elderly person who wants to get home safely");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Holborn Viaduct 112");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 50.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Social");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, uid);
        cv.put(TasksTable.Entry.COLUMN_STATUS, Task.DONE);
        TaskUpdateService.insertNewTask(context, cv);

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
        TaskUpdateService.insertNewTask(context, cv);

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
        TaskUpdateService.insertNewTask(context, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Buy me groceries");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I am a disabled person and I need help with buying groceries");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Leadenhall Street 43");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 20.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Delivery");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        TaskUpdateService.insertNewTask(context, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me choose and buy a new smartphone");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who is tech savvy and can help me choose the best possible smartphone for me");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Cripplegate 39");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 45.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Advices and Query");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        TaskUpdateService.insertNewTask(context, cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me organize my CV");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who can advice me on how to write my CV in the best possible way");
        cv.put(TasksTable.Entry.COLUMN_LOCATION, "London, Walbrook 18");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, System.currentTimeMillis());
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, System.currentTimeMillis() + 1e8);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 25.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Advices and Query");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, id);
        TaskUpdateService.insertNewTask(context, cv);
    }
}
