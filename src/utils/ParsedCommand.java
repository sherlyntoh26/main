package utils;

public class ParsedCommand {
	private Command command = null;
	private Event eventDetails = null;
	private int identifier = 1;		// Id of first command is 1, subsequent tasks' Id will increase by 1
	
	public ParsedCommand() {
		identifier++;
	}

	public int getIdentifier() {
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
}
