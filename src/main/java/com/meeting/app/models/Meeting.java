package com.meeting.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String theme;
    private String responsible;
    private String division;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;


    public Meeting() {

    }

    public Meeting(String theme, String responsible, String division, Date date) {
        this.theme = theme;
        this.responsible = responsible;
        this.division = division;
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
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

    public Date getDate() {
        return date;
    }
}
