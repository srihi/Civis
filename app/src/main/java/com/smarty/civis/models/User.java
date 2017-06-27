package com.smarty.civis.models;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.UUID;

/**
 * Created by anh.hoang on 6/25/17.
 */

public class User {
    private int id;
    private UUID uuid;
    @Json(name = "first_name")
    private String firstName;
    @Json(name = "last_name")
    private String lastName;
    private String email;
    private String phone;
    private List<Task> ownTasks; // Requests and offers
    private List<Task> tasksDone; // Requests from others that have been done by this user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public List<Task> getOwnTasks() {
        return ownTasks;
    }

    public void setOwnTasks(List<Task> ownTasks) {
        this.ownTasks = ownTasks;
    }

    public List<Task> getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(List<Task> tasksDone) {
        this.tasksDone = tasksDone;
    }
}
