package com.meeting.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class EmployeeMeetingLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int meetingId;
    private String user;

    protected EmployeeMeetingLink() {

    }

    public EmployeeMeetingLink(int meetingId, String user) {
        this.meetingId = meetingId;
        this.user = user;
    }
}
