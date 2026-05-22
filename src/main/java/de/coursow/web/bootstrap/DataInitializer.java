package de.coursow.web.bootstrap;

import de.coursow.web.entity.CompanyEntity;
import de.coursow.web.entity.ProjectEntity;
import de.coursow.web.mapper.AboutMapper;
import de.coursow.web.mapper.CompanyMapper;
import de.coursow.web.mapper.ProjectMapper;
import de.coursow.web.model.About;
import de.coursow.web.model.CompaniesWrapper;
import de.coursow.web.model.ProjectsWrapper;
import de.coursow.web.repository.jpa.AboutJpaRepository;
import de.coursow.web.repository.jpa.CompanyJpaRepository;
import de.coursow.web.repository.jpa.ProjectJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final AboutJpaRepository aboutJpaRepository;
    private final CompanyJpaRepository companyJpaRepository;
    private final ProjectJpaRepository projectJpaRepository;
    private final AboutMapper aboutMapper;
    private final CompanyMapper companyMapper;
    private final ProjectMapper projectMapper;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public DataInitializer(AboutJpaRepository aboutJpaRepository,
                           CompanyJpaRepository companyJpaRepository,
                           ProjectJpaRepository projectJpaRepository,
                           AboutMapper aboutMapper,
                           CompanyMapper companyMapper,
                           ProjectMapper projectMapper,
                           ResourceLoader resourceLoader,
                           ObjectMapper objectMapper) {
        this.aboutJpaRepository = aboutJpaRepository;
        this.companyJpaRepository = companyJpaRepository;
        this.projectJpaRepository = projectJpaRepository;
        this.aboutMapper = aboutMapper;
        this.companyMapper = companyMapper;
        this.projectMapper = projectMapper;
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) {
        if (aboutJpaRepository.count() > 0) {
            log.info("Database already seeded, skipping data initialization");
            return;
        }
        log.info("Seeding database from JSON files");
        seedAbout();
        seedCompanies();
        seedProjects();
        log.info("Database seeding completed");
    }

    private void seedAbout() {
        About about = readJson("classpath:data/about.json", About.class);
        about.setId(null);
        aboutJpaRepository.save(aboutMapper.toEntity(about));
    }

    private void seedCompanies() {
        CompaniesWrapper wrapper = readJson("classpath:data/companies.json", CompaniesWrapper.class);
        List<CompanyEntity> entities = wrapper.getCompanies()
                .stream()
                .peek(c -> c.setId(null))
                .map(companyMapper::toEntity)
                .toList();
        companyJpaRepository.saveAll(entities);
    }

    private void seedProjects() {
        ProjectsWrapper wrapper = readJson("classpath:data/projects.json", ProjectsWrapper.class);
        List<ProjectEntity> entities = wrapper.getProjects()
                .stream()
                .peek(p -> p.setId(null))
                .map(projectMapper::toEntity)
                .toList();
        projectJpaRepository.saveAll(entities);
    }

    private <T> T readJson(String path, Class<T> type) {
        try {
            InputStream stream = resourceLoader.getResource(path).getInputStream();
            return objectMapper.readValue(stream, type);
        } catch (IOException e) {
            log.error("Failed to read {}", path, e);
            throw new RuntimeException("Failed to read " + path, e);
        }
    }
}
