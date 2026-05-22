package de.coursow.web.repository.jpa;

import de.coursow.web.entity.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyJpaRepository extends JpaRepository<TechnologyEntity, Long> {
}
