package com.hari.app.tasksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView courseRV;
    Button newCourseBtn;

    ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courseRV = findViewById(R.id.coursesRV);
        newCourseBtn = findViewById(R.id.addCourse);

        newCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openNewCourse = new Intent(MainActivity.this,
                        EditCourse.class);
                openNewCourse.setAction("newCourse");
                startActivity(openNewCourse);
            }
        });

        courseRV.setLayoutManager(new LinearLayoutManager(this));
        courses=new ArrayList<>();
//        courses.add(new Course("Mathematics 1","MATH F111"));
//        courses.add(new Course("Gen Bio","BIO F111"));
//        courses.add(new Course("Gen Chem","CHEM F111"));

        CourseAdapter adapter=new CourseAdapter(courses,this);
        courseRV.setAdapter(adapter);

    }
}
