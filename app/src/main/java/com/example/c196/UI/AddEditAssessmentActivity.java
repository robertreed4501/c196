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

import com.example.c196.Model.Assessment;
import com.example.c196.Model.Course;
import com.example.c196.R;
import com.example.c196.Utilities.MyBroadcastReceiver;
import com.example.c196.ViewModel.ViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddEditAssessmentActivity extends AppCompatActivity {

    TextView assIDText;
    EditText assNameText;
    TextView assStartText, assEndText;
    Button saveAssButton;
    Switch assStartNotifySwitch, assEndNotifySwitch;
    Spinner assTypeSpinner;
    ArrayList<String> assTypeList;
    private int associatedCourseID;
    private ViewModel viewModel;
    private DatePickerDialog.OnDateSetListener assStartListener, assEndListener;
    private SimpleDateFormat sdf;
    private boolean notifyStart, notifyEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_assessment);

        //set up back arrow button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        assTypeList = new ArrayList<String>() {
            {
                add("Select Type");
                add("Objective");
                add("Performance");
            }
        };
        assTypeSpinner = findViewById(R.id.assTypeSpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, assTypeList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
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
        assTypeSpinner.setAdapter(spinnerAdapter);
        //define fields and buttons
        assIDText = findViewById(R.id.addEditAssIDText);
        assNameText = findViewById(R.id.addEditAssNameText);

        assStartText = findViewById(R.id.addEditAssStartText);
        assEndText = findViewById(R.id.addEditAssEndText);
        saveAssButton = findViewById(R.id.saveAssButton);
        assStartNotifySwitch = findViewById(R.id.assStartNotifySwitch);
        assEndNotifySwitch = findViewById(R.id.assEndNotifySwitch);

        //populate data from intent
        Intent i = getIntent();
        if (i.hasExtra("Assessment_ID"))
            assIDText.setText(Integer.toString(i.getIntExtra("Assessment_ID", 0)));
        if (i.hasExtra("Assessment_Name"))
            assNameText.setText(i.getStringExtra("Assessment_Name"));
        if (i.hasExtra("Assessment_Type"))
            assTypeSpinner.setSelection(spinnerAdapter.getPosition(i.getStringExtra("Assessment_Type")));
        if (i.hasExtra("Assessment_Start"))
            assStartText.setText(i.getStringExtra("Assessment_Start"));
        if (i.hasExtra("Assessment_End"))
            assEndText.setText(i.getStringExtra("Assessment_End"));
        associatedCourseID = i.getIntExtra("Associated_Course_ID", 0);

        if (i.getBooleanExtra("Is_New_Assessment", false))
            getSupportActionBar().setTitle("Add Assessment");
        else getSupportActionBar().setTitle("Edit Assessment");

        if (savedInstanceState != null) {
            assStartText.setText(savedInstanceState.getInt("Start"));
            assEndText.setText(savedInstanceState.getString("End"));
        }
        //instantiate viewModel for data access
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        //set onCheckedChanged for switches
        assStartNotifySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) notifyStart = true;
                else notifyStart = false;
            }
        });

        assEndNotifySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) notifyEnd = true;
                else notifyEnd = false;
            }
        });

        //set save button onClickListener.  Insert if new, Update if not.
        saveAssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (notifyStart)
                    setNotification(assStartText.getText().toString(), "start", assNameText.getText().toString());
                if (notifyEnd)
                    setNotification(assEndText.getText().toString(), "end", assNameText.getText().toString());

                if (i.getBooleanExtra("Is_New_Assessment", true)) {
                    viewModel.insertAssessment(new Assessment(
                            assNameText.getText().toString(),
                            (String) assTypeSpinner.getSelectedItem(),
                            assStartText.getText().toString(),
                            assEndText.getText().toString(),
                            associatedCourseID
                    ));
                } else {
                    viewModel.updateAssessment(new Assessment(
                            Integer.parseInt(assIDText.getText().toString()),
                            assNameText.getText().toString(),
                            (String) assTypeSpinner.getSelectedItem(),
                            assStartText.getText().toString(),
                            assEndText.getText().toString(),
                            associatedCourseID
                    ));
                }

                //package course info from associatedCourseID into intent for courseViewActivity
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


        //dateSetListener for startDate datePicker
        assStartListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                assStartText.setText(month + "/" + day + "/" + year);
            }
        };

        //onClickListener for startDateText.  opens up datePicker dialog
        assStartText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int month, day, year;
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog endDateDialog = new DatePickerDialog(
                        AddEditAssessmentActivity.this,
                        android.R.style.Theme_Translucent,
                        assStartListener,
                        year,
                        month,
                        day);
                endDateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                endDateDialog.show();
            }
        });

        //dateSetListener for endDate datePicker dialog
        assEndListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                assEndText.setText(month + "/" + day + "/" + year);
            }
        };

        //onClickListener for endDateText, opens datePicker dialog
        assEndText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int month, day, year;
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog endDateDialog = new DatePickerDialog(
                        AddEditAssessmentActivity.this,
                        android.R.style.Theme_Translucent,
                        assEndListener,
                        year,
                        month,
                        day);
                endDateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                endDateDialog.show();
            }
        });
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
        Intent intent = new Intent(AddEditAssessmentActivity.this, MyBroadcastReceiver.class);
        intent.putExtra("key", "Assessment \"" + name + "\" " + type + "s today, " + dateString);
        PendingIntent sender = PendingIntent.getBroadcast(AddEditAssessmentActivity.this, MainActivity.numAlert++, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, longDate, sender);
        Toast.makeText(AddEditAssessmentActivity.this, "Notification set for " + type + " of assessment \"" + name + "\"", Toast.LENGTH_LONG).show();
    }

    //define back button action
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Start", assStartText.getText().toString());
        outState.putString("End", assEndText.getText().toString());
    }
}