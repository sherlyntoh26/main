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
import stub.CalenderYear;
//import stub.CalenderMonth;
//import stub.CalenderDate;
//import stub.CalenderTime;
//import java.io.IOException;

public class StorageManager {
	private static String fileName;
	private static ArrayList<String> inputData;
	private static String line;
	private static ArrayList<CalenderYear> year;
	private static ArrayList<String> backup;
	//static CalenderYear name;
	
	public StorageManager(){
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
