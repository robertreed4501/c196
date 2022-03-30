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
        return TermViewHolder.create(parent, getCurrentList());
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        Term current = getItem(position);
        holder.bind(current.getName(), current.getStartDate() + " - " + current.getEndDate());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Term>{

        @Override
        public boolean areItemsTheSame(@NonNull Term oldItem, @NonNull Term newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Term oldItem, @NonNull Term newItem) {
            return false;
        }
    }
}
