package com.example.c196.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Database.Repository;
import com.example.c196.Model.Term;
import com.example.c196.R;
import com.example.c196.UI.TermViewActivity;
import com.example.c196.UI.TermsListActivity;

public class TermViewHolder extends RecyclerView.ViewHolder {
    private final TextView termNameText;
    private final TextView termDatesText;
    private ViewModel viewModel;

    private TermViewHolder(@NonNull View itemView) {
        super(itemView);
        termNameText = itemView.findViewById(R.id.termNameText);
        termDatesText = itemView.findViewById(R.id.termDatesText);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                int position = getAdapterPosition();
                Repository repo = new Repository(viewModel.getApplication());
                Term current = repo.getAllTerms().getValue().get(position);

                Intent i = new Intent(view.getContext().getApplicationContext(), TermViewActivity.class);
                i.putExtra("Term_ID", current.getTermID());
                i.putExtra("Term_Name", current.getName());
                i.putExtra("Term_Start", current.getStartDate().toString());
                i.putExtra("Term_End", current.getEndDate().toString());
                view.getContext().startActivity(i);

            }
        });
    }

    public void bind(String name, String dates){
        termNameText.setText(name);
        termDatesText.setText(dates);
    }

    static TermViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_1, parent, false);
        return new TermViewHolder(view);
    }


}
