package com.example.c196.ViewModel;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.c196.Model.Assessment;
import com.example.c196.Model.Course;

public class AssessmentListAdapter extends ListAdapter<Assessment, AssessmentViewHolder> {
    public AssessmentListAdapter(@NonNull DiffUtil.ItemCallback<Assessment> diffCallback) {
        super(diffCallback);
    }



    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AssessmentViewHolder.create(parent, getCurrentList());
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        Assessment current = getItem(position);
        holder.bind(current.getAssessmentName(), current.getAssessmentStart() + " - " + current.getAssessmentEnd(), current.getAssessmentType());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Assessment>{

        @Override
        public boolean areItemsTheSame(@NonNull Assessment oldItem, @NonNull Assessment newItem) {
            /*if (oldItem.equals(newItem)) return true; else return false;*/
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Assessment oldItem, @NonNull Assessment newItem) {
            /*if(oldItem.getName().equals(newItem.getName()) && oldItem.getStartDate().equals(newItem.getStartDate()) && oldItem.getEndDate().equals(newItem.getEndDate()))return false;
            else return true;*/
            return false;
        }
    }
}
