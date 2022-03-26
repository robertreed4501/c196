package com.example.c196.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.c196.Database.Repository;
import com.example.c196.Model.Assessment;
import com.example.c196.Model.Course;
import com.example.c196.Model.Term;

import java.util.ArrayList;
import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;

    private LiveData<List<Term>> vmAllTerms;
    private LiveData<List<Course>> vmAllCourses;
    private LiveData<List<Assessment>> vmAllAssessments;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        vmAllTerms = repository.getAllTerms();
        vmAllCourses = repository.getAllCourses();
        vmAllAssessments = repository.getAllAssessments();
    }

    public void insertTerm(Term term){
        repository.insertTerm(term);
    }

    public void insertCourse(Course course){
        repository.insertCourse(course);
    }

    public void insertAssessment(Assessment assessment){
        repository.insertAssessment(assessment);
    }

    public void updateTerm(Term term) { repository.updateTerm(term);}

    public void deleteTerm(Term term) { repository.deleteTerm(term);}

    public void deleteCourse(Course course) { repository.deleteCourse(course);}

    public Term getTermByID(int id){
        return repository.getTermByID(id);
    }

    public LiveData<List<Term>> getVmAllTerms() {
        return vmAllTerms;
    }

    public LiveData<List<Course>> getCoursesByTermID(int termID){
        return repository.getAllCoursesInTerm(termID);
    }

    public LiveData<List<Assessment>> getAssessmentsByCourseID(int courseID){
        return repository.getAssessmentsByCourseID(courseID);
    }

    public void updateCourse(Course course) {
        repository.updateCourse(course);
    }

    public void updateAssessment(Assessment assessment) {
        repository.updateAssessment(assessment);
    }

    public Course getCourseByID(int courseID) {
        return repository.getCourseByID(courseID);
    }
}
