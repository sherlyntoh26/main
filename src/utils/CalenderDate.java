package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import utils.CalenderTime;
import utils.Event;

public class CalenderDate {
	
	private int date, numOfSlot=0;
	private ArrayList<CalenderTime> timeSlot;
	
	@SuppressWarnings("deprecation")
	public CalenderDate(Event event) {
		timeSlot = new ArrayList<CalenderTime>();
		defineTimeSlot();
		this.date=event.getStartDateTime().getTime().getDate();
		addTask(event);
	}
	
	private void defineTimeSlot() {
		int i;
		
		for(i=0; i<48; i++){
			timeSlot.add(new CalenderTime(false));
		}
	}
	@SuppressWarnings("deprecation")
	public int convertTime(Calendar input){
		int hour, minutes;
		
		hour=input.getTime().getHours();
		minutes = input.getTime().getMinutes();
		
		if(minutes<30){
			hour=hour*100;
		}
		else{
			hour=hour*100+30;
		}
		return hour;
	}
	
	public void deleteTask(Event event){
		int slot, range, i;
		
		numOfSlot--;
		
		slot=convertIndex(convertTime(event.getStartDateTime()));
		range=convertIndex(convertTime(event.getEndDateTime()));

		for(i=slot; i<range; i++){
			timeSlot.get(i).clear();
		}
	}
	
	//convert index of arrayList back to time
	private int convertTime(int num){
		if(num%2!=0){
			return (num*100/2)-20;
		}
		else{
			return num*100/2;
		}
	}
	
	//convert time to the index in arraylist
	private int convertIndex(int time){
		if(time%100==30){
			return (time+20)*2/100;
		}
		else{
			return time/100*2;
		}
	}
	public int getDate(){
		return date;
	}
	
	//return number of task in a day.
	public int getTimeSlot(){
		return numOfSlot;
	}
	public void clear() {
		numOfSlot=0;
		timeSlot.clear();
	}

	@SuppressWarnings("deprecation")
	public void addTask(Event event){
		
		int slot, range, i;
		
		if(checkTimeAvaliable(event.getStartDateTime().getTime().getHours(), event.getEndDateTime().getTime().getHours())){
			numOfSlot++;
			slot=convertIndex(convertTime(event.getStartDateTime()));
			range=convertIndex(convertTime(event.getEndDateTime()));
			for(i=slot; i<range; i++){
				timeSlot.get(i).setTime(true, event);
			}
		}
	}
	
	//check whether the time slot is taken
	public Boolean checkTimeAvaliable(int startTime, int endTime){
		int start, end, i;
		start = convertIndex(startTime);
		end = convertIndex(endTime);
		for(i=start;i<end;i++){
			if(timeSlot.get(i).getTimeSlot()==true){
				return false;
			}
		}
		return true;
	}
	//check whether the timeslot is taken
	public Boolean checkTimeSlot(Event event){
		int start, end, i;
		int timeStart=convertTime(event.getStartDateTime());
		int timeEnd=convertTime(event.getEndDateTime());
		
		
		start = convertIndex(timeStart);
		end = convertIndex(timeEnd);
		for(i=start;i<end;i++){
			if(timeSlot.get(i).getTimeSlot()==false)
				return false;
		}
		return true;
	}
	public List<Event> getTask(){
		int i;
		List<Event> events = new ArrayList<>();
		
		for(i=0;i<timeSlot.size();i++){
			if(timeSlot.get(i).getTimeSlot()==true&&!events.contains(timeSlot.get(i).getEvent())){
				events.add(timeSlot.get(i).getEvent());
			}
		}
		return events;
	}
	public String getTask(int num){
		int i, flag=0;
		String Task="";
		
		for(i=0;i<timeSlot.size();i++){
			if(timeSlot.get(i).getTimeSlot()==true){
				flag++;
				if(flag==num)
				{
					Task=timeSlot.get(i).getTask();
					break;
				}
				while(timeSlot.get(i).getTimeSlot()==true){
					i++;
				}
			}
		}
		return Task;
	}
	
	//check the index of the specific time in arraylist
	public int checkIndexOfSlot(int timeStart){
		int i, num=0, index=convertIndex(timeStart);
		
		for(i=0;i<timeSlot.size();i++){
			if(timeSlot.get(i).getTimeSlot()==true){
				num++;
				if(i==index)
				{
					break;
				}
				while(timeSlot.get(i).getTimeSlot()==true){
					i++;
				}
			}
		}
		return num;
	}
	
	public int getTimeStart(int num){
		int i, timeStart=0, flag=0;
		for(i=0;i<timeSlot.size();i++){
			if(timeSlot.get(i).getTimeSlot()==true){
				flag++;
				if(flag==num){
					timeStart=convertTime(i);
					break;
				}
				while(timeSlot.get(i).getTimeSlot()==true){
					i++;
				}
			}
		}
		return timeStart;
	}
	public int getTimeEnd(int num){
		int i, timeEnd=0, flag=0, flag1=0;
		
		for(i=0;i<timeSlot.size();i++){
			if(timeSlot.get(i).getTimeSlot()==false&&flag==1){
				flag1++;
				if(flag1==num){
					timeEnd=convertTime(i);
					break;
				}
				else
					flag=0;
			}
			if(timeSlot.get(i).getTimeSlot()==true){
				flag=1;
			}
		}
		return timeEnd;
	}
}