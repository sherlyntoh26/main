package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Event {
	private String id = null;	// Id of first task is 1, subsequent tasks' Id will increase by 1
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

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public int getUpdateTaskNo() {
		return updateTaskNo;
	}

	public void setUpdateTaskNo(int updateTaskNo) {
		this.updateTaskNo = updateTaskNo;
	}

	public java.sql.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.sql.Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatePriority() {
		return updatePriority;
	}

	public void setUpdatePriority(String updatePriority) {
		this.updatePriority = updatePriority;
	}

	public String getUpdateLocation() {
		return updateLocation;
	}

	public void setUpdateLocation(String updateLocation) {
		this.updateLocation = updateLocation;
	}

	public String getUpdateRecurring() {
		return updateRecurring;
	}

	public void setUpdateRecurring(String updateRecurring) {
		this.updateRecurring = updateRecurring;
	}

	public java.sql.Date getUpdateReminder() {
		return updateReminder;
	}

	public void setUpdateReminder(java.sql.Date updateReminder) {
		this.updateReminder = updateReminder;
	}

	public String getAddTaskDescription() {
		return addTaskDescription;
	}

	public void setAddTaskDescription(String addTaskDescription) {
		this.addTaskDescription = addTaskDescription;
	}

	public java.sql.Date getAddDate() {
		return addDate;
	}

	public void setAddDate(java.sql.Date addDate) {
		this.addDate = addDate;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddPriority() {
		return addPriority;
	}

	public void setAddPriority(String addPriority) {
		this.addPriority = addPriority;
	}

	public String getAddLocation() {
		return addLocation;
	}

	public void setAddLocation(String addLocation) {
		this.addLocation = addLocation;
	}

	public String getAddNotes() {
		return addNotes;
	}

	public void setAddNotes(String addNotes) {
		this.addNotes = addNotes;
	}

	public String getAddRecurring() {
		return addRecurring;
	}

	public void setAddRecurring(String addRecurring) {
		this.addRecurring = addRecurring;
	}

	public java.sql.Date getAddReminder() {
		return addReminder;
	}

	public void setAddReminder(java.sql.Date addReminder) {
		this.addReminder = addReminder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

	
}
