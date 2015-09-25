package utils;

public class ParsedCommand {
	private Command command = null;
	private Event eventDetails = null;
	private String identifier = null;

	public Command getCommand() {
		return command;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Event getDetails() {
		return eventDetails;
	}
	
	public String getPath(){
		return identifier;
	}

	public void setPath(String path) {
		this.identifier = path;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setEventDetails(Event eventDetails) {
		this.eventDetails = eventDetails;
	}
	
	public void setCommand(Command command){
		this.command = command;
	}
}
