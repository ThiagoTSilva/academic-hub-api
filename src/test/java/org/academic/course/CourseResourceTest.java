package org.academic.course;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.academic.application.dto.CourseDTO;
import org.academic.application.mappers.CourseMapper;
import org.academic.application.service.CourseService;
import org.academic.domain.Course;
import org.academic.infrastructure.persistence.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
public class CourseResourceTest {

    @InjectMock
    CourseRepository courseRepository;

    @Inject
    CourseService courseService;

    @Test
    void testCreateCurso() {
        CourseDTO course = new CourseDTO();
        course.setName("Engenharia");
        course.setDuration(4);

        courseService.create(course);

        Mockito.verify(courseRepository).persist(CourseMapper.toEntity(course));
    }

    @Test
    void testGetAllCursos() {
        List<Course> mockList = List.of(new Course(), new Course());
        Mockito.when(courseRepository.listAll()).thenReturn(mockList);

        List<CourseDTO> result = courseService.getAllCursos();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testDeleteCurso() {
        Course course = new Course();
        course.setId(1L);

        Mockito.when(courseRepository.findById(1L)).thenReturn(course);

        boolean deleted = courseService.delete(1L);

        Assertions.assertTrue(deleted);
        Mockito.verify(courseRepository).delete(course);
    }
}
