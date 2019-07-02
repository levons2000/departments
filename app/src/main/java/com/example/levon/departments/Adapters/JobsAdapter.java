package com.example.levon.departments.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.levon.departments.Models.Job;
import com.example.levon.departments.Models.Task;
import com.example.levon.departments.R;

import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {

    private Context context;
    private List<Job> list;
    private List<Task> taskList;

    public JobsAdapter(Context context, List<Job> list, List<Task> taskList) {
        this.context = context;
        this.list = list;
        this.taskList = taskList;
    }

    @Override
    public JobsAdapter.JobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.job_item_style, parent, false);
        return new JobsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobsAdapter.JobsViewHolder holder, int position) {
        Job currentJob = list.get(position);
        holder.jobNameText.setText(currentJob.getJobTitle());
        for (Task task : taskList) {
            holder.jobTasksText.append(task.getTitle() + "\n\n");
            holder.jobTasksText.append(task.getDescription() + "\n\n");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JobsViewHolder extends RecyclerView.ViewHolder {
        TextView jobNameText;
        TextView jobTasksText;

        public JobsViewHolder(View itemView) {
            super(itemView);
            jobNameText = itemView.findViewById(R.id.job_name);
            jobTasksText = itemView.findViewById(R.id.tasks);
        }
    }
}
