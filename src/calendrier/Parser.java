package calendrier;

import utils.Command;
import utils.Event;

import java.util.Date;
import java.util.StringTokenizer;
import utils.ParsedCommand;

public class Parser {
	/*
	 * Only view, delete, filter, storage has a title. Other commands(add, update) are broken down into
	 * individual tokens.
	 * 
	 */
	
	public ParsedCommand parse(String userInput){
		ParsedCommand pc = new ParsedCommand();
		
		// If line contains only one word, e.g. command is undo, undelete
		if (userInput.indexOf(" ") == -1) {
			if (userInput.equals("undo")) {
				pc.setCommand(Command.UNDO);
			} else if (userInput.equals("undelete")) {
				pc.setCommand(Command.UNDELETE);
			}
		} else {
			Event event = new Event();
			
			StringTokenizer stringTokenizer = new StringTokenizer(userInput);
			String command = stringTokenizer.nextToken();
			String inputAfterCommand = userInput.substring(userInput.indexOf(" ")+1);
					
			if (command.equals("view")) {
				pc.setCommand(Command.VIEW);
				event.setTitle(inputAfterCommand);
			} else if (command.equals("delete")) {
				pc.setCommand(Command.DELETE);
				event.setTitle(inputAfterCommand);
			} else if (command.equals("filter")) {
				pc.setCommand(Command.FILTER);
				String filterParameter = stringTokenizer.nextToken();
				String filterValue = stringTokenizer.nextToken();
				event.setTitle(filterValue);
				
				/*
				// e.g. filter priority low
				if (filterParameter.equals("priority")) {
					filterValue = stringTokenizer.nextToken();
				}
				
				// e.g. filter date yyyy/mm/dd
				else if (filterParameter.equals("date")) {
					filterValue = stringTokenizer.nextToken();
				}
				*/
			} else if (command.equals("update")) {
				// update 23, date 2015/11/30, time 12.34, priority very high,
				// location LT 1, recurring no, reminder 2015/11/28
				String[] updateInfo = inputAfterCommand.split(",");
				event.setUpdateTaskNo(Integer.parseInt(updateInfo[0]));
				
				updateInfo[1] = updateInfo[1].trim();
				String date = updateInfo[1].substring(updateInfo[1].indexOf(" ")+1);
				event.setUpdateDate(new java.sql.Date(new Date(date).getTime()));	// yyyy-mm-dd
				
				updateInfo[2] = updateInfo[2].trim();
				String time = updateInfo[2].substring(updateInfo[2].indexOf(" ")+1);
				event.setUpdateTime(time);
				
				updateInfo[3] = updateInfo[3].trim();
				String priority = updateInfo[3].substring(updateInfo[3].indexOf(" ")+1);
				event.setUpdatePriority(priority);
				
				updateInfo[4] = updateInfo[4].trim();
				String location = updateInfo[4].substring(updateInfo[4].indexOf(" ")+1);
				event.setUpdateLocation(location);
				
				updateInfo[5] = updateInfo[5].trim();
				String recurring = updateInfo[5].substring(updateInfo[5].indexOf(" ")+1);
				event.setUpdateRecurring(recurring);
				
				updateInfo[6] = updateInfo[6].trim();
				String reminder = updateInfo[6].substring(updateInfo[6].indexOf(" ")+1);
				event.setUpdateReminder(new java.sql.Date(new Date(reminder).getTime()));	// yyyy-mm-dd
				
				pc.setCommand(Command.UPDATE);
			} else if (command.equals("save in")) {
				// save in desktop
				pc.setCommand(Command.STORAGE_LOCATION);
				event.setTitle(inputAfterCommand);
			} else if (command.equals("add")) {
				// e.g. add eat sleep drink repeat, date 2015/12/31, time 13.37, priority very low
				// location my home, notes must do, recurring no, reminder 2015/12/30
				pc.setCommand(Command.ADD);
				
				String[] updateInfo = inputAfterCommand.split(",");
				event.setAddTaskDescription(updateInfo[0]);
				
				updateInfo[1] = updateInfo[1].trim();
				String addDate = updateInfo[1].substring(updateInfo[1].indexOf(" ")+1);
				event.setAddDate(new java.sql.Date(new Date(addDate).getTime()));	// yyyy-mm-dd
				
				updateInfo[2] = updateInfo[2].trim();
				String addTime = updateInfo[2].substring(updateInfo[2].indexOf(" ")+1);
				event.setAddTime(addTime);
				
				updateInfo[3] = updateInfo[3].trim();
				String addPriority = updateInfo[3].substring(updateInfo[3].indexOf(" ")+1);
				event.setAddPriority(addPriority);
				
				updateInfo[4] = updateInfo[4].trim();
				String addLocation = updateInfo[4].substring(updateInfo[4].indexOf(" ")+1);
				event.setAddLocation(addLocation);
				
				updateInfo[5] = updateInfo[5].trim();
				String addNotes = updateInfo[5].substring(updateInfo[5].indexOf(" ")+1);
				event.setAddNotes(addNotes);
				
				updateInfo[6] = updateInfo[6].trim();
				String addRecurring = updateInfo[6].substring(updateInfo[6].indexOf(" ")+1);
				event.setAddRecurring(addRecurring);
				
				updateInfo[7] = updateInfo[7].trim();
				String addReminder = updateInfo[7].substring(updateInfo[7].indexOf(" ")+1);
				event.setAddReminder(new java.sql.Date(new Date(addReminder).getTime()));	// yyyy-mm-dd
			}
			
			//event.setTitle(inputAfterCommand);
			pc.setEventDetails(event);
		}
		
		return pc;
	}
}
