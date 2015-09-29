package test;

import static org.junit.Assert.*;
import org.junit.Test;

import calendrier.EventHandler;
import utils.Event;

public class EventHandlerTest {

	@Test
	public void test() {
		String eventTitle = "Test";
		String identifer = "";
		Event newEvent = new Event();
		
		EventHandler handle = new EventHandler();
		newEvent = handle.add("Test", newEvent);
		assertEquals(eventTitle, newEvent.getTitle());
	}

}
