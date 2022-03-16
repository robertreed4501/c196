package com.example.c196.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.time.LocalDate;
import java.util.Date;

@Entity (tableName = "Courses", foreignKeys =  @ForeignKey(entity=Term.class, parentColumns="Term_ID", childColumns="Associated_Term_ID"))
public class Course {

    public int getCourseID() {
        return courseID;
    }

    @Ignore
    public Course(String courseName, String courseStart, String courseEnd, String courseStatus, int termID) {
        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.termID = termID;
    }

    public Course(String courseName, String courseStart, String courseEnd, String courseStatus, String instructorName, String instructorPhone, String instructorEmail, int termID) {
        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.termID = termID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public int getTermID() {
        return termID;
    }

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "Course_ID")
    public int courseID;

    @ColumnInfo (name = "Course_Name")
    public String courseName;

    @ColumnInfo (name = "Course_Start")
    public String courseStart;

    @ColumnInfo (name = "Course_End")
    public String courseEnd;

    @ColumnInfo (name = "Course_Status")
    public String courseStatus;

    @ColumnInfo (name = "Instructor_Name")
    public String instructorName;

    @ColumnInfo (name = "Instructor_Phone")
    public String instructorPhone;

    @ColumnInfo (name = "Instructor_Email")
    public String instructorEmail;

    @ColumnInfo(name = "Associated_Term_ID", index = true)
    public int termID;
}
