package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.c196.R;

public class CourseViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);

        TextView courseIDText = findViewById(R.id.courseViewIDText);
        TextView nameText = findViewById(R.id.courseViewNameText);
        TextView startText = findViewById(R.id.courseViewStartText);
        TextView endText = findViewById(R.id.courseViewEndText);
        TextView statusText = findViewById(R.id.courseViewStatusText);
        TextView instructorNameText = findViewById(R.id.courseViewInstructorNameText);
        TextView instructorPhoneText = findViewById(R.id.courseViewPhoneText);
        TextView instructorEmailText = findViewById(R.id.courseViewEmailText);

        Intent i = getIntent();
        if (i.hasExtra("Course_ID")) courseIDText.setText("Course ID: " + Integer.toString(i.getIntExtra("Course_ID", 0)));
        if (i.hasExtra("Course_Name")) nameText.setText("Course Name: " + i.getStringExtra("Course_Name"));
        if (i.hasExtra("Course_Start")) startText.setText("Course Start: " + i.getStringExtra("Course_Start"));
        if (i.hasExtra("Course_End")) endText.setText("Course End: " + i.getStringExtra("Course_End"));
        if (i.hasExtra("Course_Status")) statusText.setText("Course Status: " + i.getStringExtra("Course_Status"));
        if (i.hasExtra("Instructor_Name")) instructorNameText.setText("Instructor Name: " + i.getStringExtra("Instructor_Name"));
        if (i.hasExtra("Instructor_Phone")) instructorPhoneText.setText("Instructor Phone: " + i.getStringExtra("Instructor_Phone"));
        if (i.hasExtra("Instructor_Email")) instructorEmailText.setText("Instructor Email: " + i.getStringExtra("Instructor_Email"));



    }
}