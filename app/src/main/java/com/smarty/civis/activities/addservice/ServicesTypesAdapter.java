package com.smarty.civis.activities.addservice;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smarty.civis.R;

/**
 * Created by mohammed on 6/23/17.
 */

public class ServicesTypesAdapter extends RecyclerView.Adapter<ServicesTypesAdapter.ServicesTypesViewHolder>
{
    private Cursor dataCursor;

    public class ServicesTypesViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name_textview;

        public ServicesTypesViewHolder(View parentView)
        {
            super(parentView);
            name_textview = (TextView) parentView.findViewById(R.id.tv_service_type_name);
        }

        public void bind(Cursor data, int position)
        {
            data.moveToPosition(position);
            name_textview.setText(data.getString(0));
        }
    }

    /**
     * Constructor
     */
    public ServicesTypesAdapter(Cursor dataCursor)
    {
        this.dataCursor = dataCursor;
    }

    @Override
    public ServicesTypesViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutId = R.layout.item_rv_add_form_services;

        View view = inflater.inflate(layoutId, parent, false);

        return new ServicesTypesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServicesTypesViewHolder holder, int position)
    {
        holder.bind(dataCursor, position);
    }

    @Override
    public int getItemCount()
    {
        if(dataCursor == null)
            return 0;
        return dataCursor.getCount();
    }

    public void setDataCursor(Cursor dataCursor)
    {
        this.dataCursor = dataCursor;
        notifyDataSetChanged();
    }
}
