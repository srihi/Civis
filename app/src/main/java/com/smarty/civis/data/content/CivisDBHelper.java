package com.smarty.civis.data.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smarty.civis.data.tables.TableInterface;
import com.smarty.civis.utils.DatabaseUtils;

/**
 * Created by mohammed on 6/26/17.
 */

public class CivisDBHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "civis_db";
    private static final int DB_VERSION = 1;

    private TableInterface tables[];

    public CivisDBHelper(Context context, TableInterface tables[])
    {
        super(context,DB_NAME,null,DB_VERSION);
        this.tables = tables;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        for(TableInterface table:tables) {
            table.onCreate(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        for(TableInterface table:tables)
        {
            table.onUpgrade(db,oldVersion,newVersion);
        }
        onCreate(db);
    }

    public TableInterface[] getTables()
    {
        return this.tables;
    }
}