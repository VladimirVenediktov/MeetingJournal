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
    private Long meetingId;
    private String employee;

    public EmployeeMeetingLink(Long meetingId, String employee) {
        this.meetingId = meetingId;
        this.employee = employee;
    }

    public EmployeeMeetingLink() {
    }
}
