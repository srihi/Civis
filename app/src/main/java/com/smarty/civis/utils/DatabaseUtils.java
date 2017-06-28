package com.smarty.civis.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.data.tables.UsersTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itaseski on 6/28/17.
 */

public class DatabaseUtils {

    public static void insertFakeData(Context context){
        /*if(db == null){
            return;
        }*/

        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Pick up my prescription from chemist");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who can go to my chemist and pick up my prescription");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, 1497415674);
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, 1498515674);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 40.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Delivery");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Replace my Windows 10 operating system with Linux Fedora");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "if you can help me with the installation of Linux Fedora that would be great");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, 1497415674);
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, 1498515674);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 30.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Computer service & repair");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Translate 10 pages from Japanese to English");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need help with translation from Japanese to English and you don't have to be professional translator");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, 1497415674);
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, 1498515674);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 100.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Translation");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me to get home safely");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I am an elderly person who wants to get home safely");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, 1497415674);
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, 1498515674);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 50.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Social");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Replace my hard drive");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who can replace my old hard drive with a new one");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, 1497415674);
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, 1498515674);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 35.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Computer service & repair");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me move my stuff to my new apartment");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who is able to lift heavy objects and can help me to move to my new apartment");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, 1497415674);
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, 1498515674);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 75.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Social");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Buy me groceries");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I am a disabled person and I need help with buying groceries");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, 1497415674);
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, 1498515674);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 20.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Delivery");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me choose and buy a new smartphone");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who is tech savvy and can help me choose the best possible smartphone for me");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, 1497415674);
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, 1498515674);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 45.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Advices & Query");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(TasksTable.Entry.COLUMN_TITLE, "Help me organize my CV");
        cv.put(TasksTable.Entry.COLUMN_DESCRIPTION, "I need someone who can advice me on how to write my CV in the best possible way");
        cv.put(TasksTable.Entry.COLUMN_CREATION_DATE, 1497415674);
        cv.put(TasksTable.Entry.COLUMN_DUE_DATE, 1498515674);
        cv.put(TasksTable.Entry.COLUMN_REWARD, 25.00);
        cv.put(TasksTable.Entry.COLUMN_JOB_TYPE, "Advices & Query");
        cv.put(TasksTable.Entry.COLUMN_OWNER_ID, 1);
        list.add(cv);

        for (ContentValues contentValue : list) {
            context.getContentResolver().insert(CivisContract.BASE_CONTENT_URI.buildUpon().appendPath(TasksTable.PATH_TASKS).build(),
                    contentValue);
        }


        /*try {
            db.beginTransaction();

            for(ContentValues c:list){
                db.insert(UsersTable.Entry.TABLE_NAME, null, c);
            }

            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }*/

    }
}
