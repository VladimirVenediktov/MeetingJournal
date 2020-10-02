package com.meeting.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
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
}
