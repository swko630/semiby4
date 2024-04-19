package com.gdu.semiby4.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CalendarServiceImpl implements CalendarService {

  
@Override
  public List<Map<String, Object>> viewCalendar() {
    // TODO Auto-generated method stub
    return "/calendar.do";
  }

}
