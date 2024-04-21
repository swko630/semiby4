<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js"></script>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prevYear,prev,next,nextYear today',
        center: 'title',
        right: 'dayGridMonth,dayGridWeek,dayGridDay'
      },
      initialDate: '2023-01-12',
      navLinks: true, // can click day/week names to navigate views
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: [
        {
          title: 'All Day Event',
          start: '2023-01-01'
        },
        {
          title: 'Long Event',
          start: '2023-01-07',
          end: '2023-01-10'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2023-01-09T16:00:00'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2023-01-16T16:00:00'
        },
        {
          title: 'Conference',
          start: '2023-01-11',
          end: '2023-01-13'
        },
        {
          title: 'Meeting',
          start: '2023-01-12T10:30:00',
          end: '2023-01-12T12:30:00'
        },
        {
          title: 'Lunch',
          start: '2023-01-12T12:00:00'
        },
        {
          title: 'Meeting',
          start: '2023-01-12T14:30:00'
        },
        {
          title: 'Happy Hour',
          start: '2023-01-12T17:30:00'
        },
        {
          title: 'Dinner',
          start: '2023-01-12T20:00:00'
        },
        {
          title: 'Birthday Party',
          start: '2023-01-13T07:00:00'
        },
        {
          title: 'Click for Google',
          url: 'http://google.com/',
          start: '2023-01-28'
        }
      ]
    });

    calendar.render();
  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>
</head>
<body>

  <div id='calendar'></div>

<script>
document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    events: '/calendar', // 백엔드 API 엔드포인트 주소
    editable: true,
    selectable: true,
    dateClick: function(info) {
      // 새로운 이벤트 생성
      var title = prompt('일정 제목을 입력하세요:');
      if (title) {
        $.ajax({
          url: '/calendar',
          method: 'POST',
          contentType: 'application/json',
          data: JSON.stringify({
            title: title,
            startDate: info.dateStr,
            endDate: info.dateStr,
            contents: ""
          }),
          success: function(data) {
            calendar.refetchEvents();
          }
        });
      }
    },
    eventClick: function(info) {
      // 이벤트 수정 또는 삭제
      var action = confirm('일정을 수정 또는 삭제하시겠습니까?');
      if (action) {
        // 수정 또는 삭제 동작
        var eventId = info.event.id;
        var title = info.event.title;
        var start = info.event.start;
        var end = info.event.end;
        var contents = info.event.extendedProps.contents;
        var updateData = {
          title: title,
          startDate: start,
          endDate: end,
          contents: contents
        };
        if (!eventId) {
          // 새 이벤트를 업데이트
          $.ajax({
            url: '/calendar',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(updateData),
            success: function(data) {
              calendar.refetchEvents();
            }
          });
        } else {
          // 기존 이벤트 업데이트
          $.ajax({
            url: '/events/' + eventId,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updateData),
            success: function(data) {
              calendar.refetchEvents();
            }
          });
        }
      } else {
        // 삭제 동작
        var eventId = info.event.id;
        if (eventId) {
          $.ajax({
            url: '/events/' + eventId,
            method: 'DELETE',
            success: function(data) {
              calendar.refetchEvents();
            }
          });
        }
      }
    }
  });

  calendar.render();
});
</script>



</body>
</html>
