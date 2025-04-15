package org.academic.discipline;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.academic.application.dto.DisciplineDTO;
import org.academic.application.mappers.DisciplineMapper;
import org.academic.domain.Discipline;
import org.academic.infrastructure.persistence.DisciplineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
public class DisciplineResourceTest {

    @InjectMock
    DisciplineRepository disciplineRepository;

    @Test
    void testCreateDiscipline() {
        DisciplineDTO discipline = new DisciplineDTO();
        discipline.setName("Matemática");
        discipline.setCode("001");

        Discipline disciplineMapping = DisciplineMapper.toEntity(discipline);

        disciplineRepository.persist(disciplineMapping);

        Mockito.verify(disciplineRepository).persist(disciplineMapping);
    }

    @Test
    void testGetAllDisciplines() {
        List<Discipline> mockList = List.of(new Discipline(), new Discipline());
        Mockito.when(disciplineRepository.listAll()).thenReturn(mockList);

        List<Discipline> result = disciplineRepository.listAll();
        Assertions.assertEquals(2, result.size());
    }
}
