package test;

import static org.junit.Assert.*;
import calendrier.StorageManager;
import org.junit.Before;
import org.junit.Test;

public class StorageManagerTest {
	@Before
	public void init(){
		StorageManager rm= new StorageManager();
		rm.checkForFile("src/calendrier/storageFile.txt") ;
	}
	@Test
	public void checkProcessFile(){
		assertEquals("Check", "2015 Jan 1 130 1130 Apple iPad!\n2015 Jan 1 1230 1500 Apple MacBook!\n2015 Jan 1 1630 1800 Apple MacBook!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
	}
	
	@Test
	public void checkSaveFile(){
		StorageManager rm= new StorageManager();
		rm.deleteAllTask();
		rm.addTask(2015, "Jan", 1, 1630, 1800, "Apple MacBook!");
		rm.addTask(2015, "Jan", 1, 1230, 1500, "Apple MacBook!");
		rm.addTask(2015, "Jan", 1, 130, 1130, "Apple iPad!");
		rm.addTask(2015, "Feb", 11, 1100, 1800, "Apple iPhone!");
		assertEquals("Check", "2015 Jan 1 130 1130 Apple iPad!\n2015 Jan 1 1230 1500 Apple MacBook!\n2015 Jan 1 1630 1800 Apple MacBook!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.save();
	}
	
	@Test
	public void checkAdd(){
		StorageManager rm= new StorageManager();
		rm.addTask(2015, "Jan", 1, 1630, 1800, "Apple MacBook!");
		rm.addTask(2015, "Jan", 1, 1230, 1500, "Apple MacBook!");
		rm.addTask(2015, "Jan", 1, 130, 1130, "Apple iPad!");
		rm.addTask(2015, "Feb", 11, 1100, 1800, "Apple iPhone!");
		assertEquals("Check", "2015 Jan 1 130 1130 Apple iPad!\n2015 Jan 1 1230 1500 Apple MacBook!\n2015 Jan 1 1630 1800 Apple MacBook!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
	}
	@Test
	public void checkAddRepeated(){
		StorageManager rm= new StorageManager();
		rm.addTask(2015, "Jan", 1, 1630, 1800, "Apple MacBook!");
		rm.addTask(2015, "Jan", 1, 1230, 1500, "Apple MacBook!");
		//rm.addTask(2015, "Jan", 1, 1130, 1200, "Apple MacBook!");
		rm.addTask(2015, "Jan", 1, 130, 1130, "Apple iPad!");
		rm.addTask(2015, "Feb", 11, 1100, 1800, "Apple iPhone!");
		assertEquals("Check", "2015 Jan 1 130 1130 Apple iPad!\n2015 Jan 1 1230 1500 Apple MacBook!\n2015 Jan 1 1630 1800 Apple MacBook!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
	}
	@Test
	public void checkView(){
		StorageManager rm= new StorageManager();
		rm.addTask(2015, "Jan", 1, 1630, 1800, "Apple MacBook!");
		rm.addTask(2015, "Jan", 1, 1230, 1500, "Apple MacBook!");
		rm.addTask(2015, "Feb", 1, 130, 1130, "Apple iPad!");
		rm.addTask(2016, "Feb", 11, 1100, 1800, "Apple iPhone!");
		assertEquals("Check", "2015 Jan 1 1230 1500 Apple MacBook!\n2015 Jan 1 1630 1800 Apple MacBook!\n2015 Feb 1 130 1130 Apple iPad!\n", StorageManager.viewYearTask(2015));
		assertEquals("Check", "Feb 1 130 1130 Apple iPad!\n", StorageManager.viewMonthTask(2015, "Feb"));
		assertEquals("Check", "1230 1500 Apple MacBook!\n1630 1800 Apple MacBook!\n", StorageManager.viewDayTask(2015, "Jan" , 1));
		assertEquals("Check", "Jan 1 1230 1500 Apple MacBook!\nJan 1 1630 1800 Apple MacBook!\n", StorageManager.viewMonthTask(2015, "Jan"));
		assertEquals("Check", "2016 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2016));
		assertEquals("Check", "Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewMonthTask(2016, "Feb"));
		assertEquals("Check", "2015 Jan 1 1230 1500 Apple MacBook!\n2015 Jan 1 1630 1800 Apple MacBook!\n2015 Feb 1 130 1130 Apple iPad!\n2016 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewTask());
	}
	@Test
	public void checkDelete(){
		StorageManager rm= new StorageManager();
		rm.addTask(2015, "Jan", 1, 1630, 1800, "Apple MacBook!");
		rm.addTask(2015, "Jan", 1, 1230, 1500, "Apple MacBook!");
		rm.addTask(2015, "Jan", 1, 130, 1130, "Apple iPad!");
		rm.addTask(2015, "Feb", 11, 1100, 1800, "Apple iPhone!");
		assertEquals("Check", "2015 Jan 1 130 1130 Apple iPad!\n2015 Jan 1 1230 1500 Apple MacBook!\n2015 Jan 1 1630 1800 Apple MacBook!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.deleteTask(2015, "Jan", 1, 1630, 1800);
		assertEquals("Check", "2015 Jan 1 130 1130 Apple iPad!\n2015 Jan 1 1230 1500 Apple MacBook!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.addTask(2015, "Jan", 2, 130, 1130, "Apple iPad!");
		assertEquals("Check", "2015 Jan 1 130 1130 Apple iPad!\n2015 Jan 1 1230 1500 Apple MacBook!\n2015 Jan 2 130 1130 Apple iPad!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.deleteDayTask(2015, "Jan", 1);
		assertEquals("Check", "2015 Jan 2 130 1130 Apple iPad!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.deleteMonthTask(2015, "Jan");
		assertEquals("Check", "2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.deleteYearTask(2015);
		assertEquals("Check", "", StorageManager.viewYearTask(2015));
	}
	
	@Test
	public void checkUpdate(){
		StorageManager rm= new StorageManager();
		rm.addTask(2015, "Feb", 11, 1100, 1800, "Apple iPhone!");
		assertEquals("Check", "2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.editTask(2015, "Feb",11 ,1100, 1800, "Bose SoundLink Mini!");
		assertEquals("Check", "2015 Feb 11 1100 1800 Bose SoundLink Mini!\n", StorageManager.viewYearTask(2015));
		rm.editTask(2015, "Feb",11 ,900, 1800);
		assertEquals("Check", "2015 Feb 11 900 1800 Bose SoundLink Mini!\n", StorageManager.viewYearTask(2015));
	}
	
	@Test
	public void checkUndo(){
		StorageManager rm= new StorageManager();
		
		rm.addTask(2015, "Jan", 1, 1630, 1800, "Apple MacBook!");
		rm.undo();
		assertEquals("Check", "", StorageManager.viewYearTask(2015));
		rm.addTask(2015, "Jan", 1, 1230, 1500, "Apple MacBook!");
		assertEquals("Check", "2015 Jan 1 1230 1500 Apple MacBook!\n", StorageManager.viewYearTask(2015));
		rm.addTask(2015, "Jan", 1, 1630, 1800, "Apple MacBook!");
		rm.addTask(2015, "Jan", 1, 130, 1130, "Apple iPad!");
		rm.addTask(2015, "Feb", 11, 1100, 1800, "Apple iPhone!");
		assertEquals("Check", "2015 Jan 1 130 1130 Apple iPad!\n2015 Jan 1 1230 1500 Apple MacBook!\n2015 Jan 1 1630 1800 Apple MacBook!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.deleteTask(2015, "Jan", 1, 1630, 1800);
		assertEquals("Check", "2015 Jan 1 130 1130 Apple iPad!\n2015 Jan 1 1230 1500 Apple MacBook!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.addTask(2015, "Jan", 2, 130, 1130, "Apple iPad!");
		assertEquals("Check", "2015 Jan 1 130 1130 Apple iPad!\n2015 Jan 1 1230 1500 Apple MacBook!\n2015 Jan 2 130 1130 Apple iPad!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.deleteDayTask(2015, "Jan", 1);
		assertEquals("Check", "2015 Jan 2 130 1130 Apple iPad!\n2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.deleteMonthTask(2015, "Jan");
		assertEquals("Check", "2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.deleteMonthTask(2015, "Jan");
		assertEquals("Check", "2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
		rm.deleteYearTask(2015);
		assertEquals("Check", "", StorageManager.viewYearTask(2015));
		rm.undo();
		assertEquals("Check", "2015 Feb 11 1100 1800 Apple iPhone!\n", StorageManager.viewYearTask(2015));
	}
}
