package de.coursow.web.repository;

import tools.jackson.databind.ObjectMapper;
import de.coursow.web.model.Project;
import de.coursow.web.model.ProjectsWrapper;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class JsonProjectRepository implements ProjectRepository {

    private static final String PROJECTS_PATH = "classpath:data/projects.json";

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public JsonProjectRepository(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Project> getProjects() {
        try {
            InputStream projectsDataStream = resourceLoader.getResource(PROJECTS_PATH).getInputStream();
            ProjectsWrapper wrapper = objectMapper.readValue(projectsDataStream, ProjectsWrapper.class);
            return wrapper.getProjects();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load projects data", e);
        }
    }
}
