package stub;

import java.util.ArrayList;

import stub.CalenderMonth;

public class CalenderYear {
	
	private int year;
	private ArrayList<CalenderMonth> month;
	
	
	public CalenderYear() {
		month=new ArrayList <CalenderMonth> ();
	}
	
	public CalenderYear(int year) {
		month=new ArrayList <CalenderMonth> ();
		this.year=year;
	}
	
	public CalenderYear(int year, String mon, int day, int timeStart, int timeEnd, String task) {
		this.year=year;
		addMonth(mon,day, timeStart, timeEnd, task);	
	}
	
	public void addMonth(ArrayList<CalenderMonth> data){
		month=data;
	}
	
	public void setYear(int num){
		year=num;
	}
	
	public void addMonth(String mon, int day, int timeStart, int timeEnd, String task) {
		int index;
		
		if(!isMonthAvaliable(mon)){
			month.add(new CalenderMonth(mon,day, timeStart, timeEnd, task));
		}
		else{
			index=returnIndex(mon);
			month.get(index).addDate(day, timeStart, timeEnd, task);
		}
	}
	
	public CalenderMonth getMonth(String numMonth){
		
		int index= returnIndex(numMonth);
		return month.get(index);
	}
	
	public int getYear(){
		return year;
	}
	
	public String toString(){
		
		ArrayList<String> resultMonth = new ArrayList<String>();
		String data="";
		
		int i,j;
		
		for(i=0; i<month.size(); i++){
			resultMonth = month.get(i).toArrayList();
			for(j=0;j<resultMonth.size();j++){
				data = data.concat(year + " " + resultMonth.get(j) +"\n");
			}
		}
		return data;
	}
	
	public ArrayList<String> toArrayList(){
		
		ArrayList<String> resultMonth = new ArrayList<String>();
		ArrayList<String> resultYear = new ArrayList<String>();
		String data="";
		
		int i,j;
		
		for(i=0; i<month.size(); i++){
			resultMonth = month.get(i).toArrayList();
			for(j=0;j<resultMonth.size();j++){
				data = data.concat(year + " " + resultMonth.get(j));
				resultYear.add(data);
				data="";
			}
		}
		return resultYear;
	}

	public Boolean isMonthAvaliable (String information) {
		
		int i, size = month.size();

		for (i = 0; i < size; i++) {
			if (month.get(i).getMonth().equals(information)) {
				return true;
			}
		}
		return false;
	}
	
	//Return the index of the month in arraylist
	public int returnIndex (String monthInfo){
		int i, index=0;
		
		for(i=0;i<month.size();i++){
			if(month.get(i).getMonth().equals(monthInfo)){
				index=i;
			}
		}
		return index;
	}

	public void clear() {
		month.clear();
	}
}
