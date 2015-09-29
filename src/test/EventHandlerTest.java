package test;

import static org.junit.Assert.*;
import org.junit.Test;

import calendrier.EventHandler;
import utils.Event;

public class EventHandlerTest {

	@Test
	public void test() {
		String eventTitle = "Add";
		String identifer = "";
		Event newEvent = new Event();
		
		EventHandler handle = new EventHandler();
		newEvent = handle.add("Add", newEvent);
		assertEquals(eventTitle, newEvent.getTitle());
	}

}
