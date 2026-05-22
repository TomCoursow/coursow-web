package de.coursow.web.service;

import de.coursow.web.mapper.ProjectMapper;
import de.coursow.web.model.Project;
import de.coursow.web.repository.jpa.ProjectJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {
    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
    private final ProjectJpaRepository repository;
    private final ProjectMapper mapper;

    public ProjectService(ProjectJpaRepository repository, ProjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<Project> getProjects() {
        log.debug("Fetching all projects");
        return repository.findAll()
                .stream()
                .map(mapper::toVO)
                .toList();
    }
}
