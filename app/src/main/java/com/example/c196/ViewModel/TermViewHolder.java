package com.example.c196.ViewModel;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.OnSwipe;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Model.Term;
import com.example.c196.R;
import com.example.c196.UI.AddEditTermActivity;
import com.example.c196.UI.TermViewActivity;

import java.util.List;

public class TermViewHolder extends RecyclerView.ViewHolder {
    private final TextView termNameText;
    private final TextView termDatesText;
    private ViewModel viewModel;
    private static List<Term> myList;

    private TermViewHolder(@NonNull View itemView) {
        super(itemView);
        termNameText = itemView.findViewById(R.id.courseNameText);
        termDatesText = itemView.findViewById(R.id.courseDatesText);
        //viewModel = new ViewModel(TermViewHolder.this);

        itemView.setOnClickListener(view -> {
            int position = getAdapterPosition();
            //Repository repo = new Repository(viewModel.getApplication());
            Term current = myList.get(position);


            Intent i = new Intent(view.getContext().getApplicationContext(), TermViewActivity.class);
            i.putExtra("Term_ID", current.getTermID());
            i.putExtra("Term_Name", current.getName());
            i.putExtra("Term_Start", current.getStartDate().toString());
            i.putExtra("Term_End", current.getEndDate().toString());
            AddEditTermActivity.setCurrentTerm(current);
            view.getContext().startActivity(i);

        });


    }

    public void bind(String name, String dates){
        termNameText.setText(name);
        termDatesText.setText(dates);
    }

    static TermViewHolder create(ViewGroup parent, List<Term> termList){
        myList = termList;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_1, parent, false);
        return new TermViewHolder(view);
    }

    /*public void setList(List<Term> myTerms){
        this.myList = myTerms;
    }*/

    public static Term getTermByPosition(int position){
        return myList.get(position);
    }
}
