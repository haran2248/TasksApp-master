package com.hari.app.tasksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {
    RecyclerView tasksRV;
    TaskAdapter adapter;
    ArrayList<Task>taskList;
    Button addButton;
    EditText addTasket;
    String courseCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        attachID();

        courseCode=getIntent().getStringExtra("courseCode");

        tasksRV.setLayoutManager(new LinearLayoutManager(this));
        adapter=new TaskAdapter(taskList,this,courseCode);
        tasksRV.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addTask(new Task(false))
            }
        });
    }
}
