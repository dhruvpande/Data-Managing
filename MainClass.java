import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author dhruv
 *The main class to run everything
 */
public class MainClass {

	/**
	 * Main method just calls other class methods and moves data around
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException 
	{
		ArrayList<Country> Countrys = new ArrayList<Country>();
		Countrys = Reader.reading();
		WorkerClass.attributeSelection(Countrys);
	}

}
