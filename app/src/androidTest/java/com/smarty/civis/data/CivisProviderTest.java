package com.smarty.civis.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.data.tables.UsersTable;
import com.smarty.civis.utils.ProjectionUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by mohammed on 6/26/17.
 */
@RunWith(AndroidJUnit4.class)
public class CivisProviderTest extends ProviderTestCase2<CivisProvider>
{
    MockContentResolver mMockResolver;
    public CivisProviderTest()
    {
        super(CivisProvider.class, CivisContract.AUTHORITY);
    }

    @Override
    @Before
    public void setUp() throws Exception
    {
        setContext(InstrumentationRegistry.getTargetContext());
        super.setUp();
        mMockResolver = getMockContentResolver();
    }

    @Test
    public void insertNewUserTest()
    {
        Uri uri = insertNewUser();
        assertEquals(1L, ContentUris.parseId(uri));
    }

    private Uri insertNewUser()
    {
        Uri uri = CivisContract.BASE_CONTENT_URI.buildUpon().appendPath(UsersTable.PATH_USERS).build();
        Uri insertedUri = mMockResolver.insert(uri, getUserContentValues());
        return insertedUri;
    }

    private ContentValues getUserContentValues()
    {
        ContentValues contentValues = new ContentValues(4);
        contentValues.put(UsersTable.Entry.COLUMN_FIRST_NAME, "Mohammed");
        contentValues.put(UsersTable.Entry.COLUMN_LAST_NAME, "Abuiriban");
        contentValues.put(UsersTable.Entry.COLUMN_EMAIL, "m.g.iriban@gmail.com");
        contentValues.put(UsersTable.Entry.COLUMN_PHONE, "+4912345678");
        return contentValues;
    }

    @Test
    public void selectUserTest()
    {
        // insert user first
        insertNewUser();
        // get that user
        String user_id = "1";
        Uri uri = CivisContract.BASE_CONTENT_URI.buildUpon().appendPath(UsersTable.PATH_USERS).appendPath(user_id).build();
        Cursor c = mMockResolver.query(uri, ProjectionUtils.USER_PROJECTION,null,null,null);
        c.moveToFirst();
        assertEquals("Mohammed", c.getString(ProjectionUtils.INDEX_USER_FIRST_NAME));
        assertEquals("Abuiriban", c.getString(ProjectionUtils.INDEX_USER_LAST_NAME));
        assertEquals("m.g.iriban@gmail.com", c.getString(ProjectionUtils.INDEX_USER_EMAIL));
        assertEquals("+4912345678", c.getString(ProjectionUtils.INDEX_USER_PHONE));
        c.close();
    }

    @Test
    public void insertNewTaskTest()
    {
        Uri uri = insertNewTask();
        assertEquals(1L, ContentUris.parseId(uri));
    }

    private Uri insertNewTask()
    {
        Uri uri = CivisContract.BASE_CONTENT_URI.buildUpon().appendPath(TasksTable.PATH_TASKS).build();
        Uri insertedUri = mMockResolver.insert(uri, getTaskContentValues());
        return insertedUri;
    }

    private ContentValues getTaskContentValues()
    {
        ContentValues contentValues = new ContentValues(6);
        contentValues.put(TasksTable.Entry.COLUMN_TITLE, "Test Task Title");
        contentValues.put(TasksTable.Entry.COLUMN_DESCRIPTION, "Test Task Description");
        contentValues.put(TasksTable.Entry.COLUMN_CREATION_DATE, 1497415674); // timestamp
        contentValues.put(TasksTable.Entry.COLUMN_DUE_DATE, 1498515674); // timestamp
        contentValues.put(TasksTable.Entry.COLUMN_REWARD, 13.50); // float
        contentValues.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Babysitter"); // string [ for MVP only ]
        contentValues.put(TasksTable.Entry.COLUMN_OWNER_ID, 1); // user id
        return contentValues;
    }

    @Test
    public void selectTaskTest()
    {
        // insert task first
        insertNewTask();
        // get that user
        String task_id = "1";
        Uri uri = CivisContract.BASE_CONTENT_URI.buildUpon().appendPath(TasksTable.PATH_TASKS).appendPath(task_id).build();
        Cursor c = mMockResolver.query(uri, ProjectionUtils.TASK_PROJECTION,null,null,null);
        c.moveToFirst();
        assertEquals("Test Task Title", c.getString(ProjectionUtils.INDEX_TASK_TITLE));
        assertEquals("Test Task Description", c.getString(ProjectionUtils.INDEX_TASK_DESC));
        assertEquals(1498515674, c.getInt(ProjectionUtils.INDEX_TASK_DUE_DATE));
        assertEquals(13.50f, c.getFloat(ProjectionUtils.INDEX_TASK_REWARD));
        assertEquals("Babysitter", c.getString(ProjectionUtils.INDEX_TASK_JOB_TYPE));
        assertEquals(1, c.getInt(ProjectionUtils.INDEX_TASK_OWNER_ID));
        c.close();
    }



}
