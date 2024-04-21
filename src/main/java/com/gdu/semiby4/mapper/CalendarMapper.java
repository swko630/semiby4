package com.gdu.semiby4.mapper;

import com.gdu.semiby4.dto.CalendarDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CalendarMapper {

    List<CalendarDto> getAllEvents();
    CalendarDto createEvent(CalendarDto calendarDto);
    CalendarDto updateEvent(CalendarDto calendarDto);
    CalendarDto deleteEvent(Long userNo);
}
