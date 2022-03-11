package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        viewModel.getVmAllTerms().observe(this, terms -> {
            adapter.submitList(terms);
        });

    }

    public void addClicked(View view){
        EditText text = (findViewById(R.id.termNameField));
        String name = text.getText().toString();
        ViewModel vm = new ViewModel((Application)view.getContext().getApplicationContext());
        vm.insertTerm(new Term(name, Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(86400))));
    }

    public void cardClicked(Intent i){
        startActivity(i);
    }


}