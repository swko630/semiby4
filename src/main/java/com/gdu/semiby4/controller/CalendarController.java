package com.gdu.semiby4.controller;

import com.gdu.semiby4.dto.CalendarDto;
import com.gdu.semiby4.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping
    public List<CalendarDto> getAllEvents() {
        return calendarService.getAllEvents();
    }

    @PostMapping
    public CalendarDto createEvent(@RequestBody CalendarDto calendarDto) {
        return calendarService.createEvent(calendarDto);
    }

    @PutMapping("/{userNo}")
    public CalendarDto updateEvent(@PathVariable Long userNo, @RequestBody CalendarDto calendarDto) {
        calendarDto.setUserNo(userNo);
        return calendarService.updateEvent(calendarDto);
    }

    @DeleteMapping("/{userNo}")
    public void deleteEvent(@PathVariable Long userNo) {
        calendarService.deleteEvent(userNo);
    }
}
