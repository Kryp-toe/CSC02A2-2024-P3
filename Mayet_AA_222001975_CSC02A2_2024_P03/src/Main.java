import java.io.File;
import acsse.csc2a.fmb.file.*;
import acsse.csc2a.fmb.model.*;

public class Main {

	public static void main(String[] args)
	{
		//Declare fireworkdisplay
		FireworkDisplay ReadFromFileFireworkDisplay;
		//Declare filenames
		String[] FileNamess = {"data/clean_1.txt", "data/dirty_1.txt", "data/dirty_2.txt", "data/dirty_3.txt", "data/dirty_4.txt", 
				"data/dirty_5.txt", "data/dirty_6.txt", "data/dirty_7.txt", "data/partial_1.txt", "data/partial_2.txt", 
				"data/partial_3.txt", "data/partial_4.txt",	"data/partial_5.txt", };
		
		//for ech file name
		for(String FileName : FileNamess)
		{
			//create file handle
			File handle = new File(FileName);
			System.out.println("***********************************************************************");
			System.out.println("***********************************************************************");
			System.out.println("Reading from: \" " + handle + " \" : ");
			
			try
			{
				//Create DisplayFileHandler
				DisplayFileHandler readFromFileHandler = new DisplayFileHandler();
				
				//Read from files and create FireworkDisplay instance
				ReadFromFileFireworkDisplay = readFromFileHandler.readDisplay(handle);
				
				//Print FireworkDisplay/PyroTechnician and Firework
				ReadFromFileFireworkDisplay.printDisplay();
				
			} catch (Exception e) 
			{
				//If cannot create Firework Display (file is faulty)
				System.err.println("FILE IS FAULTY. TRYING NEXT FILE.");
				System.out.println("***********************************************************************");
			}
		}	
	}
}