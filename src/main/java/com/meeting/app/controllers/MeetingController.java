package com.meeting.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeetingController {

    @GetMapping("/meet")
    public String createMeet(Model model)
    {
        return "meet-main";
    }
}
