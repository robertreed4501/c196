package com.example.c196.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196.Model.Term;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TermDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Term term);

    @Update
    void update (Term term);

    @Delete
    void delete (Term term);

    @Query("SELECT * FROM Terms")
    LiveData<List<Term>> getAllTerms();

    @Query("SELECT * FROM Terms WHERE Term_ID=:termID")
    List<Term> getTermByID(int termID);


}
