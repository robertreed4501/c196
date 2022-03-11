package com.example.c196.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


import java.util.Date;

@Entity (tableName = "Courses", foreignKeys =  @ForeignKey(entity=Term.class, parentColumns="Term_ID", childColumns="Associated_Term_ID"))
public class Course {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "Course_ID")
    public int courseID;

    @ColumnInfo (name = "Course_Name")
    public String courseName;

    @ColumnInfo (name = "Course_Start")
    public Date courseStart;

    @ColumnInfo (name = "Course_End")
    public Date courseEnd;

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
