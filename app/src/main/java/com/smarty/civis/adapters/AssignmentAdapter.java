package com.smarty.civis.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarty.civis.R;
import com.smarty.civis.models.Task;
import com.smarty.civis.utils.PrefUtils;

import java.util.List;

/**
 * Created by itaseski.
 */

/**
 * Created by itaseski.
 */

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.OfferViewHolder> {

    private final Context mContext;

    private Cursor mCursor;
    private List<Task> mTestList;

    private AssignmentAdapterOnClickHandler clickHandler;

    public AssignmentAdapter(Context context) {
        this.mContext = context;
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
        Context context0 = parent.getContext();
        View item = LayoutInflater.from(context0).inflate(R.layout.list_item_assignment, parent, false);

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
        holder.status.setText(task.getStatusString());
        holder.dueDate.setText(DateUtils.getRelativeTimeSpanString(task.getEndTime()));

        // Add appropriate buttons
        switch (task.getStatus()){
            // If the task is reserved and the user is the owner give the option to accept or refuse
            case Task.RESERVED:
                if(PrefUtils.getUserId(mContext) == task.getOwnerId()) {
                    Button btnAccept = new Button(mContext);
                    Button btnRefuse = new Button(mContext);
                    btnAccept.setText(R.string.accept);
                    btnRefuse.setText(R.string.refuse);
                    holder.buttonContainer.addView(btnAccept);
                    holder.buttonContainer.addView(btnRefuse);
                }
                break;
            // If the task is in progress and the user is the handler give the option to set as done
            case Task.IN_PROGRESS:
                if(PrefUtils.getUserId(mContext) == task.getTakenBy()) {
                    Button btnDone = new Button(mContext);
                    btnDone.setText(R.string.done);
                    holder.buttonContainer.addView(btnDone);
                }
                break;
            // If the task is done and the user is the owner give the option to pay
            case Task.DONE:
                Button btnPaid = new Button(mContext);
                btnPaid.setText(R.string.pay);
                holder.buttonContainer.addView(btnPaid);
                break;
            case Task.ACTIVE:
            case Task.PAID:
            case Task.EXPIRED:
            default:
        }

        if (mCursor == null) {
            return;
        }
        mCursor.moveToPosition(position);

        Task task = new Task(mCursor);

        holder.title.setText(task.getTitle());
        holder.price.setText("$" + String.valueOf(task.getReward()));
        holder.location.setText(task.getLocation());

    }

    @Override
    public int getItemCount() {
        return (mCursor != null) ? mCursor.getCount() : 0;
    }

    public interface AssignmentAdapterOnClickHandler {
        void onClick(int position, Task task);
    }

    class OfferViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView price;
        TextView location;
        TextView status;
        TextView dueDate;
        LinearLayout buttonContainer;

        OfferViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickHandler != null) {
                        mCursor.moveToPosition(getAdapterPosition());
                        clickHandler.onClick(getAdapterPosition(), new Task(mCursor));
                    }
                }
            });

            location = (TextView) itemView.findViewById(R.id.tv_location);
            status = (TextView) itemView.findViewById(R.id.tv_status);
            price = (TextView) itemView.findViewById(R.id.tv_price);
            dueDate = (TextView) itemView.findViewById(R.id.tv_due_date);
            buttonContainer = (LinearLayout) itemView.findViewById(R.id.button_container);
        }
    }
}
