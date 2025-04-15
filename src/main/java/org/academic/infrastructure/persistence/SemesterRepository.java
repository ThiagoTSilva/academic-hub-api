package org.academic.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.academic.domain.Semester;

@ApplicationScoped
public class SemesterRepository implements PanacheRepositoryBase<Semester, Long> {
}
