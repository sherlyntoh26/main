package stub;

import java.util.ArrayList;

import stub.CalenderTime;

public class CalenderDate {
	
	private int date, numOfSlot=0;
	private ArrayList<CalenderTime> timeSlot;

	public CalenderDate() {}
	
	public CalenderDate(int date, int timeStart, int timeEnd, String task) {
		timeSlot = new ArrayList<CalenderTime>();
		defineTimeSlot();
		this.date=date;
		addTask(timeStart, timeEnd, task);
	}
	
	private void defineTimeSlot() {
		int i;
		
		for(i=0; i<48; i++){
			timeSlot.add(new CalenderTime(false));
		}
	}
	public void deleteTask(int timeStart, int timeEnd){
		int slot, range, i;
		
		numOfSlot--;
		
		if(timeStart%100==30){
			slot= (timeStart+20)*2/100;
		}
		else{
			slot= timeStart/100*2;
		}
		if(timeEnd%100==30){
			range= (timeEnd+20)*2/100;
		}
		else{
			range= timeEnd*2/100;
		}
		
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
	public int getDay(){
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
	public String toString(){
		String data="";
		int i;
		
		for(i=0; i<numOfSlot; i++){
			data = data.concat(getTimeStart(i+1) + " " + getTimeEnd(i+1) + " " + getTask(i+1) + "\n");
		}
		return data;
	}
	public void addTask(int timeStart, int timeEnd, String information){
		
		int slot, range, i;
		if(checkTimeAvaliable(timeStart,timeEnd)){
			numOfSlot++;
			slot=convertIndex(timeStart);
			range=convertIndex(timeEnd);
			
			for(i=slot; i<range; i++){
				timeSlot.get(i).setTime(true, information);
			}
		}
	}
	//check whether the time slot is taken
	public Boolean checkTimeAvaliable(int startTime, int endTime){
		int start, end, i;
		start = convertIndex(startTime);
		end = convertIndex(endTime);
		for(i=start;i<end;i++){
			if(timeSlot.get(i).getTimeSlot()==true)
				return false;
		}
		return true;
	}
	//check whether the timeslot is taken
	public Boolean checkTimeSlot(int startTime, int endTime){
		int start, end, i;
		start = convertIndex(startTime);
		end = convertIndex(endTime);
		for(i=start;i<end;i++){
			if(timeSlot.get(i).getTimeSlot()==false)
				return false;
		}
		return true;
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
	//update the task information
	public void updateTask(int timeStart, int timeEnd, String newTask){
		int i;
		
		for(i=convertIndex(timeStart);i<convertIndex(timeEnd);i++){
			timeSlot.get(i).setTask(newTask);
		}
	}
	//update the time for a specific task
	public void updateTime(int timeStart, int timeEnd){
		int start, end, i, oriStart=0, oriEnd=0, num;
		String task;
		
		start = convertIndex(timeStart);
		end = convertIndex(timeEnd);
		
		if(!checkTimeAvaliable(timeStart, timeEnd)){
			for(i=start;i<end;i++){
				if(timeSlot.get(i).getTimeSlot()==true){
					oriStart=i;
					while(timeSlot.get(i).getTimeSlot()==true){
						i++;
					}
					oriEnd=i;
					break;
				}
			}
			num=checkIndexOfSlot(oriStart);
			task=getTask(num);
			deleteTask(convertTime(oriStart), convertTime(oriEnd));
			addTask(timeStart,timeEnd,task);
		}
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