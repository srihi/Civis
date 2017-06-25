package com.smarty.civis.models;

import java.util.List;

/**
 * Created by anh.hoang on 6/25/17.
 */

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<CivisTask> ownTasks; // Requests and offers
    private List<CivisTask> tasksDone; // Requests from others that have been done by this user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CivisTask> getOwnTasks() {
        return ownTasks;
    }

    public void setOwnTasks(List<CivisTask> ownTasks) {
        this.ownTasks = ownTasks;
    }

    public List<CivisTask> getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(List<CivisTask> tasksDone) {
        this.tasksDone = tasksDone;
    }
}
