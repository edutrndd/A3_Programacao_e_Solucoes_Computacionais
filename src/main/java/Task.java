package main.java;

public class Task {
    private String startDate;
    private String endDate;
    private String description;

    public Task(String startDate, String endDate, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }
}
