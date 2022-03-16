package com.example.c196.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

@Entity (tableName = "Terms")
public class Term {

    public Term(String name, String startDate, String endDate){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "Term_ID")
    private int termID;

    @ColumnInfo (name = "Term_Name")
    private String name;

    @ColumnInfo (name = "Term_Start")
    private String startDate;

    @ColumnInfo (name = "Term_End")
    private String endDate;

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
