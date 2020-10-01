package com.meeting.app.repo;

import com.meeting.app.models.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingsRepo extends CrudRepository<Meeting, Long> {
}
