package acsse.csc2a.fmb.file;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acsse.csc2a.fmb.model.*;

public class DisplayFileHandler
{
	
	public DisplayFileHandler()
	{
		
	}
	
	/**
	* Reading a file using ARM
	* @return FireworkDisplay
	* @param fileName File handle to read from
	*/
	public FireworkDisplay readDisplay(File fileName) throws FileNotFoundException
	{
		FireworkDisplay fileFireworkDisplay;
		
		//Use Scanner to read file (can throw exception)
		try(Scanner textin = new Scanner(fileName))
		{
			//Declare Variables (RegEx Match, Scanner Line, Data Pattern)
			Matcher dataMatcher = null;
			String line = null;
			Pattern dataPattern = null;
			
			//Read first line from file
			line = textin.nextLine();
			
			//Process Line
			//(Firework Display)RegEx: ^((FD[0-9]{4})\s(\[[^\s].*?[^\s]\])\s(\"[^\s].*?[^\s]\"))
			//FD0076	[Ode to Joy]	"New Years Eve"
			dataPattern = Pattern.compile("^((FD[0-9]{4})\\s(\\[[^\\s].*?[^\\s]\\])\\s(\\\"[^\\s].*?[^\\s]\\\"))$");
			
			
			//Create Matcher for specific Data Matcher
			dataMatcher = dataPattern.matcher(line);
			
			//Make sure the data matches
			if(dataMatcher.matches())
			{
				//Variables to create FireworksDisplay Instance
				// .group groups up the string according to the RegEx
				String File_FD_ID = dataMatcher.group(2);
				String File_FD_Name = dataMatcher.group(3);
				String File_FD_Theme = dataMatcher.group(4);
				
				//Read second line from file
				line = textin.nextLine();
				
				//Process Line
				//(Pyro Technician)RegEx: ((([A-Z][a-z]*)-([A-Z][a-z]*))\s([1-9][0-9]{2}-[0-9]{3}-[0-9]{3}[1-9]))
				//Jane-Doe	555-010-9111
				dataPattern = Pattern.compile("((([A-Z][a-z]*)-([A-Z][a-z]*))\\s([1-9][0-9]{2}-[0-9]{3}-[0-9]{3}[1-9]))$");
				
				//Create Matcher for specific Data Matcher
				dataMatcher = dataPattern.matcher(line);
				
				//Make sure the data matches
				if(dataMatcher.matches())
				{
					//Variables to create PyroTechnician Instance
					// .group groups up the string according to the RegEx
					String File_PT_FirstName = dataMatcher.group(3);
					String File_PT_LastName = dataMatcher.group(4);
					String File_PT_PhoneNumb = dataMatcher.group(5);
					
					//Create PyroTechnician instance
					PyroTechnician filePyroTechnician = new PyroTechnician(File_PT_FirstName, File_PT_LastName, File_PT_PhoneNumb);
					//Create FireworkDisplay Instance
					fileFireworkDisplay = new FireworkDisplay(File_FD_ID, File_FD_Name, File_FD_Theme, filePyroTechnician);
					
					//Read rest of file
					while(textin.hasNext())
					{
						//Declare variables to read fireworks
						String File_F_ID = null;
						String File_F_Name = null;
						double File_F_Fuse = 0;
						E_COLOUR File_F_Color = null;
						
						//Read from file, line by line
						line = textin.nextLine();
						
						//Process Line
						//(Fireworks)RegEx: ((FW[0-9]{6})\s(.*)\s(\d*\.\d{1,4})\s(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN))$
						//FW007600	Bouncing Betty	1.2	YELLOW
						dataPattern = Pattern.compile("((FW[0-9]{6})\\s(.*)\\s(\\d*\\.\\d{1,4})\\s(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN))$");
						
						//Create Matcher for specific Data Matcher
						dataMatcher = dataPattern.matcher(line);
						
						if(dataMatcher.matches())
						{
							//Variables to create Firework Instance
							// .group groups up the string according to the RegEx
							File_F_ID = dataMatcher.group(2);
							File_F_Name = dataMatcher.group(3);
							File_F_Fuse = Double.valueOf(dataMatcher.group(4));
							File_F_Color = E_COLOUR.valueOf(dataMatcher.group(5));
							
							//Create firework instance
							Firework newFileFirework = new Firework(File_F_ID, File_F_Name, File_F_Fuse, File_F_Color);
							//Add firework to FireworkDisplay
							fileFireworkDisplay.addFireWork(newFileFirework);
							
						}else {
							System.out.println("Firework did not match!");
						}
					}
				}else
				{
					System.err.println(fileName + " HAS AN ERROR");
					System.err.println("Cannot Create PyroTechnitian.\n"
							+ "Relevant information to create class was not correct/incomplete.");
					return null;
				}
				
			}else
			{
				System.err.println(fileName + " HAS AN ERROR");
				System.err.println("Cannot Create Firework Display. "
						+ "Relevant information to create class was not correct/incomplete.");
				return null;
			}
			
		}
		return fileFireworkDisplay;
	}
	
	public static void main(String[] args) {
		
	}

}