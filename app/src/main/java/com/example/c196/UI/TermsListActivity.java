package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c196.Database.Repository;
import com.example.c196.Model.Term;
import com.example.c196.R;
import com.example.c196.ViewModel.TermListAdapter;
import com.example.c196.ViewModel.TermViewHolder;
import com.example.c196.ViewModel.ViewModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TermsListActivity extends AppCompatActivity {

    private ViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);

        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermListAdapter adapter = new TermListAdapter(new TermListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        /*adapter.setList();*/
        viewModel.getVmAllTerms().observe(this, terms -> {
            adapter.submitList(terms);
        });

    }

    public void addTermFABClicked(View view){
        Toast.makeText(getApplicationContext(), "It hath been clicked", Toast.LENGTH_LONG).show();
        Intent i = new Intent(TermsListActivity.this, AddEditTermActivity.class);
        startActivity(i);
    }

    /*public void addClicked(View view){
        EditText text = (findViewById(R.id.termNameField));
        String name = text.getText().toString();
        ViewModel vm = new ViewModel((Application)view.getContext().getApplicationContext());
        vm.insertTerm(new Term(name, Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(86400))));
    }*/

}

/*
* Should I have the capability to add courses without them being assigned to a term yet?
*
*
*
*
*
* */