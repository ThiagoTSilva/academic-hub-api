package org.academic.application.mappers;

import org.academic.application.dto.semester.SemesterResponse;
import org.academic.domain.Semester;

public class SemesterMapper {
    public static SemesterResponse toDTO(Semester semester) {
        SemesterResponse dto = new SemesterResponse();
        dto.setId(semester.getId());
        dto.setYear(semester.getYear());
        dto.setSemester(semester.getSemester());

        return dto;
    }

    public static Semester toEntity(SemesterResponse userDto){
        Semester semester = new Semester();
        semester.setSemester(userDto.getSemester());
        semester.setYear(userDto.getYear());
        return semester;
    }
}
