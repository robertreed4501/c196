package com.example.c196.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "Assessments", foreignKeys = @ForeignKey(entity = Course.class, parentColumns = "Course_ID", childColumns = "Associated_Course_ID"))
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Assessment_ID")
    public int assessmentID;

    @ColumnInfo(name = "Assessment_Name")
    public String assessmentName;

    public int getAssessmentID() {
        return assessmentID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public String getAssessmentStart() {
        return assessmentStart;
    }

    public String getAssessmentEnd() {
        return assessmentEnd;
    }

    public int getAssociatedCourseID() {
        return associatedCourseID;
    }

    @ColumnInfo(name = "Assessment_Type")
    public String assessmentType;

    @ColumnInfo(name = "Assessment_Start")
    public String assessmentStart;

    @ColumnInfo(name = "Assessment_End")
    public String assessmentEnd;

    @ColumnInfo(name = "Associated_Course_ID", index = true)
    public int associatedCourseID;

    @Ignore
    public Assessment(int assessmentID, String assessmentName, String assessmentType, String assessmentStart, String assessmentEnd, int associatedCourseID) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentStart = assessmentStart;
        this.assessmentEnd = assessmentEnd;
        this.associatedCourseID = associatedCourseID;
    }

    public Assessment(String assessmentName, String assessmentType, String assessmentStart, String assessmentEnd, int associatedCourseID) {
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentStart = assessmentStart;
        this.assessmentEnd = assessmentEnd;
        this.associatedCourseID = associatedCourseID;
    }
}
