package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Event {
	//private int id = 1;	// Id of first task is 1, subsequent tasks' Id will increase by 1
	private String title = null;
	private Calendar endDateTime = null; // also for deadline
	private Calendar startDateTime = null;
	private Priority priority = Priority.VERY_LOW;
	
	// for filter
	private String filterValue = null;
	
	// for update
	private int updateTaskNo;
	private java.sql.Date updateDate;
	private String updateTime;
	private String updatePriority;
	private String updateLocation;
	private String updateRecurring;
	private java.sql.Date updateReminder;
	
	// for add
	private String addTaskDescription;
	private java.sql.Date addDate;
	private String addTime;
	private String addPriority;
	private String addLocation;
	private String addNotes;
	private String addRecurring;
	private java.sql.Date addReminder;
	
	/*
	public Event() {
		id++;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}*/

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Calendar endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Calendar getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Calendar startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		String objectString = "";
		
		objectString = String.format("%sid: %s, ", objectString, this.id);
		objectString = String.format("%stitle: %s, ", objectString, this.title);
		objectString = String.format("%sstartDateTime: %s, ", objectString, this.startDateTime.getTime());
		objectString = String.format("%sendDateTime: %s, ", objectString, this.endDateTime.getTime());
		objectString = String.format("%spriority: %s, ", objectString, this.priority.name());
		
		return objectString;
	}

}
