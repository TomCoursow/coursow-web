package de.coursow.web.model;

public class Company {

    private Long id;
    private String name;
    private String timeframe;
    private String description;
    private String titleColorClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitleColorClass() {
        return titleColorClass;
    }

    public void setTitleColorClass(String titleColorClass) {
        this.titleColorClass = titleColorClass;
    }
}
