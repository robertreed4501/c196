package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c196.Model.Course;
import com.example.c196.Model.Term;
import com.example.c196.R;
import com.example.c196.ViewModel.ViewModel;

import java.util.Calendar;

public class AddEditCourseActivity extends AppCompatActivity {

    ViewModel viewModel;
    private DatePickerDialog.OnDateSetListener courseStartListener, courseEndListener;
    private int termID;
    private boolean isNewCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_course);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button saveCourseButton = findViewById(R.id.saveCourseButton);
        TextView idText = findViewById(R.id.addEditCourseIDText);
        EditText nameText = findViewById(R.id.addEditCourseNameText);
        EditText startText = findViewById(R.id.addEditCourseStartText);
        EditText endText = findViewById(R.id.addEditCourseEndText);
        EditText statusText = findViewById(R.id.addEditCourseStatusText);
        EditText instructorNameText = findViewById(R.id.addEditCourseInstructorNameText);
        EditText instructorPhoneText = findViewById(R.id.addEditCoursePhoneText);
        EditText instructorEmailText = findViewById(R.id.addEditCourseEmailText);
        EditText notesText = findViewById(R.id.addEditCourseNotes);

        Intent i = getIntent();
        if (i.hasExtra("Term_ID")) termID = i.getIntExtra("Term_ID", 2);
        if (i.hasExtra("Course_ID")) idText.setText(Integer.toString(i.getIntExtra("Course_ID", 0)));
        if (i.hasExtra("Course_Name")) nameText.setText(i.getStringExtra("Course_Name"));
        if (i.hasExtra("Course_Start")) startText.setText(i.getStringExtra("Course_Start"));
        if (i.hasExtra("Course_End")) endText.setText(i.getStringExtra("Course_End"));
        if (i.hasExtra("Course_Status")) statusText.setText(i.getStringExtra("Course_Status"));
        if (i.hasExtra("Instructor_Name")) instructorNameText.setText(i.getStringExtra("Instructor_Name"));
        if (i.hasExtra("Instructor_Phone")) instructorPhoneText.setText(i.getStringExtra("Instructor_Phone"));
        if (i.hasExtra("Instructor_Email")) instructorEmailText.setText(i.getStringExtra("Instructor_Email"));
        if (i.hasExtra("Course_Notes")) notesText.setText(i.getStringExtra("Course_Notes"));
        if (i.hasExtra("Associated_Term_ID")) termID = i.getIntExtra("Associated_Term_ID", 0);
        isNewCourse = i.getBooleanExtra("Is_New_Course", false);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNewCourse){
                    viewModel.insertCourse(new Course(nameText.getText().toString(),
                            startText.getText().toString(),
                            endText.getText().toString(),
                            statusText.getText().toString(),
                            instructorNameText.getText().toString(),
                            instructorPhoneText.getText().toString(),
                            instructorEmailText.getText().toString(),
                            termID,
                            notesText.getText().toString()));
                }
                else viewModel.updateCourse(new Course(i.getIntExtra("Course_ID", 0),
                        nameText.getText().toString(),
                        startText.getText().toString(),
                        endText.getText().toString(),
                        statusText.getText().toString(),
                        instructorNameText.getText().toString(),
                        instructorPhoneText.getText().toString(),
                        instructorEmailText.getText().toString(),
                        termID,
                        notesText.getText().toString()));

                Intent intent = new Intent(AddEditCourseActivity.this, TermViewActivity.class);
                Term current = viewModel.getTermByID(termID);
                intent.putExtra("Term_ID", current.getTermID());
                intent.putExtra("Term_Name", current.getName());
                intent.putExtra("Term_Start", current.getStartDate());
                intent.putExtra("Term_End", current.getEndDate());
                startActivity(intent);
            }
        });

        courseStartListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                startText.setText(month + "/" + day + "/" + year);
            }
        };

        startText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int month, day, year;
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog endDateDialog = new DatePickerDialog(
                        AddEditCourseActivity.this,

                        android.R.style.Theme_Translucent,
                        courseStartListener,
                        year,
                        month,
                        day
                );

                endDateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                endDateDialog.show();
            }
        });

        courseEndListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                endText.setText(month + "/" + day + "/" + year);
            }
        };

        endText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int month, day, year;
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog endDateDialog = new DatePickerDialog(
                        AddEditCourseActivity.this,

                        android.R.style.Theme_Translucent,
                        courseEndListener,
                        year,
                        month,
                        day
                );

                endDateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                endDateDialog.show();
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