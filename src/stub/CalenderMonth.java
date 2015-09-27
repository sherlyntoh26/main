package stub;

import java.util.ArrayList;

import stub.CalenderDate;

public class CalenderMonth {

	private String month=""; 
	private ArrayList<CalenderDate> date;
	
	public CalenderMonth() {
		date=new ArrayList <CalenderDate> ();
	}
	
	public CalenderMonth(String month,int day, int timeStart, int timeEnd, String task) {
		date=new ArrayList <CalenderDate> ();
		this.month=month;
		addDate(day, timeStart, timeEnd, task);
	}
	
	public void addDate(int day, int timeStart, int timeEnd, String task) {
		if(!isDateAvaliable(day)){
			date.add(new CalenderDate(day, timeStart, timeEnd ,task));
		}
		else{
			int Index= returnIndex(day);
			date.get(Index).addTask(timeStart, timeEnd, task);
		}
	}
	
	//get all the information for a specific date.
	public CalenderDate getDate(int numDate){
		int index= returnIndex(numDate);
		return date.get(index);
	}
	
	public String getMonth(){
		return month;
	}
	
	public void clear() {
		date.clear();
	}
	
	//return number of day that contain task
	public int getSize(){
		return date.size();
	}
	
	public ArrayList<String> toArrayList(){
		ArrayList<String> result= new ArrayList<String>();
		String data="";
		
		int i,j;
		
		for(i=0; i<date.size(); i++){
			for(j=0;j<date.get(i).getTimeSlot();j++){
				data = data.concat( month + " " + date.get(i).getDay() + " " + date.get(i).getTimeStart(j+1) + " " + date.get(i).getTimeEnd(j+1) + " " + date.get(i).getTask(j+1));
				result.add(data);
				data= "";
			}
		}
		return result;
	}
	public String toString(){
		
		String data="";
		
		int i,j;
		
		for(i=0; i<date.size(); i++){
			for(j=0;j<date.get(i).getTimeSlot();j++){
				data = data.concat( month + " " + date.get(i).getDay() + " " + date.get(i).getTimeStart(j+1) + " " + date.get(i).getTimeEnd(j+1) + " " + date.get(i).getTask(j+1) + "\n");
			}
		}
		return data;
	}
	
	public Boolean isDateAvaliable (int dateInfo) {
		
		int i ;

		for (i = 0; i <date.size(); i++) {
			if (date.get(i).getDay()==dateInfo) {
				return true;
			}
		}
		return false;
	}
	
	//find the index of the date in the arraylist.
	public int returnIndex (int dateInfo){
		int i, index=0;
		
		for(i=0;i<date.size();i++){
			if(date.get(i).getDay()== dateInfo){
				index=i;
			}
		}
		return index;
	}
}