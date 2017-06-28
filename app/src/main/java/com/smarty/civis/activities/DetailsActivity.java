package com.smarty.civis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.smarty.civis.R;
import com.smarty.civis.models.Task;
import com.smarty.civis.models.User;

import java.text.NumberFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {


    @BindView(R.id.category)
    TextView category;

    @BindView(R.id.day)
    TextView day;

    @BindView(R.id.month)
    TextView month;

    @BindView(R.id.price)
    TextView price;

    @BindView(R.id.details_title)
    TextView title;

    @BindView(R.id.details_description)
    TextView disc;

    @BindView(R.id.details_user_name)
    TextView userName;

    @BindView(R.id.errorMessage)
    TextView errorMessage;

    @BindView(R.id.details_scrollview)
    ScrollView scrollView;

    @BindView(R.id.details_location)
    TextView location;


    public static final String ARG_TASK = "mTask";
    public static final String ARG_USER = "mUser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent.hasExtra(ARG_TASK) && intent.hasExtra(ARG_USER)){
            loadData((Task)intent.getParcelableExtra(ARG_TASK),(User)intent.getParcelableExtra(ARG_USER));
        }else
            showError();
    }

    private void loadData(Task task, User user){
        price.setText(NumberFormat.getCurrencyInstance().format(task.getReward()));
        title.setText(task.getTitle());
        disc.setText(task.getDescription());
        Date date = task.getEndTime();
        String _day = (String) DateFormat.format("dd",   date);
        String _monthString  = (String) DateFormat.format("MMM",  date);
        day.setText(_day);
        month.setText(_monthString);
        category.setText(task.getJobType());
        location.setText(task.getLocation());
        userName.setText(user.getFirstName() + " " + user.getLastName());
    }


    private void showError(){
        errorMessage.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }
}


