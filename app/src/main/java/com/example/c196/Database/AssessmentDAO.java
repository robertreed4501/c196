package com.example.c196.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196.Model.Assessment;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface AssessmentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM Assessments")
    LiveData<List<Assessment>> getAllAssessments();

    @Query("SELECT * FROM Assessments WHERE Assessment_ID=:assessmentID")
    Assessment getAssessmentByID(int assessmentID);

    @Query("SELECT * FROM Assessments WHERE Associated_Course_ID=:courseID")
    LiveData<List<Assessment>> getAssessmentsByCourseID(int courseID);
}
