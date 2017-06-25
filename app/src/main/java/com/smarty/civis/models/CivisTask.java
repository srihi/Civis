package com.smarty.civis.models;

import java.util.Date;

/**
 * Created by anh.hoang on 6/25/17.
 */

public class CivisTask {
    private int id;
    private String title;
    private String description;
    private int jobType;
    private double reward;
    private boolean isRequest;
    private Date startTime;
    private Date endTime;
    private String location; // will be changed to pick currents location or have location picker with map
    private boolean isDone;
    private User owner;
    private User takenBy; // Person who took the job & finished it

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public User getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(User takenBy) {
        this.takenBy = takenBy;
    }
}
