package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.c196.R;
import com.example.c196.ViewModel.CourseListAdapter;
import com.example.c196.ViewModel.TermListAdapter;
import com.example.c196.ViewModel.ViewModel;

public class TermViewActivity extends AppCompatActivity {

    public TextView termViewNameText, termViewIDText, termViewStartText, termViewEndText;
    private ViewModel viewModel;

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

        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseListAdapter adapter = new CourseListAdapter(new CourseListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        /*adapter.setList();*/
        viewModel.getCoursesByTermID(Integer.parseInt(termViewIDText.getText().toString())).observe(this, courses -> {
            adapter.submitList(courses);
        });


    }
}