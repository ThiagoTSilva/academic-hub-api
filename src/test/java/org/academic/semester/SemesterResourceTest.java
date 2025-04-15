package org.academic.semester;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.academic.application.dto.SemesterDTO;
import org.academic.application.mappers.SemesterMapper;
import org.academic.domain.Semester;
import org.academic.infrastructure.persistence.SemesterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
public class SemesterResourceTest {

    @InjectMock
    SemesterRepository semesterRepository;

    @Test
    void testCreateSemester() {
        SemesterDTO semester = new SemesterDTO();
        semester.setYear("2025");
        semester.setSemester("1");

        Semester semesterMapping = SemesterMapper.toEntity(semester);

        semesterRepository.persist(semesterMapping);

        Mockito.verify(semesterRepository).persist(semesterMapping);
    }

    @Test
    void testGetAllSemesters() {
        List<Semester> mockList = List.of(new Semester(), new Semester());
        Mockito.when(semesterRepository.listAll()).thenReturn(mockList);

        List<Semester> result = semesterRepository.listAll();
        Assertions.assertEquals(2, result.size());
    }

}
