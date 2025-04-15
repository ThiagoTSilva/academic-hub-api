package org.academic.application.mappers;

import org.academic.application.dto.SemesterDTO;
import org.academic.application.dto.UserDTO;
import org.academic.domain.Semester;
import org.keycloak.representations.idm.UserRepresentation;

public class SemesterMapper {
    public static SemesterDTO toDTO(Semester semester) {
        SemesterDTO dto = new SemesterDTO();
        dto.setId(semester.getId());
        dto.setYear(semester.getYear());
        dto.setSemester(semester.getSemester());

        return dto;
    }

    public static Semester toEntity(SemesterDTO userDto){
        Semester semester = new Semester();
        semester.setSemester(userDto.getSemester());
        semester.setYear(userDto.getYear());
        return semester;
    }
}
