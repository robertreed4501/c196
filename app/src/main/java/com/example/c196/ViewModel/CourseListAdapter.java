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

    /*public List<Term> getList(){
        return getCurrentList();

    }*/

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CourseViewHolder.create(parent, getCurrentList());
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course current = getItem(position);
        holder.bind(current.getCourseName(), current.getCourseStart() + " - " + current.getCourseEnd(), current.getCourseStatus());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Course>{

        @Override
        public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            /*if (oldItem.equals(newItem)) return true; else return false;*/
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            /*if(oldItem.getName().equals(newItem.getName()) && oldItem.getStartDate().equals(newItem.getStartDate()) && oldItem.getEndDate().equals(newItem.getEndDate()))return false;
            else return true;*/
            return false;
        }
    }
}
