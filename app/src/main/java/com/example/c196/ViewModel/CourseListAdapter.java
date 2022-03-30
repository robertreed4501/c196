package com.example.c196.ViewModel;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.c196.Model.Course;
import com.example.c196.Model.Term;

import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends ListAdapter<Course, CourseViewHolder> {
    public CourseListAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CourseViewHolder.create(parent, getCurrentList());
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course current = getItem(position);
        holder.bind(current.getCourseName() + " " + current.getCourseID(), current.getCourseStart() + " - " + current.getCourseEnd(), current.getCourseStatus());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Course>{

        @Override
        public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return false;
        }
    }
}
