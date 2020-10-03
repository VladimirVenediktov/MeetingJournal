/*
package com.meeting.app.controllers;

import com.meeting.app.models.Meeting;
import com.meeting.app.repo.UsersRepo;
import com.meeting.app.repo.MeetingsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MeetingController {

    @Autowired
    private MeetingsRepo meetingsRepo;
    private UsersRepo usersRepo;

    @GetMapping("/meet")
    public String createMeet(Model model){
        Iterable<Meeting> meetings = meetingsRepo.findAll();
        model.addAttribute("meetings", meetings);
        return "meet-main";
    }

    @GetMapping("/getUserMeeting")
    public  String getUserMeeting(Model model)
    {
        Iterable<String> users = usersRepo.findByMeetingId(5);
        System.out.println(users);
        model.addAttribute("users", users.toString());
        return "meet-main";
    }

   */
/* @PostMapping("/addUserToMeeting")
    public String addUserToMeeting(@RequestParam String user){}*//*



    @PostMapping("/meet")
    public String meetPostAdd(@RequestParam String theme, @RequestParam String timeString,
                              @RequestParam String division, @RequestParam String owner, Model model){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date time = new Date();
        try {
            time = simpleDateFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Meeting meeting = new Meeting(theme, time, division, owner);
        meetingsRepo.save(meeting);
        model.addAttribute(meeting);
        return "redirect:/";
    }
}
*/
package com.meeting.app.controllers;

import com.meeting.app.dto.EmployeeDto;
import com.meeting.app.dto.MeetingDto;
import com.meeting.app.models.Employee;
import com.meeting.app.models.EmployeeMeetingLink;
import com.meeting.app.models.Meeting;
import com.meeting.app.repo.EmployeeMeetingLinkRepo;
import com.meeting.app.repo.EmployeeRepo;
import com.meeting.app.repo.MeetingsRepo;
import com.sun.el.stream.Stream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MeetingsRepo meetingsRepo;
    @Autowired
    private EmployeeMeetingLinkRepo employeeMeetingLinkRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

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
    public String meetPostAdd(Model model, @ModelAttribute Meeting meeting) {
        /*Meeting newMeeting = new Meeting(meeting.getTheme(), meeting.getResponsible(), meeting.getDivision(),
                meeting.getDate());*/
        Meeting savedMeeting = meetingsRepo.save(meeting);

        model.addAttribute(meeting);

        return MEETING_PAGE;
    }

    @PostMapping("/addEmployeeInfo")
    public String addEmployeeInfo(Model model, @ModelAttribute EmployeeDto employeeDto) {
        Employee employee = employeeRepo.findByEmployeeName(employeeDto.getName());
        Meeting savedMeeting = meetingsRepo.save(new Meeting());


        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(employeeDto);
        for (EmployeeDto e: employeeDtoList) {
            e.setAge(employee.getAge());
            e.setDivision(employee.getDivision());
        }
        employeeMeetingLinkRepo.save(new EmployeeMeetingLink(savedMeeting.getId(), employee.getName()));

        model.addAttribute(employeeDto);
        model.addAttribute("meeting", savedMeeting);
        model.addAttribute("employeeList", employeeDtoList);

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

