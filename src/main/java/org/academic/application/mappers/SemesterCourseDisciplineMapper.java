package org.academic.application.mappers;

import org.academic.application.dto.course.CourseResponse;
import org.academic.application.dto.curriculum.CurriculumRequest;
import org.academic.application.dto.subject.DisciplineResponse;
import org.academic.application.dto.curriculum.SemesterCourseDisciplineResponse;
import org.academic.application.dto.semester.SemesterResponse;
import org.academic.domain.Course;
import org.academic.domain.Discipline;
import org.academic.domain.Semester;
import org.academic.domain.SemesterCourseDiscipline;

public class SemesterCourseDisciplineMapper {

    public static SemesterCourseDisciplineResponse toDTO(SemesterCourseDiscipline semesterCourseDiscipline,
                                                         Course course, Semester semester, Discipline discipline) {

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setName(course.getName());
        courseResponse.setDuration(course.getDuration());

        SemesterResponse semesterResponse = new SemesterResponse();
        semesterResponse.setId(semester.getId());
        semesterResponse.setSemester(semester.getSemester());
        semesterResponse.setYear(semester.getYear());

        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setId(discipline.getId());
        disciplineResponse.setName(discipline.getName());
        disciplineResponse.setDescription(discipline.getDescription());
        disciplineResponse.setCode(discipline.getCode());

        SemesterCourseDisciplineResponse dto = new SemesterCourseDisciplineResponse();
        dto.setId(semesterCourseDiscipline.getId());
        dto.setCourse(courseResponse);
        dto.setSemester(semesterResponse);
        dto.setDiscipline(disciplineResponse);
        dto.setCredits(semesterCourseDiscipline.getCredits());

        return dto;
    }

    public static SemesterCourseDiscipline toEntity(CurriculumRequest semesterCourseDisciplineDTO,
                                                    Course course, Semester semester, Discipline discipline){
        SemesterCourseDiscipline semesterCourseDiscipline = new SemesterCourseDiscipline();
        semesterCourseDiscipline.setCourse(course);
        semesterCourseDiscipline.setSemester(semester);
        semesterCourseDiscipline.setDiscipline(discipline);
        semesterCourseDiscipline.setCredits(semesterCourseDisciplineDTO.getCredits());

        return semesterCourseDiscipline;
    }

    public static SemesterCourseDisciplineResponse EntityToDTO(SemesterCourseDiscipline semesterCourseDisciplineDTO){

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(semesterCourseDisciplineDTO.getCourse().getId());
        courseResponse.setName(semesterCourseDisciplineDTO.getCourse().getName());
        courseResponse.setDuration(semesterCourseDisciplineDTO.getCourse().getDuration());

        SemesterResponse semesterResponse = new SemesterResponse();
        semesterResponse.setId(semesterCourseDisciplineDTO.getSemester().getId());
        semesterResponse.setSemester(semesterCourseDisciplineDTO.getSemester().getSemester());
        semesterResponse.setYear(semesterCourseDisciplineDTO.getSemester().getYear());

        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setId(semesterCourseDisciplineDTO.getDiscipline().getId());
        disciplineResponse.setName(semesterCourseDisciplineDTO.getDiscipline().getName());
        disciplineResponse.setDescription(semesterCourseDisciplineDTO.getDiscipline().getDescription());
        disciplineResponse.setCode(semesterCourseDisciplineDTO.getDiscipline().getCode());

        SemesterCourseDisciplineResponse semesterCourseDiscipline = new SemesterCourseDisciplineResponse();
        semesterCourseDiscipline.setCourse(courseResponse);
        semesterCourseDiscipline.setSemester(semesterResponse);
        semesterCourseDiscipline.setDiscipline(disciplineResponse);
        semesterCourseDiscipline.setCredits(semesterCourseDisciplineDTO.getCredits());

        return semesterCourseDiscipline;
    }
}
