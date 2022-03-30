package com.example.c196.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.c196.Model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 1, exportSchema = false)


public abstract class DB extends RoomDatabase {

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDao();

    private static volatile DB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DB getDB(final Context context){
        //Singleton implementation to create database if it doesn't exist or return it if it does
        if (INSTANCE != null) return INSTANCE;
        synchronized (DB.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DB.class, "Database").build();
            }
        }
        return INSTANCE;
    }
}
