package com.patnacollege.sims.dataModel;

public class ScheduleDataModel {
    private String schedule_subject;
    private String time;
    private String progress;

    public ScheduleDataModel() {
        // Default constructor required for Firebase
    }

    public ScheduleDataModel(String schedule_subject, String time, String progress) {
        this.schedule_subject = schedule_subject;
        this.time = time;
        this.progress = progress;
    }

    public String getScheduleSubject() {
        return schedule_subject;
    }
    public String getTime() {
        return time;
    }
    public String getProgress() {
        return progress;
    }
}