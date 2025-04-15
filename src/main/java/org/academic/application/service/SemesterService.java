package org.academic.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.academic.application.dto.SemesterDTO;
import org.academic.application.mappers.SemesterMapper;
import org.academic.domain.Semester;
import org.academic.infrastructure.persistence.SemesterRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SemesterService {

    private SemesterRepository semesterRepository;

    public SemesterService(SemesterRepository semesterRepository){
        this.semesterRepository = semesterRepository;
    }

    public List<SemesterDTO> getAll() {
        return semesterRepository
                .listAll()
                .stream()
                .map(SemesterMapper::toDTO)
                .collect(Collectors.toList());
    }


    public void save(SemesterDTO semesterDTO) {
        semesterRepository.persist(SemesterMapper.toEntity(semesterDTO));
    }

    public SemesterDTO update(Long id, SemesterDTO semesterDTO) {
        Semester existSemester = semesterRepository.findById(id);

        if (existSemester == null) {
            return new SemesterDTO();
        }

        existSemester.setSemester(semesterDTO.getSemester());
        existSemester.setYear(semesterDTO.getYear());

        semesterRepository.persist(existSemester);

        return SemesterMapper.toDTO(existSemester);
    }

    public boolean delete(Long id) {
        Semester existing = semesterRepository.findById(id);
        if (existing != null) {
            semesterRepository.delete(existing);
            return true;
        }
        return false;
    }
}
