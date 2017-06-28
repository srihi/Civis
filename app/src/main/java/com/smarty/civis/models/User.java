package com.smarty.civis.models;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.smarty.civis.data.tables.TasksTable;
import com.smarty.civis.data.tables.UsersTable;

import java.util.List;

/**
 * Created by anh.hoang on 6/25/17.
 */

public class User implements Parcelable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Task> ownTasks; // Requests and offers
    private List<Task> tasksDone; // Requests from others that have been done by this user;


    public User() {

    }

    User(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        phone = in.readString();
        ownTasks = in.createTypedArrayList(Task.CREATOR);
        tasksDone = in.createTypedArrayList(Task.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeTypedList(ownTasks);
        dest.writeTypedList(tasksDone);
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues(4);
        values.put(UsersTable.Entry.COLUMN_FIRST_NAME, getFirstName());
        values.put(UsersTable.Entry.COLUMN_LAST_NAME, getLastName());
        values.put(UsersTable.Entry.COLUMN_EMAIL, getEmail());
        values.put(UsersTable.Entry.COLUMN_PHONE, getPhone());
        return values;
    }
}
