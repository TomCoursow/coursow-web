package de.coursow.web.controller;

import de.coursow.web.repository.AboutRepository;
import de.coursow.web.repository.CompanyRepository;
import de.coursow.web.repository.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AboutRepository aboutRepository;
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;

    public HomeController(AboutRepository aboutRepository, ProjectRepository projectRepository,
                          CompanyRepository companyRepository) {
        this.aboutRepository = aboutRepository;
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("about", aboutRepository.getAbout());
        model.addAttribute("projects", projectRepository.getProjects());
        model.addAttribute("companies", companyRepository.getCompanies());
        return "home";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/home";
    }
}
