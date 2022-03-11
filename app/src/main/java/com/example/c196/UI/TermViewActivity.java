package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.c196.R;

public class TermViewActivity extends AppCompatActivity {

    public TextView termViewNameText, termViewIDText, termViewStartText, termViewEndText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_view);

        Intent i = getIntent();
        termViewNameText = findViewById(R.id.termViewNameText);
        termViewIDText = findViewById(R.id.termViewIDText);
        termViewStartText = findViewById(R.id.termViewStartText);
        termViewEndText = findViewById(R.id.termViewEndText);
        termViewNameText.setText(i.getStringExtra("Term_Name"));
        termViewIDText.setText(Integer.toString(i.getIntExtra("Term_ID", 0)));
        termViewStartText.setText(i.getStringExtra("Term_Start"));
        termViewEndText.setText(i.getStringExtra("Term_End"));


    }
}