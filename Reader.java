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
	 * @return An ArrayList of Country Class Type
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Country> reading() throws FileNotFoundException
	{
		ArrayList<Country> Countrys = new ArrayList<Country>();
		//Update the file path as needed
		Scanner scan = new Scanner(new File("C:\\Users\\dhruv\\eclipse-workspace1\\Assignment1\\src\\master1.csv"));
		scan.useDelimiter(",");
		while(scan.hasNext())
		{
			Country c = new Country();
			String line = scan.nextLine();
			String[] fields = line.split(",");
			c.setName(fields[0]);
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
		return Countrys; 
	}
}
