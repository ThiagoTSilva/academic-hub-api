package org.academic.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.academic.application.dto.subject.DisciplineResponse;
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

    public List<DisciplineResponse> getAll(){
        return disciplineRepository
                .listAll()
                .stream()
                .map(DisciplineMapper::toDTO)
                .toList();
    }

    @Transactional
    public DisciplineResponse save(DisciplineResponse disciplineResponse){
        disciplineRepository.persist(DisciplineMapper.toEntity(disciplineResponse));
        return disciplineResponse;
    }

    @Transactional
    public DisciplineResponse update(Long id, DisciplineResponse disciplineResponse){
        Discipline existsDiscipline = disciplineRepository.findById(id);
        if (existsDiscipline == null) {
            return new DisciplineResponse();
        }

        existsDiscipline.setName(disciplineResponse.getName());
        existsDiscipline.setDescription(disciplineResponse.getDescription());
        existsDiscipline.setCode(disciplineResponse.getCode());

        disciplineRepository.persist(existsDiscipline);

        return DisciplineMapper.toDTO(existsDiscipline);
    }

    @Transactional
    public boolean delete(Long id){
        Discipline discipline = disciplineRepository.findById(id);
        if (discipline != null) {
            disciplineRepository.delete(discipline);
            return true;
        }
        return false;
    }

}
