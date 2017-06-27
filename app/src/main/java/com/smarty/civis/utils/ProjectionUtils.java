package com.smarty.civis.utils;

import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.data.tables.UsersTable;

/**
 * Created by mohammed on 6/27/17.
 */

public class ProjectionUtils
{
    /*
        Should be The same order as index below
     */
    public static final String[] TASK_PROJECTION = {
            TasksTable.Entry._ID,
            TasksTable.Entry.COLUMN_TITLE,
            TasksTable.Entry.COLUMN_DESCRIPTION,
            TasksTable.Entry.COLUMN_JOB_TYPE,
            TasksTable.Entry.COLUMN_REWARD,
            TasksTable.Entry.COLUMN_CREATION_DATE,
            TasksTable.Entry.COLUMN_DUE_DATE,
            TasksTable.Entry.COLUMN_IS_REQUEST,
            TasksTable.Entry.COLUMN_OWNER_ID,
            TasksTable.Entry.COLUMN_TAKEN_BY_ID,
            TasksTable.Entry.COLUMN_STATUS
    };
    public static final int INDEX_TASK_ID = 0;
    public static final int INDEX_TASK_TITLE = 1;
    public static final int INDEX_TASK_DESC = 2;
    public static final int INDEX_TASK_JOB_TYPE = 3;
    public static final int INDEX_TASK_REWARD = 4;
    public static final int INDEX_TASK_DUE_DATE = 5;
    public static final int INDEX_TASK_IS_REQUEST = 6;
    public static final int INDEX_TASK_OWNER_ID = 7;
    public static final int INDEX_TASK_TAKEN_BY_ID = 8;
    public static final int INDEX_TASK_STATUS = 9;

    /*
        Should be The same order as index below
     */
    public static final String[] USER_PROJECTION = {
            UsersTable.Entry._ID,
            UsersTable.Entry.COLUMN_FIRST_NAME,
            UsersTable.Entry.COLUMN_LAST_NAME,
            UsersTable.Entry.COLUMN_EMAIL,
            UsersTable.Entry.COLUMN_PHONE
    };
    public static final int INDEX_USER_ID = 0;
    public static final int INDEX_USER_FIRST_NAME = 1;
    public static final int INDEX_USER_LAST_NAME = 2;
    public static final int INDEX_USER_EMAIL = 3;
    public static final int INDEX_USER_PHONE = 4;
}
