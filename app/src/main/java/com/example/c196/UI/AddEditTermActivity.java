package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c196.Model.Term;
import com.example.c196.R;
import com.example.c196.ViewModel.ViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class AddEditTermActivity extends AppCompatActivity {

    private TextView termStart;
    private TextView termEnd;
    private DatePickerDialog.OnDateSetListener termStartListener;
    private DatePickerDialog.OnDateSetListener termEndListener;
    private Button saveTermButton;
    private TextView termName;

    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_term);

        viewModel = new ViewModel(getApplication());
        termStart = findViewById(R.id.termStartClickableText);
        termEnd = findViewById(R.id.termEndClickableText);

        termStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int month, day, year;
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog startDateDialog = new DatePickerDialog(
                        AddEditTermActivity.this,

                        android.R.style.Theme_Translucent,
                        termStartListener,
                        year,
                        month,
                        day
                );

                startDateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                startDateDialog.show();
            }
        });

        termStartListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                termStart.setText(month + "/" + day + "/" + year);
            }
        };

        termEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int month, day, year;
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog endDateDialog = new DatePickerDialog(
                        AddEditTermActivity.this,

                        android.R.style.Theme_Translucent,
                        termEndListener,
                        year,
                        month,
                        day
                );

                endDateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                endDateDialog.show();
            }
        });

        termEndListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                termEnd.setText(month + "/" + day + "/" + year);
            }
        };

        termName = findViewById(R.id.addTermNameText);
        saveTermButton = findViewById(R.id.saveTermButton);
        saveTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = termName.getText().toString();
                String start = termStart.getText().toString();
                String end = termEnd.getText().toString();
                viewModel.insertTerm(new Term(name, start, end));
                Intent i = new Intent(AddEditTermActivity.this, TermsListActivity.class);
                startActivity(i);
            }
        });
    }
}