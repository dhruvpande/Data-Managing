package GUI;

/**
 * This class is used to hold the values of the various drop down bars for ease of access
 * @author dhruv
 *
 */
public class DataHolding {
	private int ID;
	private String txt;
	
	public DataHolding(int iD, String txt) {
		super();
		ID = iD;
		this.txt = txt;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	@Override
	public String toString()
	{
		return this.getTxt();
	}
	
}
