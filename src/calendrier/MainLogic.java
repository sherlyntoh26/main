package calendrier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import stub.EventHandlerStub;
import stub.ParserStub;
import utils.Command;
import utils.Event;
import utils.ParsedCommand;

/**
 * For handling the main logic
 * 
 * @author yeehuipoh
 *
 */
public class MainLogic {
	private Parser parser = null;
	private EventHandler eventHandler = null;
	private UserInterface userInterface = null;

	public MainLogic() {
		super();
		parser = new ParserStub();
		eventHandler = new EventHandlerStub();
		userInterface = new UserInterface(this);
	}

	/**
	 * Execute command
	 * 
	 * @param command
	 *            Command string input from user
	 * @return List of events (to be shown to user)
	 */
	public List<Event> execute(String command) {
		List<Event> eventList = new ArrayList<>();
		Event event = null;
		ParsedCommand parsedCommand = parser.parse(command);

		if (parsedCommand != null) {
			if (parsedCommand.getCommand() == Command.ADD) {
				String identifier = parsedCommand.getIdentifier();
				event = eventHandler.add(identifier, parsedCommand.getEventDetails());
				eventList = eventHandler.getAllEvents();
			} else if (parsedCommand.getCommand() == Command.DELETE) {
				String identifier = parsedCommand.getIdentifier();
				event = eventHandler.remove(identifier, parsedCommand.getEventDetails());
				eventList = eventHandler.getAllEvents();
			} else if (parsedCommand.getCommand() == Command.UPDATE) {
				String identifier = parsedCommand.getIdentifier();
				event = eventHandler.update(identifier, parsedCommand.getEventDetails());
				eventList = eventHandler.getAllEvents();
			} else if (parsedCommand.getCommand() == Command.VIEW) {
				String identifier = parsedCommand.getIdentifier();
				event = eventHandler.view(identifier);
				if (parsedCommand.getIdentifier() != null) {
					eventList.add(event);
				} else {
					eventList = eventHandler.getAllEvents();
					Collections.sort(eventList, new Comparator<Event>() {
						public int compare(Event s1, Event s2) {
							// Write your logic here.
							return s2.getPriority().compareTo(s1.getPriority());
						}
					});
				}
			}
		}

		return eventList;
	}

	/**
	 * Get list of events
	 * 
	 * @return List of events
	 */
	public List<Event> getEvents() {
		return eventHandler.getAllEvents();
	}

	/**
	 * Parse command from input string
	 * 
	 * @param command
	 *            input string
	 * @return Command with details
	 */
	public ParsedCommand getParsedCommand(String command) {
		if (command == null) {
			return null;
		} else if (command.length() == 0) {
			return null;
		} else {
			return parser.parse(command);
		}
	}
	
	/**
	 * Notifies user about event starting soon
	 * @param event event that is starting soon
	 */
	public void notifyUser(Event event){
		userInterface.notifyUser(event);
	}
}
