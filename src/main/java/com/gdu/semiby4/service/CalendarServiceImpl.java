package com.gdu.semiby4.service;

import com.gdu.semiby4.dto.CalendarDto;
import com.gdu.semiby4.mapper.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

  @Autowired
  private CalendarMapper calendarMapper;

  @Override
  public List<CalendarDto> getAllEvents() {
    return calendarMapper.getAllEvents();
  }

  @Override
  public CalendarDto createEvent(CalendarDto calendarDto) {
    calendarMapper.createEvent(calendarDto);

    return calendarDto;
  }

  @Override
  public CalendarDto updateEvent(CalendarDto calendarDto) {
    calendarMapper.updateEvent(calendarDto);
    return calendarDto;
  }

  @Override
  public void deleteEvent(Long userNo) {
    calendarMapper.deleteEvent(userNo);
  }
}
