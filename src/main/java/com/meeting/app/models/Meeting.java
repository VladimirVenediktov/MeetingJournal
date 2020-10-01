package com.meeting.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int owner;
    private int group_id;
    private String name;
    private Date dt_begin, dt_end;

    public Meeting(String name, Date dt_begin) {
        this.name = name;
        this.dt_begin = dt_begin;
    }

    public Meeting() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDt_begin() {
        return dt_begin;
    }

    public void setDt_begin(Date dt_begin) {
        this.dt_begin = dt_begin;
    }

    public Date getDt_end() {
        return dt_end;
    }

    public void setDt_end(Date dt_end) {
        this.dt_end = dt_end;
    }
}
