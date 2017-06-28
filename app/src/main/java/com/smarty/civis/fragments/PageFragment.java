package com.smarty.civis.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarty.civis.R;
import com.smarty.civis.activities.AddActivity;
import com.smarty.civis.adapters.AssignmentAdapter;
import com.smarty.civis.data.content.CivisContract;
import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.utils.DatabaseUtils;

public class PageFragment extends Fragment implements AssignmentAdapter.AssignmentAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 904328;

    private static final String ARG_PAGE = "page";

    private int page;

    private FloatingActionButton fButton;

    private AssignmentAdapter assignmentAdapter;

    public PageFragment() {

    }

    public static PageFragment newInstance(int page) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt(ARG_PAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        fButton = (FloatingActionButton) view.findViewById(R.id.fab);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddActivity.class);
                getContext().startActivity(intent);
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_assignment);

        assignmentAdapter = new AssignmentAdapter(getContext());

        assignmentAdapter.setClickHandler(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(assignmentAdapter);

        LoaderManager loaderManager = getActivity().getSupportLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, this);

        return view;
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_ID:
                return new CursorLoader(getContext(),
                        CivisContract.BASE_CONTENT_URI.buildUpon().appendPath(TasksTable.PATH_TASKS).build(),
                        null,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader not implemented: " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        assignmentAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        assignmentAdapter.setCursor(null);
    }

}
