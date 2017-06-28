package com.smarty.civis.models;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.smarty.civis.utils.ProjectionUtils;

import java.util.Date;

/**
 * Created by anh.hoang on 6/25/17.
 */

public class Task implements Parcelable {
    public static final int ACTIVE = 0;
    public static final int RESERVED = 1;
    public static final int IN_PROGRESS = 2;
    public static final int PAID = 3;
    public static final int DONE = 4;
    public static final int EXPIRED = 5;

    private int id;
    private String title;
    private String description;
    private String jobType;
    private double reward;
    private boolean isRequest;
    private Date startTime;
    private Date endTime;
    private String location; // will be changed to pick currents location or have location picker with map
    private int status;
    private int ownerId;
    private int takenBy; // Person who took the job & finished it

    public Task(){}

    public Task(Cursor cursor)
    {
        id = cursor.getInt(ProjectionUtils.INDEX_TASK_ID);
        title = cursor.getString(ProjectionUtils.INDEX_TASK_TITLE);
        description = cursor.getString(ProjectionUtils.INDEX_TASK_DESC);
        jobType = cursor.getString(ProjectionUtils.INDEX_TASK_JOB_TYPE);
        reward = cursor.getFloat(ProjectionUtils.INDEX_TASK_REWARD);
        isRequest = cursor.getInt(ProjectionUtils.INDEX_TASK_IS_REQUEST) != 0;
        location = cursor.getString(ProjectionUtils.INDEX_TASK_LOCATION);
        status = cursor.getInt(ProjectionUtils.INDEX_TASK_STATUS);
        ownerId = cursor.getInt(ProjectionUtils.INDEX_TASK_OWNER_ID);
        takenBy = cursor.getInt(ProjectionUtils.INDEX_TASK_TAKEN_BY_ID);
    }
  
    protected Task(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        jobType = in.readString();
        reward = in.readDouble();
        isRequest = in.readByte() != 0;
        location = in.readString();
        status = in.readInt();
        ownerId = in.readInt();
        takenBy = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
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

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(int takenBy) {
        this.takenBy = takenBy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(jobType);
        dest.writeDouble(reward);
        dest.writeByte((byte) (isRequest ? 1 : 0));
        dest.writeString(location);
        dest.writeInt(status);
        dest.writeInt(ownerId);
        dest.writeInt(takenBy);
    }
}

