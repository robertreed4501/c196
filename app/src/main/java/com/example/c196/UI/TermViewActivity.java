package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.c196.Model.Course;
import com.example.c196.R;
import com.example.c196.ViewModel.CourseListAdapter;
import com.example.c196.ViewModel.CourseViewHolder;
import com.example.c196.ViewModel.TermListAdapter;
import com.example.c196.ViewModel.TermViewHolder;
import com.example.c196.ViewModel.ViewModel;

import java.sql.SQLException;

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
        termViewIDText.setText("Term ID: " + Integer.toString(i.getIntExtra("Term_ID", 0)));
        termViewNameText.setText("Term Name:" + i.getStringExtra("Term_Name"));
        termViewStartText.setText("term Start: " + i.getStringExtra("Term_Start"));
        termViewEndText.setText("Term End: " + i.getStringExtra("Term_End"));

        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseListAdapter adapter = new CourseListAdapter(new CourseListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.insertCourse(new Course("Intro to Computing", "3/1/2017", "8/31/2017", "Enrolled", "Stan Pethel", "stan@berry.edu", "706-291-1967", 1));
        //adapter.setList();
        viewModel.getCoursesByTermID(Integer.parseInt(termViewIDText.getText().toString().substring(9))).observe(this, courses -> {
            adapter.submitList(courses);
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                try{

                    viewModel.deleteCourse(CourseViewHolder.getCourseByPosition(viewHolder.getAdapterPosition()));
                    adapter.notifyDataSetChanged();
                }
                catch (SQLiteConstraintException e){
                    Dialog dialog = new Dialog(TermViewActivity.this);
                    dialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    final float alpha = 1 - Math.abs(dX) / (float)viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                    viewHolder.itemView.setTranslationX(dX);
                }
            }
        };

// attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


    }

    public void termViewFABClicked(View  v){
        Intent i = new Intent(TermViewActivity.this, AddEditCourseActivity.class);
        i.putExtra("Term_ID", Integer.parseInt(termViewIDText.getText().toString().substring(9)));
        startActivity(i);

    }

    public void editTermClicked(View v){
        Intent i = new Intent(TermViewActivity.this, AddEditTermActivity.class);
        i.putExtra("name", termViewNameText.getText());
        i.putExtra("start", termViewStartText.getText());
        i.putExtra("end", termViewEndText.getText());
        i.putExtra("isNewTerm", false);
        i.putExtra("termID", Integer.parseInt(termViewIDText.getText().toString().substring(9)));
        startActivity(i);
    }
}