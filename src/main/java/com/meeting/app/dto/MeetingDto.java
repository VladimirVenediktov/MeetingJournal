package com.meeting.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingDto {

    private String theme;
    private String responsible;
    private String division;
    private String date;
    private int membersCount;

    public MeetingDto() {

    }

    public MeetingDto(String theme, String responsible, String division, String date, int membersCount) {
        this.theme = theme;
        this.responsible = responsible;
        this.division = division;
        this.date = date;
        this.membersCount = membersCount;
    }
}
