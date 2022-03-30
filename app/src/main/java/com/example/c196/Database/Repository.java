package com.example.c196.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.c196.Model.Assessment;
import com.example.c196.Model.Course;
import com.example.c196.Model.Term;

import java.util.List;

public class Repository {

    private TermDAO repoTermDAO;
    private CourseDAO repoCourseDAO;
    private AssessmentDAO repoAssessmentDAO;

    private LiveData<List<Term>> repoAllTerms;
    private LiveData<List<Course>> repoAllCourses;
    private LiveData<List<Assessment>> repoAllAssessments;
    private LiveData<List<Course>> repoAllCoursesInTerm;
    private List<Term> terms;
    private List<Course> courses;
    private List<Assessment> assessments;
    private LiveData<List<Assessment>> repoAllAssessmentsInCourse;

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

    public void updateTerm(Term term){
        DB.dbWriteExecutor.execute(() -> repoTermDAO.update(term));

    }

    public void deleteTerm(Term term){
        DB.dbWriteExecutor.execute(() -> repoTermDAO.delete(term));
    }

    public Term getTermByID(int id) {
        DB.dbWriteExecutor.execute(() -> {
            terms = repoTermDAO.getTermByID(id);
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return terms.get(0);
    }

    public void deleteCourse(Course course) {
        DB.dbWriteExecutor.execute(() -> repoCourseDAO.delete(course));
    }

    public LiveData<List<Assessment>> getAssessmentsByCourseID(int courseID){
        repoAllAssessmentsInCourse = repoAssessmentDAO.getAssessmentsByCourseID(courseID);
        return repoAllAssessmentsInCourse;
    }

    public void updateCourse(Course course) {
        DB.dbWriteExecutor.execute(() -> repoCourseDAO.update(course))  ;
    }

    public void updateAssessment(Assessment assessment) {
        DB.dbWriteExecutor.execute(() -> repoAssessmentDAO.update(assessment));
    }

    public Course getCourseByID(int courseID) {
        DB.dbWriteExecutor.execute(() -> {
            courses = repoCourseDAO.getCourseByID(courseID);
        });
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return courses.get(0);
    }

    public List<Course> getCourseList(){
        DB.dbWriteExecutor.execute(() -> courses = repoCourseDAO.getCourseList());
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Assessment> getAssessmentList(){
        DB.dbWriteExecutor.execute(() -> assessments = repoAssessmentDAO.getCourseList());
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return assessments;
    }

    public void deleteAssessment(Assessment assessment) {
        DB.dbWriteExecutor.execute(() -> repoAssessmentDAO.delete(assessment));
    }
}