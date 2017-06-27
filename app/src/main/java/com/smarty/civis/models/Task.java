package com.smarty.civis.models;

import android.database.Cursor;

import com.smarty.civis.data.tables.TasksTable;

import java.util.Date;

import static com.smarty.civis.data.content.CivisContract.getColumnDouble;
import static com.smarty.civis.data.content.CivisContract.getColumnInt;
import static com.smarty.civis.data.content.CivisContract.getColumnLong;
import static com.smarty.civis.data.content.CivisContract.getColumnString;

/**
 * Created by anh.hoang on 6/25/17.
 */

public class Task {
    public static final int ACTIVE = 0;
    public static final int RESERVED = 1;
    public static final int IN_PROGRESS = 2;
    public static final int PAID = 3;
    public static final int DONE = 4;
    public static final int EXPIRED = 5;

    public static final String ACTIVE_STR = "Active";
    public static final String RESERVED_STR = "Reserved";
    public static final String IN_PROGRESS_STR = "In Progress";
    public static final String PAID_STR = "Paid";
    public static final String DONE_STR = "Done";
    public static final String EXPIRED_STR = "Expired";

    private int id;
    private String title;
    private String description;
    private int jobType;
    private double reward;
    private boolean isRequest;
    private Date startTime;
    private Date endTime;
    private String location; // will be changed to pick currents location or have location picker with map
    private int status;
    private int ownerId;
    private int takenBy; // Person who took the job & finished it


    /**
     * Create a new task from a database Cursor
     */
    public Task(Cursor cursor) {
        this.id = getColumnInt(cursor, TasksTable.Entry._ID);
        this.description = getColumnString(cursor, TasksTable.Entry.COLUMN_DESCRIPTION);
        this.title = getColumnString(cursor, TasksTable.Entry.COLUMN_TITLE);
        this.jobType = getColumnInt(cursor, TasksTable.Entry.COLUMN_JOB_TYPE);
        this.reward = getColumnDouble(cursor, TasksTable.Entry.COLUMN_REWARD);
        this.isRequest = getColumnInt(cursor, TasksTable.Entry.COLUMN_IS_REQUEST) == 1;
        this.startTime = new Date(getColumnLong(cursor, TasksTable.Entry.COLUMN_CREATION_DATE));
        this.endTime = new Date(getColumnLong(cursor, TasksTable.Entry.COLUMN_DUE_DATE));
        this.status = getColumnInt(cursor, TasksTable.Entry.COLUMN_STATUS);
        this.ownerId = getColumnInt(cursor, TasksTable.Entry.COLUMN_OWNER_ID);
        this.takenBy = getColumnInt(cursor, TasksTable.Entry.COLUMN_TAKEN_BY_ID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public boolean isRequest() {
        return isRequest;
    }

    public void setRequest(boolean request) {
        isRequest = request;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusString() {
        switch (getStatus()){
            case ACTIVE:
                return ACTIVE_STR;
            case RESERVED:
                return RESERVED_STR;
            case IN_PROGRESS:
                return IN_PROGRESS_STR;
            case PAID:
                return PAID_STR;
            case DONE:
                return DONE_STR;
            case EXPIRED:
                return EXPIRED_STR;
            default:
                return "Invalid Status";
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(int takenBy) {
        this.takenBy = takenBy;
    }

}
