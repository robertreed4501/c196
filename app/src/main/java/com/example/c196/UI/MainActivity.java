package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196.Database.TermDAO;
import com.example.c196.R;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Degree Plan Scheduler");
    }

    public void toAddTermClicked(View view){
        Intent i = new Intent(this, TermsListActivity.class);
        startActivity(i);
    }
}