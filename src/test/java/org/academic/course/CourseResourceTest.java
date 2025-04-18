package org.academic.course;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.academic.application.dto.course.CourseResponse;
import org.academic.application.dto.course.CourseResquest;
import org.academic.application.service.CourseService;
import org.academic.domain.Course;
import org.academic.infrastructure.persistence.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CourseResourceTest {

    @InjectMock
    CourseRepository courseRepository;

    @Inject
    CourseService courseService;

    @Test
    void testCreateCourse() {
        CourseResquest courseRequest = new CourseResquest();
        courseRequest.setName("Engenharia");
        courseRequest.setDuration(4);

        Course existingCourse = new Course();
        existingCourse.setId(1L);
        existingCourse.setName("Matemática");
        existingCourse.setCreatedAt(LocalDateTime.of(2024, 1, 1, 10, 0));

        Course savedCourse = new Course();
        savedCourse.setId(2L);
        savedCourse.setName("Engenharia");
        savedCourse.setDuration(4);
        savedCourse.setCreatedAt(LocalDateTime.of(2025, 1, 1, 10, 0));

        List<Course> allCourses = List.of(existingCourse, savedCourse);

        // Act
        Mockito.doNothing().when(courseRepository).persist(Mockito.any(Course.class));
        Mockito.when(courseRepository.listAll()).thenReturn(allCourses);

        CourseResponse response = courseService.create(courseRequest);

        // Assert
        Mockito.verify(courseRepository).persist(Mockito.any(Course.class));
        assertNotNull(response);
        assertEquals("Engenharia", response.getName());
        assertEquals(4, response.getDuration());
    }


    @Test
    void testGetAllCourse() {
        List<Course> mockList = List.of(new Course(), new Course());
        Mockito.when(courseRepository.listAll()).thenReturn(mockList);

        List<CourseResponse> result = courseService.getAllCursos();
        assertEquals(2, result.size());
    }

    @Test
    void testDeleteCourse() {
        Course course = new Course();
        course.setId(1L);

        Mockito.when(courseRepository.findById(1L)).thenReturn(course);

        boolean deleted = courseService.delete(1L);

        Assertions.assertTrue(deleted);
        Mockito.verify(courseRepository).delete(course);
    }
}
