package org.academic.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.academic.domain.Course;

@ApplicationScoped
public class CourseRepository implements PanacheRepositoryBase<Course, Long> {
}
