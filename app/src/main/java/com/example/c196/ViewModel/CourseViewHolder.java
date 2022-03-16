package com.example.c196.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Model.Course;
import com.example.c196.R;

import java.util.List;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    private final TextView courseNameText;
    private final TextView courseDatesText;
    private final TextView courseStatusText;
    private ViewModel viewModel;
    private static List<Course> myList;

    private CourseViewHolder(@NonNull View itemView) {
        super(itemView);
        courseNameText = itemView.findViewById(R.id.courseNameText);
        courseDatesText = itemView.findViewById(R.id.courseDatesText);
        courseStatusText = itemView.findViewById(R.id.courseStatusText);
        //viewModel = new ViewModel(TermViewHolder.this);

        /*itemView.setOnClickListener(view -> {
            int position = getAdapterPosition();
            //Repository repo = new Repository(viewModel.getApplication());
            Course current = myList.get(position);


            Intent i = new Intent(view.getContext().getApplicationContext(), TermViewActivity.class);
            i.putExtra("Term_ID", current.getTermID());
            i.putExtra("Term_Name", current.getName());
            i.putExtra("Term_Start", current.getStartDate().toString());
            i.putExtra("Term_End", current.getEndDate().toString());
            view.getContext().startActivity(i);

        });*/
    }

    public static Course getCourseByPosition(int adapterPosition) {
        return myList.get(adapterPosition);
    }

    public void bind(String name, String dates, String status){
        courseNameText.setText(name);
        courseDatesText.setText(dates);
        courseStatusText.setText(status);
    }

    static CourseViewHolder create(ViewGroup parent, List<Course> courseList){
        myList = courseList;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_2, parent, false);
        return new CourseViewHolder(view);
    }

    /*public void setList(List<Term> myTerms){
        this.myList = myTerms;
    }*/

}
