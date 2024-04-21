package com.gdu.semiby4.service;

import com.gdu.semiby4.dto.CalendarDto;

import java.util.List;
import java.util.Map;

public interface CalendarService {
   
  List<CalendarDto> getAllEvents();
  CalendarDto createEvent(CalendarDto calendarDto);
  CalendarDto updateEvent(CalendarDto calendarDto);
  void deleteEvent(Long userNo);
}
