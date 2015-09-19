package utils;

import java.util.Calendar;

public class Event {
	private String id = null;
	private String title = null;
	private Calendar endDateTime = null; // also for deadline
	private Calendar startDateTime = null;
	private Priority priority = Priority.VERY_LOW;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	
	
}
