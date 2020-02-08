package com.hari.app.tasksapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseVH> {

    ArrayList<Course> courseList;
    Context context;

    Realm realm;

    public CourseAdapter(ArrayList<Course> coursList, Context context) {
        this.courseList = coursList;
        this.context = context;

        realm=Realm.getDefaultInstance();
        loadCourses();
    }


    void loadCourses(){
        RealmQuery<Course> realmQuery = realm.where(Course.class);
        RealmResults<Course> realmResults = realmQuery.findAll();

        courseList.addAll(realmResults);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseVH holder, int position) {
        holder.populateCourse(courseList.get(position));
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    class CourseVH extends RecyclerView.ViewHolder{

        Button courseBtn;

        public CourseVH(@NonNull View itemView) {
            super(itemView);
            courseBtn=itemView.findViewById(R.id.courseBtn);
        }

        void populateCourse(final Course course){
            courseBtn.setText(course.getCourseName()+" "+course.getCourseCode());
            courseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent loadTasks = new Intent(context, TasksActivity.class);
                    loadTasks.putExtra("courseCode", course.getCourseCode());
                    itemView.getContext().startActivity(loadTasks);
                }
            });

        }
    }
}
