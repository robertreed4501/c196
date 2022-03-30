package com.example.c196.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196.Model.Course;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CourseDAO {

    @Insert
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM Courses")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM Courses WHERE Course_ID=:courseID")
    List<Course> getCourseByID(int courseID);

    @Query("SELECT * FROM Courses WHERE Associated_Term_ID=:termID")
    LiveData<List<Course>> getCourseByTermID(int termID);

    @Query("SELECT * FROM Courses")
    List<Course> getCourseList();
}
