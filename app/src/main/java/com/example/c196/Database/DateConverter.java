package com.example.c196.Database;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static LocalDate toDate(String dateString) {
        return dateString == null ? null : LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/YYYY"));
    }

    @TypeConverter
    public static String toString(LocalDate date) {
        return date == null ? null : date.toString();
    }
}