package com.hari.app.tasksapp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {

    public Task(){

    }

    private boolean isCheck;
    private String taskText;
    private String course;

    public Task(boolean isCheck, String taskText, String course) {
        this.isCheck = isCheck;
        this.taskText = taskText;
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }
}
