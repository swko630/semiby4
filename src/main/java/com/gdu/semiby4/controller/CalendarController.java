package com.gdu.semiby4.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import oracle.sql.json.OracleJsonParser.Event;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String showCalendar(Model model) {
        // FullCalendar를 위한 데이터를 모델에 추가
        // 예를 들어, 일정 목록을 가져와서 모델에 추가하거나, 모델에 직접 일정 데이터를 추가할 수 있습니다.
        // 여기서는 예시로 더미 데이터를 추가하겠습니다.
        model.addAttribute("events", getDummyEvents());

        return "calendar";
    }

    private List<Event> getDummyEvents() {
        List<Event> events = new ArrayList<>();
        // 여기서는 간단한 더미 데이터를 생성하여 반환합니다.
        // 실제 프로덕션 환경에서는 데이터베이스나 외부 소스에서 일정 데이터를 가져와야 합니다.
        // 이 데이터는 FullCalendar의 이벤트 객체 형식을 따라야 합니다.
        // 이벤트 객체에는 id, title, start, end, 등의 속성이 있습니다.
        events.add(new Event(1L, "Event 1", "2024-04-20", "2024-04-21"));
        events.add(new Event(2L, "Event 2", "2024-04-22", "2024-04-23"));
        return events;
    }
}
