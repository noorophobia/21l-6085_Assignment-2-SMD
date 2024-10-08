package com.example.a2;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    public static ArrayList<Task> completedTasks;

    @Override
    public void onCreate() {
        super.onCreate();
        completedTasks = new ArrayList<>();
    }

    public static void addCompletedTask(Task task) {
        completedTasks.add(task);
    }

    public ArrayList<Task> getCompletedTasks() {
        return completedTasks;
    }
}
