package com.smarty.civis.activities.main;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smarty.civis.R;

/**
 * Created by itaseski on 6/23/17.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {


    private final Context context;

    private Cursor cursor;

    private OfferAdapterOnClickHandler clickHandler;

    OfferAdapter(Context context) {
        this.context = context;
    }

    void setClickHandler(OfferAdapterOnClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.offer_list_item, parent, false);

        return new OfferViewHolder(item);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        //cursor.moveToPosition(position);

        holder.title.setText("Hello World !");

        holder.price.setText("1000$");

        holder.location.setText("London, Cambridge 7994");

        holder.description.setText("Hello we are looking for a hello worlder who would like to become part of a team.");
    }

    @Override
    public int getItemCount() {
        //if (cursor != null) {
          //  return cursor.getCount();
        //}
        return 10;
    }

    interface OfferAdapterOnClickHandler {
        void onClick();
    }

    class OfferViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        TextView location;

        TextView description;

        TextView price;

        OfferViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandler.onClick();
                }
            });
            location = (TextView) itemView.findViewById(R.id.tv_location);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }

}
