package calendrier;
//import java.util.Scanner;
import java.util.ArrayList;
//import java.util.Collections;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.IOException;

class CalenderYear {
	
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

class CalenderMonth {

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

class CalenderDate {
	
	private int date, numOfSlot=0;
	private ArrayList<CalenderTime> timeSlot;

	public CalenderDate() {
	}
	
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

class CalenderTime{
	private Boolean avalibility;
	private String task;
	
	public CalenderTime(){}
	public CalenderTime(Boolean input){
		this.avalibility=input;
	}
	public void setTime(Boolean input, String info){
		setTimeSlot(input);
		setTask(info);
	}
	public void setTimeSlot(Boolean input){
		this.avalibility = input;
	}
	public void setTask(String input){
		this.task = input;
	}
	public String getTask(){
		return task;
	}
	public Boolean getTimeSlot(){
		return avalibility;
	}
	public void clear(){
		avalibility=false;
		task="";
	}
}

public class ReminderManager {
	private static String fileName;
	private static ArrayList<String> inputData;
	private static String line;
	private static ArrayList<CalenderYear> year;
	private static ArrayList<String> backup;
	//static CalenderYear name;
	
	public ReminderManager(){
		year= new ArrayList<CalenderYear>();
		backup= new ArrayList<String>();
	}
	
	public void addTask(int numYear, String numMonth, int numDate, int timeStart, int timeEnd, String task){
		int index;
		
		updateStatus();
		if(!isYearAvaliable(numYear)){
			year.add(new CalenderYear(numYear));
		}
		index= returnIndex(numYear);
		addMonth(index, numMonth, numDate, timeStart, timeEnd, task);
	}
	public void deleteTask(int numYear, String numMonth, int numDate,int timeStart, int timeEnd){
		int index=returnIndex(numYear);
		updateStatus();
		year.get(index).getMonth(numMonth).getDate(numDate).deleteTask(timeStart, timeEnd);
	}
	public void deleteDayTask(int numYear, String numMonth, int numDate){
		int index=returnIndex(numYear);
		updateStatus();
		year.get(index).getMonth(numMonth).getDate(numDate).clear();
	}
	public void deleteMonthTask(int numYear, String numMonth){
		int index=returnIndex(numYear);
		updateStatus();
		year.get(index).getMonth(numMonth).clear();
	}
	public void deleteYearTask(int numYear){
		int index=returnIndex(numYear);
		updateStatus();
		year.get(index).clear();
	}
	public void deleteAllTask(){
		year.clear();
	}
	public void editTask(int numYear, String numMonth, int numDate,int timeStart, int timeEnd){
		int index=returnIndex(numYear);
		
		updateStatus();
		if(!year.get(index).getMonth(numMonth).getDate(numDate).checkTimeSlot(timeStart, timeEnd)){
			year.get(index).getMonth(numMonth).getDate(numDate).updateTime(timeStart,timeEnd);
		}
	}
	public void editTask(int numYear, String numMonth, int numDate,int timeStart, int timeEnd, String task){
		int index=returnIndex(numYear);
		
		updateStatus();
		year.get(index).getMonth(numMonth).getDate(numDate).updateTask(timeStart, timeEnd, task);
	}
	public static String viewTask(){
		int i;
		String data="";
		
		for(i=0;i<year.size();i++){
			data=data.concat(year.get(i).toString());
		}
		return data;
	}
	public static String viewYearTask(int numYear){
		int index=returnIndex(numYear);
		String data="";
		
		if(year.size()>0){
			data=year.get(index).toString();
		}
		
		return data;
	}
	public static String viewMonthTask(int numYear, String numMonth){
		int index=returnIndex(numYear);
		String data="";
		
		if(year.size()>0){
			data =year.get(index).getMonth(numMonth).toString();
		}
		return data;
	}
	public static String viewDayTask(int numYear, String numMonth, int numDate){
		int index=returnIndex(numYear);
		String data="";
		
		if(year.size()>0){
			data = year.get(index).getMonth(numMonth).getDate(numDate).toString();
		}
		return data;
	}
	public static void addMonth(int index, String month, int day, int timeStart, int timeEnd, String task){
		year.get(index).addMonth(month, day, timeStart, timeEnd, task);
	}
	
	//update the current status that prepared for undo.
	public void updateStatus(){
		int i,j=0;
		ArrayList<String> data= new ArrayList<String>();
		backup.clear();
		for(i=0;i<year.size();i++){
			data=year.get(i).toArrayList();
			while (j < data.size()) {
				backup.add(data.get(j));
				j++;
			}
		}
	}
	
	public void undo(){
		year.clear();
		processInputFromFile(backup);
	}

	public static Boolean isYearAvaliable (int info) {
		
		int i, size = year.size();

		for (i = 0; i < size; i++) {
			if (year.get(i).getYear()==info) {
				return true;
			}
		}
		return false;
	}
	
	//it return the index for the year in arraylist.
	public static int returnIndex (int info){
		int i, index=0;
		
		for(i=0;i<year.size();i++){
			if(year.get(i).getYear()==info){
				index=i;
			}
		}
		return index;
	}
	
	public void checkForFile(String fileLocation) {
		if (fileLocation.length() == 0) {
			//printMessage(MESSAGE_ERRORFILE);
			System.out.println("Cannot detect the specific file!");
			System.exit(0);
		}
		File file = new File(fileLocation);
		//try {
			if (!file.exists()) {
				//printMessage(MESSAGE_NEWFILE);
				System.out.println("File not found!");
				/*if(sc.nextLine().toUpperCase().equals("Y")){
					file.createNewFile();
				}*/
			}
			else{
				processFile(fileLocation);
			}
		//} catch (IOException e) {
			//printMessage(MESSAGE_ERRORREADFILE);
		//	System.out.println("ERROR!");
		//}
	}
	
	public void processFile(String fileLocation) {

		fileName = fileLocation;
		inputData = new ArrayList<String>();

		try {
			FileReader inputFile = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(inputFile);

			// Read file line by line and store into arraylist
			while ((line = bufferReader.readLine())!=null) {
				inputData.add(line);
			}

			bufferReader.close();
			checkInputData();
		} catch (Exception e) {
			System.out.println("Error while reading file: " + e.getMessage());
			System.exit(0);
		}
	}
	
	private void checkInputData() {
		if(!inputData.isEmpty()){
			processInputFromFile(inputData);
		}
	}

	private void processInputFromFile(ArrayList<String> dataList) {
		int i, year, day, timeStart, timeEnd;
		String task, month;
		String[] splitedData = new String[6];
		
		for(i=0; i<dataList.size();i++){
			splitedData=dataList.get(i).split(" ", 6);
			year= Integer.parseInt(splitedData[0]);
			month = splitedData[1];
			day=Integer.parseInt(splitedData[2]);
			timeStart=Integer.parseInt(splitedData[3]);
			timeEnd=Integer.parseInt(splitedData[4]);
			task=splitedData[5];
			addTask(year, month, day, timeStart, timeEnd, task);
		}
	}

	/**
	 * This method is to print the arraylist into the specified file.
	 */
	public void save() {
		int i, j=0;
		ArrayList<String> data= new ArrayList<String>();

		try {
			FileWriter fileWrite = new FileWriter(fileName);
			BufferedWriter bufferWrite = new BufferedWriter(fileWrite);
			PrintWriter fileOut = new PrintWriter(bufferWrite);
			
			for(i=0;i<year.size();i++){
				data=year.get(i).toArrayList();
				while (j < data.size()) {
					fileOut.println(data.get(j).toString());
					j++;
				}
			}
			
			fileOut.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
