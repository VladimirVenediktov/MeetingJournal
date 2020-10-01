package com.meeting.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String owner;
    private String division;
    private String name;
    private Date dtBegin, dtEnd;
    @ElementCollection
    private List<String> users;

    public Meeting(String name, Date datetime, String division, String owner) {
        this.name = name;
        this.dtBegin = datetime;
        this.division = division;
        this.owner = owner;
        //this.users = users;
    }

    public Meeting() {
    }

}
