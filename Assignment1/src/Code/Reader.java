package Code;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author dhruv
 * This class will read the data and store it in an ArrayList of Country Class 
 */
public class Reader {
	/**
	 * This method scans each line in a CSV file into an object of Class Country and adds it to the ArrayList 
	 * @param filename A object of type File containing the path of the file to be opened 
	 * @return An ArrayList of Country Class Type
	 */
	public static ArrayList<Country> reading(File fileName)
	{
		ArrayList<Country> Countrys = new ArrayList<Country>();
		
		//Checks if file exists
		try {
			Scanner scan = new Scanner(fileName);
			scan.useDelimiter(",");
			
			//Adds each attribute to an object of class Country
			while(scan.hasNext())
			{
				Country c = new Country();
				String line = scan.nextLine();
				String[] fields = line.split(",");
				c.setName(fields[0]);
				c.setName(c.getName().replaceAll("[^a-zA-Z0-9]", ""));
				c.setYear(Integer.parseInt(fields[1]));
				c.setAge(fields[3]);
				c.setSex(fields[2]);
				c.setSuicide(Integer.parseInt(fields[4]));
				c.setPopulation(Integer.parseInt(fields[5]));
				c.setGDP_per_year(Double.valueOf(fields[6]));
				c.setGDP_per_capita(Integer.parseInt(fields[7]));
				c.setGeneration(fields[8]);
				Countrys.add(c);
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Can't find file");
		}
		return Countrys; 
	}
}
