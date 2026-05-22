package de.coursow.web.controller;

import de.coursow.web.service.AboutService;
import de.coursow.web.service.CompanyService;
import de.coursow.web.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AboutService aboutService;
    private final ProjectService projectService;
    private final CompanyService companyService;

    public HomeController(AboutService aboutService, ProjectService projectService,
                          CompanyService companyService) {
        this.aboutService = aboutService;
        this.projectService = projectService;
        this.companyService = companyService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("about", aboutService.getAbout());
        model.addAttribute("projects", projectService.getProjects());
        model.addAttribute("companies", companyService.getCompanies());
        return "home";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/home";
    }
}
