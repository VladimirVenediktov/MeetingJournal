package com.meeting.app.repo;

import com.meeting.app.models.EmployeeMeetingLink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeMeetingLinkRepo extends CrudRepository<EmployeeMeetingLink, Long> {

    @Query("SELECT em.employeeId FROM EmployeeMeetingLink em WHERE em.meetingId = :meetingId")
    List<Long> findByMeetingId(@Param("meetingId") Long meetingId);

}
