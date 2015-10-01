package utils;

public class ParsedCommand {
	private Command command = null;
	private Event eventDetails = null;
	private String identifier = null;		// Id of first command is 1, subsequent tasks' Id will increase by 1
	
	public ParsedCommand() {
//		identifier++;
	}

	public String getIdentifier() {
		return identifier;
	}
	

	public Event getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(Event eventDetails) {
		this.eventDetails = eventDetails;
	}
	
	public void setCommand(Command command){
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
}
