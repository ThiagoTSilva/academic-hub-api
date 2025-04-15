package org.academic.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.academic.application.dto.DisciplineDTO;
import org.academic.application.mappers.DisciplineMapper;
import org.academic.domain.Discipline;
import org.academic.infrastructure.persistence.DisciplineRepository;

import java.util.List;

@ApplicationScoped
public class DisciplineService {

    private DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository){
        disciplineRepository = disciplineRepository;
    }

    public List<DisciplineDTO> getAll(){
        return disciplineRepository
                .listAll()
                .stream()
                .map(DisciplineMapper::toDTO)
                .toList();
    }

    public DisciplineDTO save(DisciplineDTO disciplineDTO){
        disciplineRepository.persist(DisciplineMapper.toEntity(disciplineDTO));
        return disciplineDTO;
    }

    public DisciplineDTO update(Long id, DisciplineDTO disciplineDTO){
        Discipline existsDiscipline = disciplineRepository.findById(id);
        if (existsDiscipline == null) {
            return new DisciplineDTO();
        }

        existsDiscipline.setName(disciplineDTO.getName());
        existsDiscipline.setDescription(disciplineDTO.getDescription());
        existsDiscipline.setCode(disciplineDTO.getCode());

        disciplineRepository.persist(existsDiscipline);

        return DisciplineMapper.toDTO(existsDiscipline);
    }

    public boolean delete(Long id){
        Discipline discipline = disciplineRepository.findById(id);
        if (discipline != null) {
            disciplineRepository.delete(discipline);
            return true;
        }
        return false;
    }

}
