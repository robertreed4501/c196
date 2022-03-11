package com.example.c196.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "Terms")
public class Term {

    public Term(String name, Date startDate, Date endDate){
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
    private Date startDate;

    @ColumnInfo (name = "Term_End")
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}