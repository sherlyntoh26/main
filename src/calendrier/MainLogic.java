package calendrier;

import java.util.ArrayList;
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
				String identifier = parsedCommand.getIdentifier();
				event = eventHandler.add(identifier, parsedCommand.getDetails());
			} else if (parsedCommand.getCommand() == Command.DELETE) {
				String identifier = parsedCommand.getIdentifier();
				event = eventHandler.remove(identifier, parsedCommand.getDetails());
			} else if (parsedCommand.getCommand() == Command.UPDATE) {
				String identifier = parsedCommand.getIdentifier();
				event = eventHandler.update(identifier, parsedCommand.getDetails());
			} else if (parsedCommand.getCommand() == Command.VIEW) {
				String identifier = parsedCommand.getIdentifier();
				event = eventHandler.view(identifier);
			}
			
			if(parsedCommand.getCommand() == Command.VIEW){
				eventList.add(event);
			}
			else{
				eventList = eventHandler.getAllEvents();		
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
