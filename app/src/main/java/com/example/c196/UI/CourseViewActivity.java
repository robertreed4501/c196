package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c196.Model.Assessment;
import com.example.c196.R;
import com.example.c196.ViewModel.AssessmentListAdapter;
import com.example.c196.ViewModel.ViewModel;

public class CourseViewActivity extends AppCompatActivity {

    private ViewModel viewModel;
    private int associatedTermID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button editCourseButton = findViewById(R.id.editCourseButton);
        TextView courseIDText = findViewById(R.id.courseViewIDText);
        TextView nameText = findViewById(R.id.courseViewNameText);
        TextView startText = findViewById(R.id.courseViewStartText);
        TextView endText = findViewById(R.id.courseViewEndText);
        TextView statusText = findViewById(R.id.courseViewStatusText);
        TextView instructorNameText = findViewById(R.id.courseViewInstructorNameText);
        TextView instructorPhoneText = findViewById(R.id.courseViewPhoneText);
        TextView instructorEmailText = findViewById(R.id.courseViewEmailText);
        EditText courseNotesText = findViewById(R.id.courseViewNotesText);

        Intent i = getIntent();
        if (i.hasExtra("Course_ID")) courseIDText.setText("Course ID: " + Integer.toString(i.getIntExtra("Course_ID", 0)));
        if (i.hasExtra("Course_Name")) nameText.setText("Course Name: " + i.getStringExtra("Course_Name"));
        if (i.hasExtra("Course_Start")) startText.setText("Course Start: " + i.getStringExtra("Course_Start"));
        if (i.hasExtra("Course_End")) endText.setText("Course End: " + i.getStringExtra("Course_End"));
        if (i.hasExtra("Course_Status")) statusText.setText("Course Status: " + i.getStringExtra("Course_Status"));
        if (i.hasExtra("Instructor_Name")) instructorNameText.setText("Instructor Name: " + i.getStringExtra("Instructor_Name"));
        if (i.hasExtra("Instructor_Phone")) instructorPhoneText.setText("Instructor Phone: " + i.getStringExtra("Instructor_Phone"));
        if (i.hasExtra("Instructor_Email")) instructorEmailText.setText("Instructor Email: " + i.getStringExtra("Instructor_Email"));
        if (i.hasExtra("Course_Notes")) courseNotesText.setText(i.getStringExtra("Course_Notes"));
        if (i.hasExtra("Associated_Term_ID")) associatedTermID = i.getIntExtra("Associated_Term_ID", 0);

        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentListAdapter adapter = new AssessmentListAdapter(new AssessmentListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.insertAssessment(new Assessment("CompTIA A+", "Objective", "8/31/2017", "8/32/2017",  1));
        //adapter.setList();
        viewModel.getAssessmentsByCourseID(i.getIntExtra("Course_ID", 0)).observe(this, courses -> {
            adapter.submitList(courses);
        });

        editCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseViewActivity.this, AddEditCourseActivity.class);
                intent.putExtra("Course_ID", i.getIntExtra("Course_ID", 0));
                intent.putExtra("Course_Name", i.getStringExtra("Course_Name"));
                intent.putExtra("Course_Start", i.getStringExtra("Course_Start"));
                intent.putExtra("Course_End", i.getStringExtra("Course_End"));
                intent.putExtra("Course_Status", i.getStringExtra("Course_Status"));
                intent.putExtra("Instructor_Name", i.getStringExtra("Instructor_Name"));
                intent.putExtra("Instructor_Phone", i.getStringExtra("Instructor_Phone"));
                intent.putExtra("Instructor_Email", i.getStringExtra("Instructor_Email"));
                intent.putExtra("Course_Notes", i.getStringExtra("Course_Notes"));
                intent.putExtra("Associated_Term_ID", associatedTermID);
                intent.putExtra("Is_New_Course", false);
                view.getContext().startActivity(intent);

            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addAssessmentFABClicked(View view){
        Intent intent = new Intent(CourseViewActivity.this, AddEditAssessmentActivity.class);
        startActivity(intent);
    }
}