package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c196.Model.Course;
import com.example.c196.R;
import com.example.c196.ViewModel.ViewModel;

import java.util.Calendar;

public class AddEditCourseActivity extends AppCompatActivity {

    ViewModel viewModel = new ViewModel(getApplication());
    private DatePickerDialog.OnDateSetListener courseStartListener, courseEndListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_course);

        Button saveCourseButton = findViewById(R.id.saveCourseButton);
        TextView idText = findViewById(R.id.addEditCourseIDText);
        EditText nameText = findViewById(R.id.addEditCourseNameText);
        EditText startText = findViewById(R.id.addEditCourseStartText);
        EditText endText = findViewById(R.id.addEditCourseEndText);
        EditText statusText = findViewById(R.id.addEditCourseStatusText);
        EditText instructorNameText = findViewById(R.id.addEditCourseInstructorNameText);
        EditText instructorPhoneText = findViewById(R.id.addEditCoursePhoneText);
        EditText instructorEmailText = findViewById(R.id.addEditCourseEmailText);

        Intent i = getIntent();
        int termID = i.getIntExtra("Term_ID", 0);

        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.insertCourse(new Course(nameText.getText().toString(),
                        startText.getText().toString(),
                        endText.getText().toString(),
                        statusText.getText().toString(),
                        instructorNameText.getText().toString(),
                        instructorPhoneText.getText().toString(),
                        instructorEmailText.getText().toString(),
                        termID));
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
}