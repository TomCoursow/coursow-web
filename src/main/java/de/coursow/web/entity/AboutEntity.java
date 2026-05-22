package de.coursow.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "about")
public class AboutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleGreeting;

    private String nameHighlight;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleGreeting() {
        return titleGreeting;
    }

    public void setTitleGreeting(String titleGreeting) {
        this.titleGreeting = titleGreeting;
    }

    public String getNameHighlight() {
        return nameHighlight;
    }

    public void setNameHighlight(String nameHighlight) {
        this.nameHighlight = nameHighlight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
