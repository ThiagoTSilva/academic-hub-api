package org.academic.application.mappers;

import org.academic.application.dto.DisciplineDTO;
import org.academic.domain.Discipline;

public class DisciplineMapper {

    public static DisciplineDTO toDTO(Discipline discipline) {
        DisciplineDTO dto = new DisciplineDTO();
        dto.setId(discipline.getId());
        dto.setCode(discipline.getCode());
        dto.setName(discipline.getName());
        dto.setDescription(discipline.getDescription());

        return dto;
    }

    public static Discipline toEntity(DisciplineDTO disciplineDTO){
        Discipline discipline = new Discipline();
        discipline.setId(disciplineDTO.getId());
        discipline.setName(disciplineDTO.getName());
        discipline.setCode(disciplineDTO.getCode());
        discipline.setDescription(discipline.getDescription());

        return discipline;
    }
}
