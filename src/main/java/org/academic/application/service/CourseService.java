package org.academic.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.academic.application.dto.course.CourseResponse;
import org.academic.application.dto.course.CourseResquest;
import org.academic.application.mappers.CourseMapper;
import org.academic.domain.Course;
import org.academic.infrastructure.persistence.CourseRepository;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Transactional
    public CourseResponse create(CourseResquest courseResquest) {
        courseRepository.persist(CourseMapper.toEntity(courseResquest));

        return courseRepository
                .listAll()
                .stream()
                .max(Comparator.comparing(Course::getCreatedAt))
                .map(CourseMapper::toDTO)
                .orElse(null);
    }

    public List<CourseResponse> getAllCursos() {
        return courseRepository
                .listAll()
                .stream()
                .map(CourseMapper::toDTO)
                .toList();
    }

    public List<CourseResponse> getCursoById(Long id) {
        return List.of(CourseMapper.toDTO(courseRepository.findById(id)));
    }


    @Transactional
    public CourseResponse update(Long id, CourseResquest request) {
        Course existsCourse = courseRepository.findById(id);
        if (existsCourse == null) return new CourseResponse();

        existsCourse.setDuration(request.getDuration());
        existsCourse.setName(request.getName());

        courseRepository.persist(existsCourse);

        return CourseMapper.toDTO(existsCourse);
    }

    @Transactional
    public boolean delete(Long id) {
        Course existing = courseRepository.findById(id);
        if (existing != null) {
            courseRepository.delete(existing);
            return true;
        }
        return false;
    }

}
