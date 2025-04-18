package org.academic.application.dto.curriculum;


import org.academic.application.dto.course.CourseResponse;
import org.academic.application.dto.semester.SemesterResponse;
import org.academic.application.dto.subject.DisciplineResponse;

public class SemesterCourseDisciplineResponse {

    private Long id;

    private SemesterResponse semester;

    private CourseResponse course;

    private DisciplineResponse discipline;

    private Integer credits;

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public DisciplineResponse getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineResponse discipline) {
        this.discipline = discipline;
    }

    public CourseResponse getCourse() {
        return course;
    }

    public void setCourse(CourseResponse course) {
        this.course = course;
    }

    public SemesterResponse getSemester() {
        return semester;
    }

    public void setSemester(SemesterResponse semester) {
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
