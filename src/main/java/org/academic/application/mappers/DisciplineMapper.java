package org.academic.application.mappers;

import org.academic.application.dto.subject.DisciplineResponse;
import org.academic.domain.Discipline;

public class DisciplineMapper {

    public static DisciplineResponse toDTO(Discipline discipline) {
        DisciplineResponse dto = new DisciplineResponse();
        dto.setId(discipline.getId());
        dto.setCode(discipline.getCode());
        dto.setName(discipline.getName());
        dto.setDescription(discipline.getDescription());

        return dto;
    }

    public static Discipline toEntity(DisciplineResponse disciplineResponse){
        Discipline discipline = new Discipline();
        discipline.setId(disciplineResponse.getId());
        discipline.setName(disciplineResponse.getName());
        discipline.setCode(disciplineResponse.getCode());
        discipline.setDescription(discipline.getDescription());

        return discipline;
    }
}
