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
		ParsedCommand parsedCommand = parser.parse(command);

		if (parsedCommand != null) {
			if (parsedCommand.getCommand() == Command.ADD) {
				String identifier = parsedCommand.getIdentifier();
				Event event = eventHandler.add(identifier, parsedCommand.getDetails());
			} else if (parsedCommand.getCommand() == Command.DELETE) {
				String identifier = parsedCommand.getIdentifier();
				Event event = eventHandler.remove(identifier, parsedCommand.getDetails());
			} else if (parsedCommand.getCommand() == Command.UPDATE) {
				String identifier = parsedCommand.getIdentifier();
				Event event = eventHandler.update(identifier, parsedCommand.getDetails());
			}
		}
		eventList = eventHandler.getAllEvents();

		return eventList;
	}

	public List<Event> getEvents() {
		return null;
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
