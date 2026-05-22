package de.coursow.web.repository.jpa;

import de.coursow.web.entity.AboutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutJpaRepository extends JpaRepository<AboutEntity, Long> {
}
