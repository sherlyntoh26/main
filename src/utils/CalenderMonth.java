package utils;

import java.util.ArrayList;
import java.util.List;

import utils.Event;

import utils.CalenderDate;

public class CalenderMonth {

	private int month; 
	private ArrayList<CalenderDate> date;
	
	public CalenderMonth() {
		date=new ArrayList <CalenderDate> ();
	}
	
	@SuppressWarnings("deprecation")
	public CalenderMonth(Event event) {
		date=new ArrayList <CalenderDate> ();
		this.month=event.getStartDateTime().getTime().getMonth();
		addDate(event);
	}
	
	@SuppressWarnings("deprecation")
	public void addDate(Event event) {
		if(!isDateAvaliable(event)){
			date.add(new CalenderDate(event));
		}
		else{
			int Index= returnIndex(event.getStartDateTime().getTime().getDate());
			date.get(Index).addTask(event);
		}
	}
	
	//get all the information for a specific date.
	public CalenderDate getDate(int numDate){
		int index= returnIndex(numDate);
		return date.get(index);
	}
	
	public int getMonth(){
		return month;
	}
	
	public void clear() {
		date.clear();
	}
	
	//return number of day that contain task
	public int getSize(){
		return date.size();
	}
	
	public List<Event> getTask(){
		int i;
		List<Event> events = new ArrayList<>();
		for(i=0;i<date.size();i++){
			events.addAll(date.get(i).getTask());
		}
		return events;
	}

	@SuppressWarnings("deprecation")
	public Boolean isDateAvaliable (Event event) {
		
		int i ;

		for (i = 0; i <date.size(); i++) {
			if (date.get(i).getDate()==event.getStartDateTime().getTime().getDate()) {
				return true;
			}
		}
		return false;
	}
	public Boolean isDateAvaliable (int dateInfo) {
		
		int i ;

		for (i = 0; i <date.size(); i++) {
			if (date.get(i).getDate()==dateInfo) {
				return true;
			}
		}
		return false;
	}
	
	//find the index of the date in the arraylist.
	public int returnIndex (int dateInfo){
		int i, index=0;
		
		for(i=0;i<date.size();i++){
			if(date.get(i).getDate()== dateInfo){
				index=i;
			}
		}
		return index;
	}
}