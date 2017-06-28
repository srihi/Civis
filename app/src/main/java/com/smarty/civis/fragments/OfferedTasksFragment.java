package com.smarty.civis.fragments;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarty.civis.R;
import com.smarty.civis.adapters.AssignmentAdapter;
import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.utils.ProjectionUtils;

public class OfferedTasksFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private final String LOG_TAG = OfferedTasksFragment.class.getSimpleName();
    private static final int LOADER_OFFERS_ID = 0;

    AssignmentAdapter mOffersAdapter;

    public OfferedTasksFragment() {
        // Required empty public constructor
    }

    public static OfferedTasksFragment newInstance() {
        return new OfferedTasksFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offered_tasks, container, false);
        getActivity().setTitle(getString(R.string.offered_jobs));

        RecyclerView rv_offers = (RecyclerView) view.findViewById(R.id.rv_offers);
        mOffersAdapter = new AssignmentAdapter(getContext());
        rv_offers.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_offers.setAdapter(mOffersAdapter);

        getActivity().getSupportLoaderManager().initLoader(LOADER_OFFERS_ID, null, this);

        return view;
    }

    // ------------- Loader methods ----------------//

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        Log.i(LOG_TAG, "Loading data from DB");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int uid = sharedPref.getInt(getString(R.string.pref_uid_key), -1);

        String offersSelection = TasksTable.Entry.COLUMN_TAKEN_BY_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(uid)};

        return new CursorLoader(getActivity(),
                CivisContract.TASKS_CONTENT_URI,
                ProjectionUtils.TASK_PROJECTION,
                offersSelection,
                selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mOffersAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mOffersAdapter.setCursor(null);
    }
}
