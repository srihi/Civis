package com.smarty.civis.activities.main;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smarty.civis.R;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.OfferViewHolder> {


    private final Context context;

    private Cursor cursor;

    private AssignmentAdapterOnClickHandler clickHandler;

    AssignmentAdapter(Context context) {
        this.context = context;
    }

    void setClickHandler(AssignmentAdapterOnClickHandler  clickHandler) {
        this.clickHandler = clickHandler;
    }

    void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.list_item_assignment, parent, false);

        return new OfferViewHolder(item);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        //cursor.moveToPosition(position);

        holder.title.setText("Skilled mobile developer needed for video streaming app");

        holder.price.setText("$790");

        holder.location.setText("London, Cambridge 7994 - 18 mins ago");
    }

    @Override
    public int getItemCount() {
        //if (cursor != null) {
          //  return cursor.getCount();
        //}
        return 10;
    }

    interface AssignmentAdapterOnClickHandler  {
        void onClick(int position);
    }

    class OfferViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        TextView price;

        TextView location;

        OfferViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandler.onClick(getAdapterPosition());
                }
            });

            location = (TextView) itemView.findViewById(R.id.tv_location);

            price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }

}
