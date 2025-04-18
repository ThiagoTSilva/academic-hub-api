package org.academic.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.academic.domain.SemesterCourseDiscipline;

@ApplicationScoped
public class SemesterCourseDisciplineRepository implements PanacheRepositoryBase<SemesterCourseDiscipline, Long> {
}
