package com.patnacollege.sims.dataAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patnacollege.sims.HelperFunctions;
import com.patnacollege.sims.R;
import com.patnacollege.sims.dataModel.ScheduleDataModel;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private List<ScheduleDataModel> dataList;

    public ScheduleAdapter(List<ScheduleDataModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleDataModel data = dataList.get(position);
        holder.scheduleSubjectTextView.setText(data.getScheduleSubject());
        holder.timeTextView.setText(data.getTime());
        holder.progressTextView.setText(data.getProgress());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView scheduleSubjectTextView;
        TextView timeTextView;
        TextView progressTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            scheduleSubjectTextView = itemView.findViewById(R.id.schedule_subject);
            timeTextView = itemView.findViewById(R.id.time);
            progressTextView = itemView.findViewById(R.id.subject_progress);
        }
    }
}