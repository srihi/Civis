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

public class OffersTasksFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private final String LOG_TAG = OffersTasksFragment.class.getSimpleName();
    private static final int LOADER_REQUESTS_ID = 1;

    AssignmentAdapter mRequestsAdapter;

    public OffersTasksFragment() {
        // Required empty public constructor
    }

    public static OffersTasksFragment newInstance() {
        return new OffersTasksFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requested_tasks, container, false);
        getActivity().setTitle(getString(R.string.offered_jobs));

        RecyclerView rv_requests = (RecyclerView) view.findViewById(R.id.rv_requests);
        mRequestsAdapter = new AssignmentAdapter(getContext());
        rv_requests.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_requests.setAdapter(mRequestsAdapter);

        getActivity().getSupportLoaderManager().initLoader(LOADER_REQUESTS_ID, null, this);
        return view;
    }

    // ------------- Loader methods ----------------//

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        Log.i(LOG_TAG, "Loading data from DB");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int uid = sharedPref.getInt(getString(R.string.pref_uid_key), -1);

        String requestsSelection = TasksTable.Entry.COLUMN_OWNER_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(uid)};

        return new CursorLoader(getActivity(),
                CivisContract.TASKS_CONTENT_URI,
                null,
//                null, null, null);
                requestsSelection,
                selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mRequestsAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mRequestsAdapter.setCursor(null);
    }
}
