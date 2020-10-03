package com.meeting.app.controllers;

import com.meeting.app.dto.EmployeeDto;
import com.meeting.app.dto.MeetingDto;
import com.meeting.app.models.Employee;
import com.meeting.app.models.EmployeeMeetingLink;
import com.meeting.app.models.Meeting;
import com.meeting.app.repo.EmployeeMeetingLinkRepo;
import com.meeting.app.repo.EmployeeRepo;
import com.meeting.app.repo.MeetingsRepo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;

@Controller
public class MeetingController {

    private MeetingsRepo meetingsRepo;
    private EmployeeMeetingLinkRepo employeeMeetingLinkRepo;
    private EmployeeRepo employeeRepo;

    private final List<EmployeeDto> employeeDtoList = new ArrayList<>();

    private static final String MEETING_PAGE = "meeting";
    private static final String HOME_PAGE = "home";
    private static final String ATTRIBUTE_MEETING = "meeting";
    private static final String ATTRIBUTE_MEETING_LIST = "meetingList";
    private static final String ATTRIBUTE_EMPLOYEE_DTO = "employeeDto";
    private static final String ATTRIBUTE_EMPLOYEE_DTO_LIST = "employeeDtoList";
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public MeetingController(MeetingsRepo meetingsRepo,
        EmployeeMeetingLinkRepo employeeMeetingLinkRepo, EmployeeRepo employeeRepo) {
        this.meetingsRepo = meetingsRepo;
        this.employeeMeetingLinkRepo = employeeMeetingLinkRepo;
        this.employeeRepo = employeeRepo;
    }

    // главная страница со списком всех совещаний
    @GetMapping("/home")
    public String meetingList(Model model){
        Iterable<Meeting> meetings = meetingsRepo.findAll();
        Collection<MeetingDto> meetingList = new ArrayList<>();
        for (Meeting item : meetings) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            meetingList.add(new MeetingDto(
                item.getTheme(),
                item.getResponsible(),
                item.getDivision(),
                Optional.ofNullable(item.getDate())
                    .map(sdf::format)
                    .orElse(""),
                employeeMeetingLinkRepo.findByMeetingId(item.getId()).size()));
        }
        model.addAttribute(ATTRIBUTE_MEETING_LIST, meetingList);

        return HOME_PAGE;
    }

    // новое совещание (страница с формой)
    @GetMapping("/newMeeting")
    public String newMeeting(Model model) {
        model.addAttribute(ATTRIBUTE_MEETING, new Meeting());

        return MEETING_PAGE;
    }

    // создание нового совещания
    @PostMapping("/addMeeting")
    public String meetPostAdd(Model model, @ModelAttribute Meeting meeting) {
        Meeting savedMeeting = meetingsRepo.save(meeting);
        model.addAttribute(ATTRIBUTE_MEETING, savedMeeting);

        // получим список участников по id совещания
        List<Long> employeeIdList = employeeMeetingLinkRepo.findByMeetingId(savedMeeting.getId());
        List<EmployeeDto> employeeDtoList = employeeIdList.stream()
            .filter(Objects::nonNull)
            .map(employeeRepo::findById)
            .map(optEmployee -> optEmployee
                .map(employee ->
                    new EmployeeDto(employee.getName(), employee.getAge(), employee.getDivision()))
                .orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        model.addAttribute(ATTRIBUTE_EMPLOYEE_DTO_LIST, employeeDtoList);

        return MEETING_PAGE;
    }

    @PostMapping("/addEmployee")
    public String addEmployeeInfo(Model model, @ModelAttribute EmployeeDto employeeDto, @ModelAttribute Meeting meeting) {
        // если id совещания == null (еще не сохранялось), то очистим список участников
        if (meeting.getId() == null) {
            employeeDtoList.clear();
        }
        Meeting savedMeeting = meetingsRepo.save(meeting);

        Employee employee = employeeRepo.findByEmployeeName(employeeDto.getName());

        Optional.ofNullable(employee)
            .ifPresent(emp -> {
                employeeDto.setAge(emp.getAge());
                employeeDto.setDivision(emp.getDivision());
                // создаем связь участника с совещанием
                employeeMeetingLinkRepo.save(new EmployeeMeetingLink(savedMeeting.getId(), employee.getId()));
            });
        employeeDtoList.add(employeeDto);

        model.addAttribute(ATTRIBUTE_EMPLOYEE_DTO, employeeDto);
        model.addAttribute(ATTRIBUTE_MEETING, savedMeeting);
        model.addAttribute(ATTRIBUTE_EMPLOYEE_DTO_LIST, employeeDtoList);

        return MEETING_PAGE;
    }

}

