package stub;

import calendrier.Parser;
import utils.Command;
import utils.Event;
import utils.ParsedCommand;
import utils.Priority;

public class ParserStub extends Parser{

	@Override
	public ParsedCommand parse(String command) {
		// TODO Auto-generated method stub
		ParsedCommand parsedCommand = null;
		if(command.equals("add added")){
			Event event = new Event();
			event.setTitle("added");
			//event.setId("added");
			event.setPriority(Priority.LOW);
			
			parsedCommand = new ParsedCommand();
			//parsedCommand.setIdentifier("added");
			parsedCommand.setCommand(Command.ADD);
			parsedCommand.setEventDetails(event);
		}
		else if(command.equals("delete deleted")){
			Event event = new Event();
			event.setTitle("deleted");
			//event.setId("deleted");
			event.setPriority(Priority.LOW);
			
			parsedCommand = new ParsedCommand();
			//parsedCommand.setIdentifier("deleted");
			parsedCommand.setCommand(Command.DELETE);
			parsedCommand.setEventDetails(event);
		}
		else if(command.equals("update updated")){
			Event event = new Event();
			event.setTitle("updated");
			//event.setId("updated");
			event.setPriority(Priority.HIGH);
			
			parsedCommand = new ParsedCommand();
			//parsedCommand.setIdentifier("updated");
			parsedCommand.setCommand(Command.UPDATE);
			parsedCommand.setEventDetails(event);
		}
		else if(command.equals("view viewDetails")){
			parsedCommand = new ParsedCommand();
			//parsedCommand.setIdentifier("viewDetails");
			parsedCommand.setCommand(Command.VIEW);
		}
		else if(command.equals("view")){
			parsedCommand = new ParsedCommand();
			parsedCommand.setCommand(Command.VIEW);
		}
		return parsedCommand;
	}
	
}
