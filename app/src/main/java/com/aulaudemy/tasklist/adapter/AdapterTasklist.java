package com.aulaudemy.tasklist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aulaudemy.tasklist.R;
import com.aulaudemy.tasklist.model.Task;

import java.util.List;

public class AdapterTasklist extends RecyclerView.Adapter<AdapterTasklist.MyViewHolder> {

    private List<Task> taskList;

    public AdapterTasklist(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tasklistView = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.adapter_tasklist, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(tasklistView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.checkBoxTask.setText(task.getDescription());
        holder.checkBoxTask.setChecked(task.getStatus());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBoxTask;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxTask = itemView.findViewById(R.id.checkBoxTask);
        }
    }
}
