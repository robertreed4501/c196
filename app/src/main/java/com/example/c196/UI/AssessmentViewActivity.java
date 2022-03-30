package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.c196.R;

public class AssessmentViewActivity extends AppCompatActivity {

    private int associatedCourseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button editAssButton;
        TextView idText, nameText, typeText, startText, endText;
        idText = findViewById(R.id.assViewIDText);
        nameText = findViewById(R.id.assViewNameText);
        typeText = findViewById(R.id.assViewTypeText);
        startText = findViewById(R.id.assViewStartText);
        endText = findViewById(R.id.assViewEndText);
        editAssButton = findViewById(R.id.editAssButton);

        Intent i = getIntent();
        idText.setText(Integer.toString(i.getIntExtra("Assessment_ID", 0)));
        nameText.setText(i.getStringExtra("Assessment_Name"));
        typeText.setText(i.getStringExtra("Assessment_Type"));
        startText.setText(i.getStringExtra("Assessment_Start"));
        endText.setText(i.getStringExtra("Assessment_End"));
        associatedCourseID = i.getIntExtra("Associated_Course_ID", 0);

        getSupportActionBar().setTitle("Assessment: " + i.getStringExtra("Assessment_Name"));

        editAssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentViewActivity.this, AddEditAssessmentActivity.class);
                intent.putExtra("Assessment_ID", Integer.parseInt(idText.getText().toString()));
                intent.putExtra("Assessment_Name", nameText.getText());
                intent.putExtra("Assessment_Type", typeText.getText());
                intent.putExtra("Assessment_Start", startText.getText());
                intent.putExtra("Assessment_End", endText.getText());
                intent.putExtra("Associated_Course_ID", associatedCourseID);
                intent.putExtra("Is_New_Assessment", false);
                startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}