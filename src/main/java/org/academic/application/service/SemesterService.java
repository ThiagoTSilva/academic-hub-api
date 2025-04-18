package org.academic.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.academic.application.dto.semester.SemesterResponse;
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

    public List<SemesterResponse> getAll() {
        return semesterRepository
                .listAll()
                .stream()
                .map(SemesterMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Transactional
    public void save(SemesterResponse semesterResponse) {
        semesterRepository.persist(SemesterMapper.toEntity(semesterResponse));
    }

    @Transactional
    public SemesterResponse update(Long id, SemesterResponse semesterResponse) {
        Semester existSemester = semesterRepository.findById(id);

        if (existSemester == null) {
            return new SemesterResponse();
        }

        existSemester.setSemester(semesterResponse.getSemester());
        existSemester.setYear(semesterResponse.getYear());

        semesterRepository.persist(existSemester);

        return SemesterMapper.toDTO(existSemester);
    }

    @Transactional
    public boolean delete(Long id) {
        Semester existing = semesterRepository.findById(id);
        if (existing != null) {
            semesterRepository.delete(existing);
            return true;
        }
        return false;
    }
}
