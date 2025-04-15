package org.academic.application.dto;


public class SemesterCourseDisciplineDTO {

    private Long id;

    private SemesterDTO semester;

    private CourseDTO course;

    private DisciplineDTO discipline;

    private Integer credits;

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public DisciplineDTO getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineDTO discipline) {
        this.discipline = discipline;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public SemesterDTO getSemester() {
        return semester;
    }

    public void setSemester(SemesterDTO semester) {
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
