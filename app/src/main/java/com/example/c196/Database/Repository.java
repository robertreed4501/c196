package com.example.c196.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.c196.Model.Assessment;
import com.example.c196.Model.Course;
import com.example.c196.Model.Term;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private TermDAO repoTermDAO;
    private CourseDAO repoCourseDAO;
    private AssessmentDAO repoAssessmentDAO;

    private LiveData<List<Term>> repoAllTerms;
    private LiveData<List<Course>> repoAllCourses;
    private LiveData<List<Assessment>> repoAllAssessments;
    private LiveData<List<Course>> repoAllCoursesInTerm;

    public Repository(Application application){
        DB db = DB.getDB(application);
        repoTermDAO = db.termDAO();
        repoCourseDAO = db.courseDAO();
        repoAssessmentDAO = db.assessmentDao();
        repoAllTerms = repoTermDAO.getAllTerms();
        repoAllCourses = repoCourseDAO.getAllCourses();
        repoAllAssessments = repoAssessmentDAO.getAllAssessments();
    }

    public LiveData<List<Term>> getAllTerms(){
        return repoAllTerms;
    }

    public LiveData<List<Course>> getAllCourses() {
        return repoAllCourses;
    }

    public LiveData<List<Assessment>> getAllAssessments() {
        return repoAllAssessments;
    }

    public LiveData<List<Course>> getAllCoursesInTerm(int termID){
        repoAllCoursesInTerm = repoCourseDAO.getCourseByTermID(termID);
        return repoAllCoursesInTerm;
    }

    public void insertTerm(Term term){
        DB.dbWriteExecutor.execute(() -> repoTermDAO.insert(term));
    }

    public void insertCourse(Course course){
        DB.dbWriteExecutor.execute(() -> repoCourseDAO.insert(course));
    }

    public void insertAssessment(Assessment assessment){
        DB.dbWriteExecutor.execute(() -> repoAssessmentDAO.insert(assessment));
    }


}