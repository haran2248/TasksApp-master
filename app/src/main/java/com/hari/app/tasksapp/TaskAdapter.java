package com.hari.app.tasksapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskRvViewHolder>{

    ArrayList<Task> taskList = new ArrayList<>();
    Context context;
    String courseCode;
    Realm realm;

    public TaskAdapter(ArrayList<Task> taskList, Context context, String courseCode) {
        this.taskList = taskList;
        this.context = context;
        this.courseCode = courseCode;
        realm = Realm.getDefaultInstance();
        loadTasks();
    }

    private void loadTasks() {
        RealmQuery<Task> query = realm.where(Task.class);
        RealmResults<Task> results = query.equalTo("course", courseCode).findAll();
        taskList.addAll(results);
        notifyDataSetChanged();
    }

    public void addTask(Task task){
        taskList.add(task);
        notifyDataSetChanged();

        realm.beginTransaction();
        realm.insertOrUpdate(task);
        realm.commitTransaction();
    }



    @NonNull
    @Override
    public TaskRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskRvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tasks, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRvViewHolder holder, int position) {
        holder.populate(taskList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskRvViewHolder extends RecyclerView.ViewHolder{
        private CheckBox check;
        private TextView taskText;
        public TaskRvViewHolder(@NonNull View itemView) {
            super(itemView);
            check = itemView.findViewById(R.id.item_check);
            taskText = itemView.findViewById(R.id.item_text);
        }
        void populate(Task task){
            taskText.setText(task.getTaskText());
            check.setChecked(task.isCheck());
        }
    }
}
