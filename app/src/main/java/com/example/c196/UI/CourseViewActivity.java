package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c196.Model.Assessment;
import com.example.c196.Model.Term;
import com.example.c196.R;
import com.example.c196.ViewModel.AssessmentListAdapter;
import com.example.c196.ViewModel.AssessmentViewHolder;
import com.example.c196.ViewModel.CourseViewHolder;
import com.example.c196.ViewModel.TermViewHolder;
import com.example.c196.ViewModel.ViewModel;

public class CourseViewActivity extends AppCompatActivity {

    private ViewModel viewModel;
    private int associatedTermID;
    private int courseID;
    TextView courseIDText, nameText, startText, endText, statusText, instructorNameText,
            instructorPhoneText, instructorEmailText, courseNotesText;
    Button shareNotesButton, editCourseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        shareNotesButton = findViewById(R.id.shareNotesButton);
        editCourseButton = findViewById(R.id.editCourseButton);
        courseIDText = findViewById(R.id.courseViewIDText);
        nameText = findViewById(R.id.courseViewNameText);
        startText = findViewById(R.id.courseViewStartText);
        endText = findViewById(R.id.courseViewEndText);
        statusText = findViewById(R.id.courseViewStatusText);
        instructorNameText = findViewById(R.id.courseViewInstructorNameText);
        instructorPhoneText = findViewById(R.id.courseViewPhoneText);
        instructorEmailText = findViewById(R.id.courseViewEmailText);
        courseNotesText = findViewById(R.id.courseViewNotesText);

        Intent i = getIntent();
        if (i.hasExtra("Course_ID")) {
            courseIDText.setText("Course ID: " + Integer.toString(i.getIntExtra("Course_ID", 0)));
            courseID = i.getIntExtra("Course_ID", 0);
        }
        if (i.hasExtra("Course_Name"))
            nameText.setText("Course Name: " + i.getStringExtra("Course_Name"));
        if (i.hasExtra("Course_Start"))
            startText.setText("Course Start: " + i.getStringExtra("Course_Start"));
        if (i.hasExtra("Course_End"))
            endText.setText("Course End: " + i.getStringExtra("Course_End"));
        if (i.hasExtra("Course_Status"))
            statusText.setText("Course Status: " + i.getStringExtra("Course_Status"));
        if (i.hasExtra("Instructor_Name"))
            instructorNameText.setText("Instructor Name: " + i.getStringExtra("Instructor_Name"));
        if (i.hasExtra("Instructor_Phone"))
            instructorPhoneText.setText("Instructor Phone: " + i.getStringExtra("Instructor_Phone"));
        if (i.hasExtra("Instructor_Email"))
            instructorEmailText.setText("Instructor Email: " + i.getStringExtra("Instructor_Email"));
        if (i.hasExtra("Course_Notes")) courseNotesText.setText(i.getStringExtra("Course_Notes"));
        if (i.hasExtra("Associated_Term_ID"))
            associatedTermID = i.getIntExtra("Associated_Term_ID", 0);

        getSupportActionBar().setTitle("Course: " + i.getStringExtra("Course_Name"));

        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentListAdapter adapter = new AssessmentListAdapter(new AssessmentListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getAssessmentsByCourseID(i.getIntExtra("Course_ID", 0)).observe(this, courses -> {
            adapter.submitList(courses);
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteAssessment(AssessmentViewHolder.getAssessmentByPosition(viewHolder.getAdapterPosition()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                    viewHolder.itemView.setTranslationX(dX);
                }
            }
        };

        // attach the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        editCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseViewActivity.this, AddEditCourseActivity.class);
                intent.putExtra("Course_ID", i.getIntExtra("Course_ID", 0));
                intent.putExtra("Course_Name", i.getStringExtra("Course_Name"));
                intent.putExtra("Course_Start", i.getStringExtra("Course_Start"));
                intent.putExtra("Course_End", i.getStringExtra("Course_End"));
                intent.putExtra("Course_Status", i.getStringExtra("Course_Status"));
                intent.putExtra("Instructor_Name", i.getStringExtra("Instructor_Name"));
                intent.putExtra("Instructor_Phone", i.getStringExtra("Instructor_Phone"));
                intent.putExtra("Instructor_Email", i.getStringExtra("Instructor_Email"));
                intent.putExtra("Course_Notes", i.getStringExtra("Course_Notes"));
                intent.putExtra("Associated_Term_ID", associatedTermID);
                intent.putExtra("Is_New_Course", false);
                view.getContext().startActivity(intent);
            }
        });

        shareNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, courseNotesText.getText().toString());
                startActivity(intent.createChooser(intent, "Share notes with: "));
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //get current term, create intent to send term info to termViewActivity
                Term current = viewModel.getTermByID(associatedTermID);
                Intent intent = new Intent(CourseViewActivity.this, TermViewActivity.class);
                intent.putExtra("Term_ID", current.getTermID());
                intent.putExtra("Term_Name", current.getName());
                intent.putExtra("Term_Start", current.getStartDate());
                intent.putExtra("Term_End", current.getEndDate());
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addAssessmentFABClicked(View view) {
        Intent intent = new Intent(CourseViewActivity.this, AddEditAssessmentActivity.class);
        intent.putExtra("Is_New_Assessment", true);
        intent.putExtra("Associated_Course_ID", courseID);
        startActivity(intent);
    }
}