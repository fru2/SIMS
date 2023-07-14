package com.patnacollege.sims.dataModel;

public class AssignmentDataModel {
    private String title;
    private String description;
    private String dueDate;
    private String subject;


    public AssignmentDataModel() {
        // Default constructor required for Firebase
    }

    public AssignmentDataModel(String title, String description, String dueDate, String subject) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.subject = subject;

    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getDueDate() {
        return dueDate;
    }
    public String getSubject() {
        return subject;
    }
}