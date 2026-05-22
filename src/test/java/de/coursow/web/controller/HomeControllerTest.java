package de.coursow.web.controller;

import de.coursow.web.model.About;
import de.coursow.web.model.Company;
import de.coursow.web.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Test
    @SuppressWarnings("unchecked")
    public void testHomePage() {
        Model model = new ConcurrentModel();

        String viewName = homeController.homePage(model);

        assertEquals("home", viewName);

        About about = (About) model.getAttribute("about");
        assertNotNull(about);
        assertNotNull(about.getTitleGreeting());
        assertFalse(about.getTitleGreeting().isBlank());
        assertNotNull(about.getNameHighlight());
        assertFalse(about.getNameHighlight().isBlank());

        List<Project> projects = (List<Project>) model.getAttribute("projects");
        assertNotNull(projects);
        assertFalse(projects.isEmpty());

        List<Company> companies = (List<Company>) model.getAttribute("companies");
        assertNotNull(companies);
        assertFalse(companies.isEmpty());
    }

    @Test
    public void testHomeRedirect() {
        String viewName = homeController.home();

        assertEquals("redirect:/home", viewName);
    }
}
