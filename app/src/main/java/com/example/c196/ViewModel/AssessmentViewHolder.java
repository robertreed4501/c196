package com.example.c196.ViewModel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Model.Assessment;
import com.example.c196.R;
import com.example.c196.UI.AssessmentViewActivity;

import java.util.List;

public class AssessmentViewHolder extends RecyclerView.ViewHolder {
    private final TextView assessmentNameText;
    private final TextView assessmentDatesText;
    private final TextView assessmentTypeText;
    private static List<Assessment> myList;

    private AssessmentViewHolder(@NonNull View itemView) {
        super(itemView);
        //define TextViews on the cards
        assessmentNameText = itemView.findViewById(R.id.assessmentNameText);
        assessmentDatesText = itemView.findViewById(R.id.assessmentDatesText);
        assessmentTypeText = itemView.findViewById(R.id.assessmentTypeText);

        //listener for when card is clicked
        itemView.setOnClickListener(view -> {
            //get the assessment that was clicked on
            int position = getAdapterPosition();
            Assessment current = myList.get(position);

            //set up intent to pass data to assessment view
            Intent i = new Intent(view.getContext().getApplicationContext(), AssessmentViewActivity.class);
            i.putExtra("Assessment_ID", current.getAssessmentID());
            i.putExtra("Assessment_Name", current.getAssessmentName());
            i.putExtra("Assessment_Start", current.getAssessmentStart());
            i.putExtra("Assessment_End", current.getAssessmentEnd());
            i.putExtra("Assessment_Type", current.getAssessmentType());
            i.putExtra("Associated_Course_ID", current.getAssociatedCourseID());
            view.getContext().startActivity(i);
        });
    }

    public static Assessment getAssessmentByPosition(int adapterPosition) {
        return myList.get(adapterPosition);
    }

    public void bind(String name, String dates, String type){
        //set data to card fields
        assessmentNameText.setText(name);
        assessmentDatesText.setText(dates);
        assessmentTypeText.setText(type);
    }

    static AssessmentViewHolder create(ViewGroup parent, List<Assessment> assessmentList){
        myList = assessmentList;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new AssessmentViewHolder(view);
    }
}
