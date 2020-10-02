package com.meeting.app.controllers;

import com.meeting.app.dto.MeetingDto;
import com.meeting.app.models.Meeting;
import com.meeting.app.repo.EmployeeMeetingLinkRepo;
import com.meeting.app.repo.MeetingsRepo;
import com.sun.el.stream.Stream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingsRepo meetingsRepo;
    private final EmployeeMeetingLinkRepo employeeMeetingLinkRepo;

    private static final String MEETING_PAGE = "meeting";
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    // главная страница со списком всех совещаний
    @GetMapping("/home")
    public String meetingList(Model model){
        Iterable<Meeting> meetings = meetingsRepo.findAll();
        Collection<MeetingDto> meetingList = new ArrayList<>();
        for (Meeting item : meetings) {
          SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
          meetingList.add(new MeetingDto(item.getTheme(), item.getResponsible(), item.getDivision(),
              sdf.format(item.getDate()), 0));
        }
        model.addAttribute("meetingList", meetingList);

        return "home";
    }

    // новое совещание (страница с формой)
    @GetMapping("/newMeeting")
    public String newMeeting(Model model) {
        model.addAttribute(new Meeting());
        return MEETING_PAGE;
    }

   // создание нового совещания
   @PostMapping("/addMeeting")
    public String meetPostAdd(Model model, @ModelAttribute Meeting meeting){
       Meeting newMeeting = new Meeting(meeting.getTheme(), meeting.getResponsible(), meeting.getDivision(),
           meeting.getDate());
       Meeting savedMeeting = meetingsRepo.save(newMeeting);

       // отдаем id созданного совещания, чтобы потом передать его в запрос на получение участников
       model.addAttribute("meetingId", savedMeeting.getId());
       model.addAttribute(meeting);

       return MEETING_PAGE;
    }

    /*// получение списка участников по id совещения
    @GetMapping("/getUserMeeting")
    public  String getUserMeeting(Model model)
    {
        // искать по id совещания всех участников
        Iterable<String> users = employeeMeetingLinkRepo.findByMeetingId(5);
        System.out.println(users);
        model.addAttribute("users", users.toString());

        return MEETING_PAGE;
    }*/

    // добавление участника в совещание
   /* @PostMapping("/addUserToMeeting")
    public String addUserToMeeting(@RequestParam String user){}*/
}
