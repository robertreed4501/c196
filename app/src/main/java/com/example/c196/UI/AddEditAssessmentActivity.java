package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c196.Model.Assessment;
import com.example.c196.Model.Course;
import com.example.c196.Model.Term;
import com.example.c196.R;
import com.example.c196.ViewModel.ViewModel;

public class AddEditAssessmentActivity extends AppCompatActivity {

    private int associatedCourseID;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_assessment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView assIDText;
        EditText assNameText, assTypeText, assStartText, assEndText;
        Button saveAssButton;

        assIDText = findViewById(R.id.addEditAssIDText);
        assNameText = findViewById(R.id.addEditAssNameText);
        assTypeText = findViewById(R.id.addEditAssTypeText);
        assStartText = findViewById(R.id.addEditAssStartText);
        assEndText = findViewById(R.id.addEditAssEndText);
        saveAssButton = findViewById(R.id.saveAssButton);

        Intent i = getIntent();
        if (i.hasExtra("Assessment_ID")) assIDText.setText(Integer.toString(i.getIntExtra("Assessment_ID", 0)));
        if (i.hasExtra("Assessment_Name")) assNameText.setText(i.getStringExtra("Assessment_Name"));
        if (i.hasExtra("Assessment_Type")) assTypeText.setText(i.getStringExtra("Assessment_Type"));
        if (i.hasExtra("Assessment_Start")) assStartText.setText(i.getStringExtra("Assessment_Start"));
        if (i.hasExtra("Assessment_End")) assEndText.setText(i.getStringExtra("Assessment_End"));
        associatedCourseID = i.getIntExtra("Associated_Course_ID", 0);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        saveAssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i.getBooleanExtra("Is_New_Assessment", true)){
                    viewModel.insertAssessment(new Assessment(
                            assNameText.getText().toString(),
                            assTypeText.getText().toString(),
                            assStartText.getText().toString(),
                            assEndText.getText().toString(),
                            associatedCourseID
                    ));
                }else{
                    viewModel.updateAssessment(new Assessment(
                            Integer.parseInt(assIDText.getText().toString()),
                            assNameText.getText().toString(),
                            assTypeText.getText().toString(),
                            assStartText.getText().toString(),
                            assEndText.getText().toString(),
                            associatedCourseID
                    ));
                }
                Intent intent = new Intent(AddEditAssessmentActivity.this, CourseViewActivity.class);
                Course current = viewModel.getCourseByID(associatedCourseID);
                intent.putExtra("Course_ID", current.getCourseID());
                intent.putExtra("Course_Name", current.getCourseName());
                intent.putExtra("Course_Start", current.getCourseStart());
                intent.putExtra("Course_End", current.getCourseEnd());
                intent.putExtra("Course_Status", current.getCourseStatus());
                intent.putExtra("Instructor_Name", current.getInstructorName());
                intent.putExtra("Instructor_Phone", current.getInstructorPhone());
                intent.putExtra("Instructor_Email", current.getInstructorEmail());
                intent.putExtra("Course_Notes", current.getNotes());
                intent.putExtra("Associated_Term_ID", current.getTermID());
                startActivity(intent);
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
}