package com.smarty.civis.data;

import com.smarty.civis.data.content.CivisDBHelper;
import com.smarty.civis.data.content.ImprovedContentProvider;
import com.smarty.civis.data.tables.TableInterface;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.data.tables.UsersTable;

/**
 * Created by mohammed on 6/26/17.
 */

public class CivisProvider extends ImprovedContentProvider
{
    @Override
    public boolean onCreate()
    {
        TableInterface tables[] = new TableInterface[2];
        tables[0] = new TasksTable();
        tables[1] = new UsersTable();
        mDbHelper = new CivisDBHelper(getContext(),tables);
        return true;
    }
}
