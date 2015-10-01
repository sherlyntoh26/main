package test;

import static org.junit.Assert.*;
import org.junit.Test;

import calendrier.EventHandler;
import utils.Event;

public class EventHandlerTest {

	@Test
	public void testAddEvent() {
		String eventId = "ADD1";
		Event newEvent = new Event();
		
		EventHandler handle = new EventHandler();
		newEvent = handle.add(eventId, newEvent);
		assertEquals(eventId, newEvent.getId());
	}

}
