package de.coursow.web.repository;

import tools.jackson.databind.ObjectMapper;
import de.coursow.web.model.CompaniesWrapper;
import de.coursow.web.model.Company;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class JsonCompanyRepository implements CompanyRepository {

    private static final String COMPANIES_PATH = "classpath:data/companies.json";

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public JsonCompanyRepository(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Company> getCompanies() {
        try {
            InputStream companiesDataStream = resourceLoader.getResource(COMPANIES_PATH).getInputStream();
            CompaniesWrapper wrapper = objectMapper.readValue(companiesDataStream, CompaniesWrapper.class);
            return wrapper.getCompanies();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load companies data", e);
        }
    }
}
