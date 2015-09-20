package utils;

import java.text.SimpleDateFormat;
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
