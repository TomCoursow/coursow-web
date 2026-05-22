package de.coursow.web.service;

import de.coursow.web.mapper.AboutMapper;
import de.coursow.web.model.About;
import de.coursow.web.repository.jpa.AboutJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AboutService {
    private static final Logger log = LoggerFactory.getLogger(AboutService.class);
    private final AboutJpaRepository repository;
    private final AboutMapper mapper;

    public AboutService(AboutJpaRepository repository, AboutMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public About getAbout() {
        log.debug("Fetching about information");
        return repository.findAll()
                .stream()
                .findFirst()
                .map(mapper::toVO)
                .orElseThrow(() -> {
                    log.error("No about entry found in database");
                    return new RuntimeException("About not found");
                });
    }
}
