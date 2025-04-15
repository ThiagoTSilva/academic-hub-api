package org.academic.application.mappers;

import org.academic.application.dto.CourseDTO;
import org.academic.application.dto.SemesterDTO;
import org.academic.domain.Course;
import org.academic.domain.Semester;

public class CourseMapper {

    public static CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDuration(course.getDuration());

        return dto;
    }

    public static Course toEntity(CourseDTO courseDTO){
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setDuration(courseDTO.getDuration());
        return course;
    }
}
