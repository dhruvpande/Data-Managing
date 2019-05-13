package GUI;

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
