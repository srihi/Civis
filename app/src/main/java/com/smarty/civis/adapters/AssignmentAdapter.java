package com.smarty.civis.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smarty.civis.R;
import com.smarty.civis.models.Task;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.OfferViewHolder> {

    private final Context context;

    private Cursor mCursor;

    private AssignmentAdapterOnClickHandler clickHandler;

    public AssignmentAdapter(Context context) {
        this.context = context;
    }

    public void setClickHandler(AssignmentAdapterOnClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    public void setCursor(Cursor cursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.list_item_assignment, parent, false);

        return new OfferViewHolder(item);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        if (mCursor == null) {
            return;
        }
        mCursor.moveToPosition(position);

        Task task = new Task(mCursor);

        holder.title.setText(task.getTitle());
        holder.price.setText(String.valueOf(task.getReward()));
        holder.location.setText(task.getLocation());

    }

    @Override
    public int getItemCount() {
        return (mCursor != null) ? mCursor.getCount() : 0;
    }

    public interface AssignmentAdapterOnClickHandler {
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

        void bindViews(Task task) {

        }
    }

//    class DoneTaksViewHolder extends OfferViewHolder{
//        DoneTaksViewHolder(View itemView) {
//            super(itemView);
//
//        }
//
//        @Override
//        void bindViews(Object task) {
//
//        }
//    }
}
