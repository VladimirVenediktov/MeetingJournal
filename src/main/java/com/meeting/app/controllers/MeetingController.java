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

   /* @PostMapping("/addUserToMeeting")
    public String addUserToMeeting(@RequestParam String user){}*/


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
