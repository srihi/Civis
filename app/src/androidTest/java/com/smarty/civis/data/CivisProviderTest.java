package com.smarty.civis.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentProvider;
import android.test.mock.MockContentResolver;

import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.UsersTable;

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
        Cursor c = mMockResolver.query(uri,null,null,null,null);
        assertEquals(1, c.getCount());
        c.close();
    }



}
