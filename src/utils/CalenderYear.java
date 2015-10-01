package utils;

import java.util.ArrayList;
import java.util.List;

import utils.CalenderMonth;
import utils.Event;

public class CalenderYear {
	
	private int year;
	private ArrayList<CalenderMonth> month;
	
	
	public CalenderYear(int year) {
		month=new ArrayList <CalenderMonth> ();
		this.year=year;
	}
	
	@SuppressWarnings("deprecation")
	public CalenderYear(Event event) {
		month=new ArrayList <CalenderMonth> ();
		this.year=event.getStartDateTime().getTime().getYear();
		addMonth(event);
	}
	
	@SuppressWarnings("deprecation")
	public void addMonth(Event event) {
		int index;
		
		if(!isMonthAvaliable(event.getStartDateTime().getTime().getMonth())){
			month.add(new CalenderMonth(event));
		}
		else{
			index=returnIndex(event.getStartDateTime().getTime().getMonth());
			month.get(index).addDate(event);
		}
	}
	
	public int getYear(){
		return year;
	}
	
	public List<Event> getTask(){
		int i;
		List<Event> events = new ArrayList<>();
		for(i=0;i<month.size();i++){
			events.addAll(month.get(i).getTask());
		}
		return events;
	}
	
	public Boolean isMonthAvaliable (int information) {
		
		int i, size = month.size();

		for (i = 0; i < size; i++) {
			if (month.get(i).getMonth()==information) {
				return true;
			}
		}
		return false;
	}
	
	//Return the index of the month in arraylist
	public int returnIndex (int monthInfo){
		int i, index=0;
		
		for(i=0;i<month.size();i++){
			if(month.get(i).getMonth()==monthInfo){
				index=i;
			}
		}
		return index;
	}

	public void clear() {
		month.clear();
	}
	
	public CalenderMonth getMonth(int numMonth){
		
		int index= returnIndex(numMonth);
		return month.get(index);
	}
}
