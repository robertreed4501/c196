package com.example.c196.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.c196.Model.Course;
import com.example.c196.Model.Term;
import com.example.c196.R;
import com.example.c196.Utilities.MyBroadcastReceiver;
import com.example.c196.ViewModel.ViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddEditCourseActivity extends AppCompatActivity {

    ViewModel viewModel;
    SimpleDateFormat sdf;
    Switch courseStartSwitch, courseEndSwitch;
    Spinner courseStatusSpinner;
    TextView startText, endText;
    ArrayList<String> statusList = new ArrayList<String>() {
        {
            add("Select Status");
            add("In Progress");
            add("Completed");
            add("Dropped");
            add("Plan to Take");
        }
    };
    private DatePickerDialog.OnDateSetListener courseStartListener, courseEndListener;
    private int termID;
    private boolean isNewCourse;
    private boolean notifyStart, notifyEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_course);

        //set up back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //define fields and buttons
        Button saveCourseButton = findViewById(R.id.saveCourseButton);
        TextView idText = findViewById(R.id.addEditCourseIDText);
        EditText nameText = findViewById(R.id.addEditCourseNameText);
        startText = findViewById(R.id.addEditCourseStartText);
        endText = findViewById(R.id.addEditCourseEndText);
        courseStatusSpinner = findViewById(R.id.courseStatusSpinner);
        //EditText statusText = findViewById(R.id.addEditCourseStatusText);
        EditText instructorNameText = findViewById(R.id.addEditCourseInstructorNameText);
        EditText instructorPhoneText = findViewById(R.id.addEditCoursePhoneText);
        EditText instructorEmailText = findViewById(R.id.addEditCourseEmailText);
        EditText notesText = findViewById(R.id.addEditCourseNotes);
        courseStartSwitch = findViewById(R.id.courseStartSwitch);
        courseEndSwitch = findViewById(R.id.courseEndSwitch);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statusList) {
            @Override
            public boolean isEnabled(int position) {
                // Disable the first item from Spinner
                // First item will be use for hint
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        courseStatusSpinner.setAdapter(spinnerAdapter);
        startText.setText("Tap to Select");
        endText.setText("Tap to Select");
        //unpack data from previous activity
        Intent i = getIntent();
        if (i.hasExtra("Term_ID")) termID = i.getIntExtra("Term_ID", 2);
        if (i.hasExtra("Course_ID"))
            idText.setText(Integer.toString(i.getIntExtra("Course_ID", 0)));
        if (i.hasExtra("Course_Name")) nameText.setText(i.getStringExtra("Course_Name"));
        if (i.hasExtra("Course_Start")) startText.setText(i.getStringExtra("Course_Start"));
        if (i.hasExtra("Course_End")) endText.setText(i.getStringExtra("Course_End"));
        //if (i.hasExtra("Course_Status")) statusText.setText(i.getStringExtra("Course_Status"));
        if (i.hasExtra("Course_Status"))
            courseStatusSpinner.setSelection(spinnerAdapter.getPosition(i.getStringExtra("Course_Status")));

        if (i.hasExtra("Instructor_Name"))
            instructorNameText.setText(i.getStringExtra("Instructor_Name"));
        if (i.hasExtra("Instructor_Phone"))
            instructorPhoneText.setText(i.getStringExtra("Instructor_Phone"));
        if (i.hasExtra("Instructor_Email"))
            instructorEmailText.setText(i.getStringExtra("Instructor_Email"));
        if (i.hasExtra("Course_Notes")) notesText.setText(i.getStringExtra("Course_Notes"));
        if (i.hasExtra("Associated_Term_ID")) termID = i.getIntExtra("Associated_Term_ID", 0);
        isNewCourse = i.getBooleanExtra("Is_New_Course", false);

        if (isNewCourse) getSupportActionBar().setTitle("Add Course");
        else getSupportActionBar().setTitle("Edit Course");
        //get viewModel for data access
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        if (savedInstanceState != null) {
            startText.setText(savedInstanceState.getString("Start"));
            endText.setText(savedInstanceState.getString("End"));
        }

        //if new course, insert, if not, update
        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notifyStart)
                    setNotification(startText.getText().toString(), "start", nameText.getText().toString());
                if (notifyEnd)
                    setNotification(endText.getText().toString(), "end", nameText.getText().toString());

                if (isNewCourse) {
                    viewModel.insertCourse(new Course(nameText.getText().toString(),
                            startText.getText().toString(),
                            endText.getText().toString(),
                            (String) courseStatusSpinner.getSelectedItem(),
                            instructorNameText.getText().toString(),
                            instructorPhoneText.getText().toString(),
                            instructorEmailText.getText().toString(),
                            termID,
                            notesText.getText().toString()));
                } else viewModel.updateCourse(new Course(i.getIntExtra("Course_ID", 0),
                        nameText.getText().toString(),
                        startText.getText().toString(),
                        endText.getText().toString(),
                        (String) courseStatusSpinner.getSelectedItem(),
                        instructorNameText.getText().toString(),
                        instructorPhoneText.getText().toString(),
                        instructorEmailText.getText().toString(),
                        termID,
                        notesText.getText().toString()));

                //package data for termViewActivity from current courses associatedTermID
                Intent intent = new Intent(AddEditCourseActivity.this, TermViewActivity.class);
                Term current = viewModel.getTermByID(termID);
                intent.putExtra("Term_ID", current.getTermID());
                intent.putExtra("Term_Name", current.getName());
                intent.putExtra("Term_Start", current.getStartDate());
                intent.putExtra("Term_End", current.getEndDate());
                startActivity(intent);
            }
        });

        //dateSetListener for startDate datePicker
        courseStartListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                startText.setText(month + "/" + day + "/" + year);
            }
        };

        //onClickListener for startDateText.  opens up datePicker dialog
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
                        day);
                endDateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                endDateDialog.show();
            }
        });

        //dateSetListener for endDate datePicker dialog
        courseEndListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                endText.setText(month + "/" + day + "/" + year);
            }
        };

        //onClickListener for endDateText, opens datePicker dialog
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
                        day);
                endDateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                endDateDialog.show();
            }
        });

        courseStartSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                notifyStart = compoundButton.isChecked();
            }
        });

        courseEndSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                notifyEnd = compoundButton.isChecked();
            }
        });

    }

    //define back action on back arrow click.  end activity and return to previous
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNotification(String dateString, String type, String name) {
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        Long longDate;
        Date startDate = null;
        try {
            startDate = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        longDate = startDate.getTime();
        Intent intent = new Intent(AddEditCourseActivity.this, MyBroadcastReceiver.class);
        intent.putExtra("key", "Course \"" + name + "\" " + type + "s today, " + dateString);
        PendingIntent sender = PendingIntent.getBroadcast(AddEditCourseActivity.this, MainActivity.numAlert++, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, longDate, sender);
        Toast.makeText(AddEditCourseActivity.this, "Notification set for " + type + " of course \"" + name + "\"", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Start", startText.getText().toString());
        outState.putString("End", endText.getText().toString());
    }
}