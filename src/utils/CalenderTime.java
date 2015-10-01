package utils;
import utils.Event;

public class CalenderTime{
	private Boolean avalibility;
	private Event event;
	
	public CalenderTime(){}
	public CalenderTime(Boolean input){
		this.avalibility=input;
	}
	public void setTime(Boolean input, Event event){
		setTimeSlot(input);
		setTask(event);
	}
	public void setTimeSlot(Boolean input){
		this.avalibility = input;
	}
	public void setTask(Event event){
		this.event = event;
	}
	public String getTask(){
		return event.getTitle();
	}
	public Event getEvent(){
		return event;
	}
	public String getTaskID(){
		return event.getId();
	}
	public Boolean getTimeSlot(){
		return avalibility;
	}
	public void clear(){
		avalibility=false;
		event=null;
	}
}