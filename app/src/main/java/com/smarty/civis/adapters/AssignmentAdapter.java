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

import java.util.ArrayList;
import java.util.List;

import static com.smarty.civis.models.Task.ACTIVE;
import static com.smarty.civis.models.Task.PAID;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.OfferViewHolder> {

    private final Context mContext;

    private Cursor mCursor;
    private List<Task> mTestList;

    private AssignmentAdapterOnClickHandler clickHandler;

    public AssignmentAdapter(Context context) {
        this.mContext = context;
        this.mTestList = initTasksList();
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
//        Log.i("onCreateViewHolder", "===========called===========");

        return new OfferViewHolder(item);
    }

    private List<Task> initTasksList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Help", "Babysit the kids", "Home", 15.3, true, System.currentTimeMillis() + 100000, "London", ACTIVE, 1, -1));
        tasks.add(new Task("Help", "Babysit the kids", "Home", 15.3, true, System.currentTimeMillis() + 100000, "London", Task.DONE, 1, -1));
        tasks.add(new Task("Help", "Babysit the kids", "Home", 15.3, true, System.currentTimeMillis() + 100000, "London", Task.EXPIRED, 1, -1));
        tasks.add(new Task("Help", "Babysit the kids", "Home", 15.3, true, System.currentTimeMillis() + 100000, "London", Task.IN_PROGRESS, 1, -1));
        tasks.add(new Task("Help", "Babysit the kids", "Home", 15.3, true, System.currentTimeMillis() + 100000, "London", PAID, 1, -1));
        tasks.add(new Task("Help", "Babysit the kids", "Home", 15.3, true, System.currentTimeMillis() + 100000, "London", Task.RESERVED, 1, -1));
        return tasks;
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
//        if (mCursor == null) {
//            return;
//        }
//        mCursor.moveToPosition(position);
//        Task task = new Task(mCursor);
//        Log.i("onBindViewHolder", "===========called===========");

        Task task = mTestList.get(position);

        holder.title.setText(task.getTitle());
        holder.price.setText(String.valueOf(task.getReward()));
        holder.location.setText(task.getStatusString());
        holder.dueDate.setText(DateUtils.getRelativeTimeSpanString(task.getEndTime()));

        // Add appropriate buttons
        switch (task.getStatus()){
            // If the task is reserved and the user is the owner give the option to accept or refuse
            case Task.RESERVED:
                if(PrefUtils.getUserId(mContext) == task.getOwnerId()) {
                    Button btnAccept = new Button(mContext);
                    Button btnRefuse = new Button(mContext);
                    btnAccept.setText("Accept");
                    btnRefuse.setText("Refuse");
                    holder.buttonContainer.addView(btnAccept);
                    holder.buttonContainer.addView(btnRefuse);
                }
                break;
            // If the task is in progress and the user is the handler give the option to set as done
            case Task.IN_PROGRESS:
                if(PrefUtils.getUserId(mContext) == task.getTakenBy()) {
                    Button btnDone = new Button(mContext);
                    btnDone.setText("Done");
                    holder.buttonContainer.addView(btnDone);
                }
                break;
            // If the task is done and the user is the owner give the option to pay
            case Task.DONE:
                Button btnPaid = new Button(mContext);
                btnPaid.setText("Pay");
                holder.buttonContainer.addView(btnPaid);
                break;
            case Task.ACTIVE:
            case Task.PAID:
            case Task.EXPIRED:
            default:
        }
    }

    @Override
    public int getItemCount() {
//        return (mCursor != null) ? mCursor.getCount() : 0;
        return mTestList.size();
    }

    public interface AssignmentAdapterOnClickHandler {
        void onClick(int position);
    }

    class OfferViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView price;
        TextView location;
        TextView dueDate;
        LinearLayout buttonContainer;

        void bindViews(Task task) {

        }

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
            dueDate = (TextView) itemView.findViewById(R.id.tv_due_date);
            buttonContainer = (LinearLayout) itemView.findViewById(R.id.button_container);
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
