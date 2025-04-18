package org.academic.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.academic.application.dto.course.CourseResponse;
import org.academic.application.dto.curriculum.CurriculumRequest;
import org.academic.application.dto.subject.DisciplineResponse;
import org.academic.application.dto.curriculum.SemesterCourseDisciplineResponse;
import org.academic.application.dto.semester.SemesterResponse;
import org.academic.application.mappers.CourseMapper;
import org.academic.application.mappers.DisciplineMapper;
import org.academic.application.mappers.SemesterCourseDisciplineMapper;
import org.academic.application.mappers.SemesterMapper;
import org.academic.domain.Course;
import org.academic.domain.Discipline;
import org.academic.domain.Semester;
import org.academic.domain.SemesterCourseDiscipline;
import org.academic.infrastructure.persistence.CourseRepository;
import org.academic.infrastructure.persistence.DisciplineRepository;
import org.academic.infrastructure.persistence.SemesterCourseDisciplineRepository;
import org.academic.infrastructure.persistence.SemesterRepository;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class SemesterCourseDisciplineService {

    private SemesterCourseDisciplineRepository semesterCourseDisciplineRepository;
    private CourseRepository courseRepository;
    private SemesterRepository semesterRepository;
    private DisciplineRepository disciplineRepository;

    public SemesterCourseDisciplineService(SemesterCourseDisciplineRepository semesterCourseDisciplineRepository,
                                           CourseRepository courseRepository,
                                           SemesterRepository semesterRepository,
                                           DisciplineRepository disciplineRepository){
        this.semesterCourseDisciplineRepository = semesterCourseDisciplineRepository;
        this.courseRepository = courseRepository;
        this.semesterRepository = semesterRepository;
        this.disciplineRepository = disciplineRepository;
    }

    @Transactional
    public SemesterCourseDisciplineResponse associateDiscipline(CurriculumRequest cirriculum){
        long courseId = cirriculum.getCourseId();
        long semesterId  = cirriculum.getSemesterId();
        long subjectId = cirriculum.getSubjectId();

        Course course = courseRepository.findById(courseId);
        Semester semester = semesterRepository.findById(semesterId);
        Discipline discipline = disciplineRepository.findById(subjectId);

        if (course == null || semester == null || discipline == null) {
            return new SemesterCourseDisciplineResponse();
        }

        semesterCourseDisciplineRepository.persist(SemesterCourseDisciplineMapper.toEntity(cirriculum, course, semester,discipline));

        SemesterCourseDiscipline SemesterCourseDisciplineEntity = semesterCourseDisciplineRepository.findAll().stream()
                .filter(scd -> scd.getCourse().getId().equals(courseId))
                .filter(scd -> scd.getSemester().getId().equals(semesterId))
                .filter(scd -> scd.getDiscipline().getId().equals(subjectId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Combination not found"));

        CourseResponse courseResponse = CourseMapper.toDTO(course);
        SemesterResponse semesterResponse = SemesterMapper.toDTO(semester);
        DisciplineResponse disciplineResponse = DisciplineMapper.toDTO(discipline);

        return SemesterCourseDisciplineMapper.toDTO(SemesterCourseDisciplineEntity, course, semester, discipline);
    }

    public List<SemesterCourseDisciplineResponse> viewCurriculumMatrix(Long id) {

        Course course = courseRepository.findById(id);

        if (course == null) {
            return Collections.emptyList();
        }

        List<SemesterCourseDiscipline> semesterCourseDisciplines = semesterCourseDisciplineRepository.list("course", course);

        return  semesterCourseDisciplines.stream().map(SemesterCourseDisciplineMapper::EntityToDTO).toList();
    }
}
