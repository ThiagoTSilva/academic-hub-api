package org.academic.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import org.academic.application.dto.CourseDTO;
import org.academic.application.dto.DisciplineDTO;
import org.academic.application.dto.SemesterCourseDisciplineDTO;
import org.academic.application.dto.SemesterDTO;
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
import java.util.stream.Collectors;

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

    public SemesterCourseDisciplineDTO associateDiscipline(SemesterCourseDisciplineDTO semesterCourseDisciplineDTO){
        Course course = courseRepository.findById(semesterCourseDisciplineDTO.getCourse().getId());
        Semester semester = semesterRepository.findById(semesterCourseDisciplineDTO.getSemester().getId());
        Discipline discipline = disciplineRepository.findById(semesterCourseDisciplineDTO.getDiscipline().getId());

        if (course == null || semester == null || discipline == null) {
            return new SemesterCourseDisciplineDTO();
        }

        semesterCourseDisciplineRepository.persist(SemesterCourseDisciplineMapper.toEntity(semesterCourseDisciplineDTO, course, semester,discipline));

        SemesterCourseDiscipline SemesterCourseDisciplineEntity = semesterCourseDisciplineRepository.findAll().stream()
                .filter(scd -> scd.getCourse().getId().equals(semesterCourseDisciplineDTO.getCourse().getId()))
                .filter(scd -> scd.getSemester().getId().equals(semesterCourseDisciplineDTO.getSemester().getId()))
                .filter(scd -> scd.getDiscipline().getId().equals(semesterCourseDisciplineDTO.getDiscipline().getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Combination not found"));

        CourseDTO courseDTO = CourseMapper.toDTO(course);
        SemesterDTO semesterDTO = SemesterMapper.toDTO(semester);
        DisciplineDTO disciplineDTO = DisciplineMapper.toDTO(discipline);

        return SemesterCourseDisciplineMapper.toDTO(SemesterCourseDisciplineEntity, course, semester, discipline);
    }

    public List<SemesterCourseDisciplineDTO> viewCurriculumMatrix(Long id) {

        Course course = courseRepository.findById(id);

        if (course == null) {
            return Collections.emptyList();
        }

        List<SemesterCourseDiscipline> semesterCourseDisciplines = semesterCourseDisciplineRepository.list("course", course);

        return  semesterCourseDisciplines.stream().map(SemesterCourseDisciplineMapper::EntityToDTO).toList();
    }
}
