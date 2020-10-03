package com.meeting.app.dto;

import lombok.Getter;
import lombok.Setter;

public class MeetingDto {

    private String theme;
    private String responsible;
    private String division;
    private String date;
    private int employeeCount;

    public MeetingDto() {

    }

    public MeetingDto(String theme, String responsible, String division, String date, int employeeCount) {
        this.theme = theme;
        this.responsible = responsible;
        this.division = division;
        this.date = date;
        this.employeeCount = employeeCount;
    }

    public String getTheme() {
        return theme;
    }

    public String getResponsible() {
        return responsible;
    }

    public String getDivision() {
        return division;
    }

    public String getDate() {
        return date;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }
}
