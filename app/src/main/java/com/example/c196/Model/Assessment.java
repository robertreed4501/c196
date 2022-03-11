package com.example.c196.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Assessments", foreignKeys = @ForeignKey(entity = Course.class, parentColumns = "Course_ID", childColumns = "Associated_Course_ID"))
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Assessment_ID")
    public int assessmentID;

    @ColumnInfo(name = "Assessment_Name")
    public String assessmentName;

    @ColumnInfo(name = "Assessment_Type")
    public String assessmentType;

    @ColumnInfo(name = "Assessment_End")
    public Date assessmentEnd;

    @ColumnInfo(name = "Associated_Course_ID", index = true)
    public int associatedCourseID;
}
