package org.academic.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Semesters")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String year;
    private String semester;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
    private List<SemesterCourseDiscipline> semesterCourseDisciplines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

}
