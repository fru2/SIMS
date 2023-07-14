package com.patnacollege.sims.dataAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patnacollege.sims.HelperFunctions;
import com.patnacollege.sims.R;
import com.patnacollege.sims.dataModel.AssignmentDataModel;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {
    private List<AssignmentDataModel> dataList;

    public AssignmentAdapter(List<AssignmentDataModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AssignmentDataModel data = dataList.get(position);
        holder.titleTextView.setText(data.getTitle());
        holder.descriptionTextView.setText(data.getDescription());
        holder.dueDateTextView.setText(data.getDueDate());
        holder.subjectTextView.setText(data.getSubject());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView dueDateTextView;
        TextView subjectTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.description);
            dueDateTextView = itemView.findViewById(R.id.due_date);
            subjectTextView= itemView.findViewById(R.id.assignment_subject);

        }
    }
}