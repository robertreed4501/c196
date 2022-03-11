package com.example.c196.ViewModel;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.c196.Model.Term;

public class TermListAdapter extends ListAdapter<Term, TermViewHolder> {
    public TermListAdapter(@NonNull DiffUtil.ItemCallback<Term> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TermViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        Term current = getItem(position);
        holder.bind(current.getName(), current.getStartDate().toString() + " - " + current.getEndDate().toString());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Term>{

        @Override
        public boolean areItemsTheSame(@NonNull Term oldItem, @NonNull Term newItem) {
            if (oldItem.equals(newItem)) return true; else return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Term oldItem, @NonNull Term newItem) {
            if(oldItem.getName().equals(newItem.getName()) && oldItem.getStartDate().equals(newItem.getStartDate()) && oldItem.getEndDate().equals(newItem.getEndDate()))return false;
            else return true;
        }
    }
}
