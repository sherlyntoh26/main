package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import utils.Event;
import utils.Priority;

public class EventTest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testToString() {
		Calendar calendarStart = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		calendarStart.set(2015, 9, 20, 10, 33, 25);
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.set(2015, 9, 21, 11, 34, 26);
		
		Event event = new Event();
		event.setId("abc");
		event.setTitle("def");
		event.setStartDateTime(calendarStart);
		event.setEndDateTime(calendarEnd);
		event.setPriority(Priority.MEDIUM);
		
		assertEquals("id: abc, title: def, startDateTime: Tue Oct 20 10:33:25 SGT 2015, endDateTime: Wed Oct 21 11:34:26 SGT 2015, priority: MEDIUM, ", event.toString());
	}

}
