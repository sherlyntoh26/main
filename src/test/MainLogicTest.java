package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import calendrier.MainLogic;
import utils.Event;
import utils.ParsedCommand;
import utils.Priority;

public class MainLogicTest {

	// @Test
	// public void test() {
	// fail("Not yet implemented");
	// }

	@Test
	public void parsedCommandShouldNotBeNull() {
		MainLogic mainLogic = new MainLogic();
		ParsedCommand parsedCommand = mainLogic.getParsedCommand("add added");
		assertFalse("parsedCommand should not be null", parsedCommand == null);
	}

	@Test
	public void commandShouldNotBeNull() {
		MainLogic mainLogic = new MainLogic();
		ParsedCommand parsedCommand = mainLogic.getParsedCommand(null);
		assertTrue("command should not be null", parsedCommand == null);
	}

	@Test
	public void commandShouldNotBeEmpty() {
		MainLogic mainLogic = new MainLogic();
		ParsedCommand parsedCommand = mainLogic.getParsedCommand("");
		assertTrue("command should not be empty", parsedCommand == null);
	}

	@Test
	public void executeShouldNotReturnNull() {
		MainLogic mainLogic = new MainLogic();
		List<Event> eventList = mainLogic.execute("add added");
		assertFalse("should not return null", eventList == null);
	}

	@Test
	public void executeEventListSizeShouldBeGreaterThanZero() {
		MainLogic mainLogic = new MainLogic();
		List<Event> eventList = mainLogic.execute("add added");
		assertTrue("should be > 0", eventList.size() > 0);
	}

	@Test
	public void executeEventListShouldHaveAdded() {
		MainLogic mainLogic = new MainLogic();
		List<Event> eventList = mainLogic.execute("add added");
		boolean haveAdded = false;
		for (Event event : eventList) {
			if (event.getTitle().equals("added")) {
				haveAdded = true;
				break;
			}
		}
		assertTrue("should not have \"added\" event", haveAdded);
	}

	@Test
	public void executeEventListShouldNotHaveDeleted() {
		MainLogic mainLogic = new MainLogic();
		List<Event> eventList = mainLogic.execute("delete deleted");
		boolean haveDeleted = false;
		for (Event event : eventList) {
			if (event.getTitle().equals("deleted")) {
				haveDeleted = true;
				break;
			}
		}
		assertFalse("should not have \"deleted\" event", haveDeleted);
	}

	@Test
	public void executeEventListShouldBeUpdated() {
		MainLogic mainLogic = new MainLogic();
		List<Event> eventList = mainLogic.execute("update updated");
		boolean isHigh = false;
		for (Event event : eventList) {
			if (event.getTitle().equals("updated")) {
				if (event.getPriority() == Priority.HIGH) {
					isHigh = true;
				}
				break;
			}
		}
		assertTrue("\"updated\" should have \"HIGH\" priority", isHigh);
	}
	
	@Test
	public void executeEventListShouldHaveOnlyOneEvent() {
		MainLogic mainLogic = new MainLogic();
		List<Event> eventList = mainLogic.execute("view viewDetails");
		boolean isViewEvent = false;
		assertTrue("should only have 1 event", eventList.size() == 1);
		
		for (Event event : eventList) {
			if (event.getTitle().equals("viewDetails")) {
				isViewEvent = true;
				break;
			}
		}
		assertTrue("should only have \"viewDetails\"", isViewEvent);
	}
	
	@Test
	public void executeEventListShouldHaveAllEvent() {
		MainLogic mainLogic = new MainLogic();
		List<Event> eventList = mainLogic.execute("view");
		
		assertTrue("should have all event", eventList.size() > 1);
	}

}
