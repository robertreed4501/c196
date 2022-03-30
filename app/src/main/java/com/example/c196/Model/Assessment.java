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
    private int assessmentID;

    @ColumnInfo(name = "Assessment_Name")
    private String assessmentName;

    @ColumnInfo(name = "Assessment_Type")
    private String assessmentType;

    @ColumnInfo(name = "Assessment_Start")
    private String assessmentStart;

    @ColumnInfo(name = "Assessment_End")
    private String assessmentEnd;

    @ColumnInfo(name = "Associated_Course_ID", index = true)
    private int associatedCourseID;

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

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public void setAssessmentStart(String assessmentStart) {
        this.assessmentStart = assessmentStart;
    }

    public void setAssessmentEnd(String assessmentEnd) {
        this.assessmentEnd = assessmentEnd;
    }

    public void setAssociatedCourseID(int associatedCourseID) {
        this.associatedCourseID = associatedCourseID;
    }
}
