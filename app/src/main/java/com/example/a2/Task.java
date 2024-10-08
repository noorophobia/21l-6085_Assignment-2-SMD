package com.example.a2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Task  {
    private String title;
    private String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }




    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
