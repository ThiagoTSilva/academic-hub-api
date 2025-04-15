package org.academic.application.mappers;

import org.academic.application.dto.CourseDTO;
import org.academic.application.dto.DisciplineDTO;
import org.academic.application.dto.SemesterCourseDisciplineDTO;
import org.academic.application.dto.SemesterDTO;
import org.academic.domain.Course;
import org.academic.domain.Discipline;
import org.academic.domain.Semester;
import org.academic.domain.SemesterCourseDiscipline;

public class SemesterCourseDisciplineMapper {

    public static SemesterCourseDisciplineDTO toDTO(SemesterCourseDiscipline semesterCourseDiscipline,
                                                    Course course, Semester semester, Discipline discipline) {

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDuration(course.getDuration());

        SemesterDTO semesterDTO = new SemesterDTO();
        semesterDTO.setId(semester.getId());
        semesterDTO.setSemester(semester.getSemester());
        semesterDTO.setYear(semester.getYear());

        DisciplineDTO disciplineDTO = new DisciplineDTO();
        disciplineDTO.setId(discipline.getId());
        disciplineDTO.setName(discipline.getName());
        disciplineDTO.setDescription(discipline.getDescription());
        disciplineDTO.setCode(discipline.getCode());

        SemesterCourseDisciplineDTO dto = new SemesterCourseDisciplineDTO();
        dto.setId(semesterCourseDiscipline.getId());
        dto.setCourse(courseDTO);
        dto.setSemester(semesterDTO);
        dto.setDiscipline(disciplineDTO);
        dto.setCredits(semesterCourseDiscipline.getCredits());

        return dto;
    }

    public static SemesterCourseDiscipline toEntity(SemesterCourseDisciplineDTO semesterCourseDisciplineDTO,
                                                    Course course, Semester semester, Discipline discipline){
        SemesterCourseDiscipline semesterCourseDiscipline = new SemesterCourseDiscipline();
        semesterCourseDiscipline.setCourse(course);
        semesterCourseDiscipline.setSemester(semester);
        semesterCourseDiscipline.setDiscipline(discipline);
        semesterCourseDiscipline.setCredits(semesterCourseDisciplineDTO.getCredits());

        return semesterCourseDiscipline;
    }

    public static SemesterCourseDisciplineDTO EntityToDTO(SemesterCourseDiscipline semesterCourseDisciplineDTO){

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(semesterCourseDisciplineDTO.getCourse().getId());
        courseDTO.setName(semesterCourseDisciplineDTO.getCourse().getName());
        courseDTO.setDuration(semesterCourseDisciplineDTO.getCourse().getDuration());

        SemesterDTO semesterDTO = new SemesterDTO();
        semesterDTO.setId(semesterCourseDisciplineDTO.getSemester().getId());
        semesterDTO.setSemester(semesterCourseDisciplineDTO.getSemester().getSemester());
        semesterDTO.setYear(semesterCourseDisciplineDTO.getSemester().getYear());

        DisciplineDTO disciplineDTO = new DisciplineDTO();
        disciplineDTO.setId(semesterCourseDisciplineDTO.getDiscipline().getId());
        disciplineDTO.setName(semesterCourseDisciplineDTO.getDiscipline().getName());
        disciplineDTO.setDescription(semesterCourseDisciplineDTO.getDiscipline().getDescription());
        disciplineDTO.setCode(semesterCourseDisciplineDTO.getDiscipline().getCode());

        SemesterCourseDisciplineDTO semesterCourseDiscipline = new SemesterCourseDisciplineDTO();
        semesterCourseDiscipline.setCourse(courseDTO);
        semesterCourseDiscipline.setSemester(semesterDTO);
        semesterCourseDiscipline.setDiscipline(disciplineDTO);
        semesterCourseDiscipline.setCredits(semesterCourseDisciplineDTO.getCredits());

        return semesterCourseDiscipline;
    }
}
