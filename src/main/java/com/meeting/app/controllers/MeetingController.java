package com.meeting.app.controllers;

import com.meeting.app.models.Meeting;
import com.meeting.app.repo.MeetingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class MeetingController {

    @Autowired
    private MeetingsRepo meetingsRepo;

    @GetMapping("/meet")
    public String createMeet(Model model){
        Iterable<Meeting> meetings=meetingsRepo.findAll();
        model.addAttribute("meetings", meetings);
        return "meet-main";
    }

    @PostMapping("/meet")
    public String meetPostAdd(@RequestParam String theme, @RequestParam Date time){
        Meeting meeting = new Meeting(theme, time);
        meetingsRepo.save(meeting);
        return "redirect:/";
    }


}
