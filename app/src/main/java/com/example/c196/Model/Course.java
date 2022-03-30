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

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "Course_ID")
    private int courseID;

    @ColumnInfo (name = "Course_Name")
    private String courseName;

    @ColumnInfo (name = "Course_Start")
    private String courseStart;

    @ColumnInfo (name = "Course_End")
    private String courseEnd;

    @ColumnInfo (name = "Course_Status")
    private String courseStatus;

    @ColumnInfo (name = "Instructor_Name")
    private String instructorName;

    @ColumnInfo (name = "Instructor_Phone")
    private String instructorPhone;

    @ColumnInfo (name = "Instructor_Email")
    private String instructorEmail;

    @ColumnInfo(name = "Associated_Term_ID", index = true)
    private int termID;

    @ColumnInfo(name = "Notes")
    private String notes;


    @Ignore
    public Course(int courseID, String courseName, String courseStart, String courseEnd, String courseStatus, String instructorName, String instructorPhone, String instructorEmail, int termID, String notes) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.termID = termID;
        this.notes = notes;
    }

    @Ignore
    public Course(String courseName, String courseStart, String courseEnd, String courseStatus, int termID) {
        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.termID = termID;
    }

    public Course(String courseName, String courseStart, String courseEnd, String courseStatus, String instructorName, String instructorPhone, String instructorEmail, int termID, String notes) {
        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.termID = termID;
        this.notes = notes;
    }

    public int getCourseID() {
        return courseID;
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

    public String getNotes() {
        return notes;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
