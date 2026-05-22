package de.coursow.web.service;

import de.coursow.web.mapper.CompanyMapper;
import de.coursow.web.model.Company;
import de.coursow.web.repository.jpa.CompanyJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {
    private static final Logger log = LoggerFactory.getLogger(CompanyService.class);
    private final CompanyJpaRepository repository;
    private final CompanyMapper mapper;

    public CompanyService(CompanyJpaRepository repository, CompanyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<Company> getCompanies() {
        log.debug("Fetching all companies");
        return repository.findAll()
                .stream()
                .map(mapper::toVO)
                .toList();
    }
}
