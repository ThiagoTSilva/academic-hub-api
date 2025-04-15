package org.academic.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.academic.domain.Discipline;

@ApplicationScoped
public class DisciplineRepository implements PanacheRepositoryBase<Discipline, Long> {
}
