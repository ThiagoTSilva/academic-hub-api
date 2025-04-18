package org.academic.application.mappers;

import org.academic.application.dto.course.CourseResponse;
import org.academic.application.dto.course.CourseResquest;
import org.academic.domain.Course;

import java.time.LocalDateTime;

public class CourseMapper {

    public static CourseResponse toDTO(Course course) {
        CourseResponse dto = new CourseResponse();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDuration(course.getDuration());

        return dto;
    }

    public static Course toEntity(CourseResquest courseResponse){
        Course course = new Course();
        course.setName(courseResponse.getName());
        course.setDuration(courseResponse.getDuration());
        course.setCreatedAt(LocalDateTime.now());
        return course;
    }
}
