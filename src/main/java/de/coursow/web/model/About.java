package de.coursow.web.model;

public class About {

    private Long id;
    private String titleGreeting;
    private String nameHighlight;
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
