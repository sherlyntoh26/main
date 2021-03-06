package calendrier;

import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Collections;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import utils.CalenderYear;
import utils.Event;
import utils.Priority;

import java.util.List;
import java.util.TimeZone;


public class StorageManager {
	private static String fileName;
	private static List<String> inputData;
	private static String line;
	private static ArrayList<CalenderYear> year;

	//private static List<String> backup;
	//static CalenderYear name;
	
	public StorageManager(){
		year= new ArrayList<CalenderYear>();
		//backup= new ArrayList<String>();
	}
	
	@SuppressWarnings("deprecation")
	public void add(Event event){
		int index;

		//updateStatus();
		if(!isYearAvaliable(event.getStartDateTime().getTime().getYear())){
			year.add(new CalenderYear(event));
		}
		else{
			index= returnIndex(event.getStartDateTime().getTime().getYear());
			year.get(index).addMonth(event);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void remove(Event event){	
		int index=returnIndex(event.getStartDateTime().getTime().getYear());
		//updateStatus();
		year.get(index).getMonth(event.getStartDateTime().getTime().getMonth()).getDate(event.getStartDateTime().getTime().getDate()).deleteTask(event);
	}
	
	public void update(Event eventOld, Event eventNew){
		//updateStatus();
		remove(eventOld);
		add(eventNew);
	}
	
	public void clear(){
		year.clear();
	}
	
	public List<Event> load(){
		int i;
		List<Event> events = new ArrayList<>();
		for(i=0;i<year.size();i++){
			events.addAll(year.get(i).getTask());
		}
		return events;
	}
	
	//This is use to check for the content stored in List. Will remove after coding is done.
	public String listToString(){
		List<Event> events = new ArrayList<>();
		String data="";
		events=load();
		int i;
		
		for(i=0;i<events.size();i++){
			data=data.concat(events.get(i).toString()+ "\n");
		}
		return data;
	}
	
	/*//update the current status that prepared for undo function.
	public void updateStatus() {
		int i, j = 0;
		List<Event> data = new ArrayList<Event>();
		backup.clear();
		for (i = 0; i < year.size(); i++) {
			data = year.get(i).getTask();
			while (j < data.size()) {
				backup.add(data.get(j).toString());
				j++;
			}
		}
	}
	public void undo(){
		year.clear();
		processInputFromFile(backup);
	}*/
	
	public static Boolean isYearAvaliable (int info) {
		
		int i, size = year.size();

		for (i = 0; i < size; i++) {
			if (year.get(i).getYear()==info) {
				return true;
			}
		}
		return false;
	}
	
	//it return the index for year in the List.
	public static int returnIndex (int info){
		int i, index=0;
		
		for(i=0;i<year.size();i++){
			if(year.get(i).getYear()==info){
				index=i;
			}
		}
		return index;
	}
	
	/**
	 * This method is to check for file location.
	 */
	public void setStorageLocation(String fileLocation) {
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
	
	/**
	 * This method is to print the arraylist into the specified file.
	 */
	public void save() {
		int i, j=0;
		List<Event> data= new ArrayList<Event>();

		try {
			FileWriter fileWrite = new FileWriter(fileName);
			BufferedWriter bufferWrite = new BufferedWriter(fileWrite);
			PrintWriter fileOut = new PrintWriter(bufferWrite);
			
			for(i=0;i<year.size();i++){
				data=year.get(i).getTask();
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
	
	/**
	 * This method is to read input from file.
	 */
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
			//System.exit(0);
		}
	}
	
	//check whether the input is empty
	private void checkInputData() {
		if(!inputData.isEmpty()){
			processInputFromFile(inputData);
		}
	}
	
	/**
	 * This method is to convert the string file to event as well as adding it to the List.
	 */
	private void processInputFromFile(List<String> dataList) {
		int i;
		int[] startDate= new int[6], endDate= new int[6];
		String title, id;
		String[] splitedData = new String[6];
		Priority prior;
		
		for(i=0; i<dataList.size();i++){
			
			splitedData=dataList.get(i).split(", ", 5);

			id=removeName(splitedData[0]);
			title=removeName(splitedData[1]);
			prior=determinePrior(removeName(splitedData[4]));
			
			startDate=convertDate(removeName(splitedData[2]));
			endDate=convertDate(removeName(splitedData[3]));
			
			Calendar calendarStart = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
			calendarStart.set(startDate[0], startDate[1], startDate[2], startDate[3], startDate[4], startDate[5]);
			Calendar calendarEnd = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
			calendarEnd.set(endDate[0], endDate[1], endDate[2], endDate[3], endDate[4], endDate[5]);
			
			
			Event event1 = new Event();
			event1.setId(id);
			event1.setTitle(title);
			event1.setStartDateTime(calendarStart);
			event1.setEndDateTime(calendarEnd);
			event1.setPriority(prior);
			
			add(event1);
		}
	}

	//remove the tag in string. Example: ID: abc, this method will remove ID: and return abc.
	private String removeName(String input){
		String[] splitedData = new String[2];
		splitedData=input.split(": ", 2);
		return splitedData[1];
	}
	
	//convert date from string format(MM DD HH:MM:SS YY) to int array
	private int[] convertDate(String input){
		String[] splitedData = new String[6];
		int[] date= new int[6];
		int[] time= new int[3];
	
		splitedData=input.split(" ", 6);
		date[0] = Integer.parseInt(splitedData[5]);
		date[1] = convertMonth(splitedData[1])-1;
		date[2] = Integer.parseInt(splitedData[2]);
		time=convertTime(splitedData[3]);
		date[3] = time[0];
		date[4] = time[1];
		date[5] = time[2];
		
		return date;
	}
	
	//convert time from string format(HH:MM:SS) to int array
	private int[] convertTime(String input){
		String[] splitedData = new String[3];
		int[] time= new int[3];
		
		splitedData=input.split(":", 3);
		time[0] = Integer.parseInt(splitedData[0]);
		time[1] = Integer.parseInt(splitedData[1]);
		time[2] = Integer.parseInt(splitedData[2]);
		return time;
	}
	
	//convert string month to int
	private int convertMonth(String input){
		switch(input){
		case"Jan":
			return 1;
		case"Feb":
			return 2;
		case"Mar":
			return 3;
		case"Apr":
			return 4;
		case"May":
			return 5;
		case"Jun":
			return 6;
		case"Jul":
			return 7;
		case"Aug":
			return 8;
		case"Sep":
			return 9;
		case"Oct":
			return 10;
		case"Nov":
			return 11;
		case"Dec":
			return 12;
		}
		return 0;
	}
	
	//convert string to priority data type
	private Priority determinePrior(String input){
		switch(input){
		case "HIGH":
			return Priority.HIGH;
		case "MEDIUM":
			return Priority.MEDIUM;
		case "VERY_LOW":
			return Priority.VERY_LOW;
		case "VERY_HIGH":
			return Priority.VERY_HIGH;
		case "LOW":
			return Priority.LOW;
		}
		return Priority.MEDIUM;
	}
}
