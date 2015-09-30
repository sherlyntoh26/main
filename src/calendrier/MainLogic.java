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

public class MainLogic {
	private Parser parser = null;
	private EventHandler eventHandler = null;

	public MainLogic() {
		super();
		parser = new ParserStub();
		eventHandler = new EventHandlerStub();
	}

	public List<Event> execute(String command) {
		List<Event> eventList = new ArrayList<>();
		Event event = null;
		ParsedCommand parsedCommand = parser.parse(command);

		if (parsedCommand != null) {
			if (parsedCommand.getCommand() == Command.ADD) {
				int identifier = parsedCommand.getIdentifier();
				event = eventHandler.add(identifier, parsedCommand.getEventDetails());
				eventList = eventHandler.getAllEvents();
			} else if (parsedCommand.getCommand() == Command.DELETE) {
				int identifier = parsedCommand.getIdentifier();
				event = eventHandler.remove(identifier, parsedCommand.getEventDetails());
				eventList = eventHandler.getAllEvents();
			} else if (parsedCommand.getCommand() == Command.UPDATE) {
				int identifier = parsedCommand.getIdentifier();
				event = eventHandler.update(identifier, parsedCommand.getEventDetails());
				eventList = eventHandler.getAllEvents();
			} else if (parsedCommand.getCommand() == Command.VIEW) {
				int identifier = parsedCommand.getIdentifier();
				event = eventHandler.view(identifier);
				if (parsedCommand.getIdentifier() != 0) {
					eventList.add(event);
				}
				else{
					eventList = eventHandler.getAllEvents();
					Collections.sort(eventList, new Comparator<Event>(){
	                     public int compare(Event s1,Event s2){
	                           // Write your logic here.
	                    	 return s2.getPriority().compareTo(s1.getPriority());
	                     }});
				}
			}
		}

		return eventList;
	}

	public List<Event> getEvents() {
		return eventHandler.getAllEvents();
	}

	public ParsedCommand getParsedCommand(String command) {
		if (command == null) {
			return null;
		} else if (command.length() == 0) {
			return null;
		} else {
			return parser.parse(command);
		}
	}
}
