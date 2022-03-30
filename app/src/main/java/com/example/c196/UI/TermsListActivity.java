package com.example.c196.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c196.Database.Repository;
import com.example.c196.Model.Course;
import com.example.c196.Model.Term;
import com.example.c196.R;
import com.example.c196.ViewModel.TermListAdapter;
import com.example.c196.ViewModel.TermViewHolder;
import com.example.c196.ViewModel.ViewModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TermsListActivity extends AppCompatActivity {

    private ViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);

        getSupportActionBar().setTitle("All Terms");
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermListAdapter adapter = new TermListAdapter(new TermListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        /*adapter.setList();*/
        viewModel.getVmAllTerms().observe(this, terms -> {
            adapter.submitList(terms);
        });



        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Term current = TermViewHolder.getTermByPosition(position);
                int termID = current.getTermID();
                if(!viewModel.getCourseList().stream().anyMatch(course -> course.getTermID()==termID)){
                    viewModel.deleteTerm(current);
                    Toast.makeText(
                            TermsListActivity.this,
                            "Term \"" + current.getName() + "\" Deleted.",
                            Toast.LENGTH_LONG)
                            .show();
                }
                else{
                    Toast.makeText(
                            TermsListActivity.this,
                            "Term Not Deleted.\nCannot Delete Term With Registered Courses.",
                            Toast.LENGTH_LONG)
                            .show();
                    viewModel.updateTerm(TermViewHolder.getTermByPosition(position));
                }
                adapter.notifyDataSetChanged();
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
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    public void addTermFABClicked(View view){
        Intent i = new Intent(TermsListActivity.this, AddEditTermActivity.class);
        i.putExtra("isNewTerm", true);
        startActivity(i);
    }
}
