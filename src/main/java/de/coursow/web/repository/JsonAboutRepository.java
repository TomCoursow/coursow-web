package de.coursow.web.repository;

import tools.jackson.databind.ObjectMapper;
import de.coursow.web.model.About;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class JsonAboutRepository implements AboutRepository {

    private static final String ABOUT_PATH = "classpath:data/about.json";

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public JsonAboutRepository(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    @Override
    public About getAbout() {
        try {
            InputStream aboutDataStream = resourceLoader.getResource(ABOUT_PATH).getInputStream();
            return objectMapper.readValue(aboutDataStream, About.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load about data", e);
        }
    }
}
