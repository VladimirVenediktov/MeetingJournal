package com.meeting.app.repo;

import com.meeting.app.models.EmployeeMeetingLink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeMeetingLinkRepo extends CrudRepository<EmployeeMeetingLink, Long> {

    @Query("SELECT um.user FROM EmployeeMeetingLink um WHERE um.meetingId = :meetingId")
    List<String> findByMeetingId(@Param("meetingId") int meetingId);
}
