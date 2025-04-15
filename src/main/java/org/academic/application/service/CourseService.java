package org.academic.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.academic.application.dto.CourseDTO;
import org.academic.application.mappers.CourseMapper;
import org.academic.domain.Course;
import org.academic.infrastructure.persistence.CourseRepository;

import java.util.List;

@ApplicationScoped
public class CourseService {

    private CourseRepository courseRepository;


    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public CourseDTO create(CourseDTO courseDTO) {
        courseRepository.persist(CourseMapper.toEntity(courseDTO));
        return courseDTO;
    }

    public List<CourseDTO> getAllCursos() {
        return courseRepository
                .listAll()
                .stream()
                .map(CourseMapper::toDTO)
                .toList();
    }

    public CourseDTO getCursoById(Long id) {
        return CourseMapper.toDTO(courseRepository.findById(id));
    }

    public CourseDTO update(Long id, CourseDTO courseDTO) {
        Course existsCourse = courseRepository.findById(id);
        if (existsCourse == null) return new CourseDTO();

        existsCourse.setDuration(courseDTO.getDuration());
        existsCourse.setName(courseDTO.getName());

        courseRepository.persist(existsCourse);

        return CourseMapper.toDTO(existsCourse);
    }

    public boolean delete(Long id) {
        Course existing = courseRepository.findById(id);
        if (existing != null) {
            courseRepository.delete(existing);
            return true;
        }
        return false;
    }

}
