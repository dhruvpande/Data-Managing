package GUI;

/**
 * This class is used to hold the chart data in an organised manner that can be directly transfers to the chart
 * @author dhruv
 *
 */
public class ChartData {
	
	private String name;
	private double value;
	private double secondValue;
	
	public ChartData(String name, double value, double secondValue) {
		super();
		this.name = name;
		this.value = value;
		this.secondValue = secondValue;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getSecondValue() {
		return secondValue;
	}
	public void setSecondValue(double secondValue) {
		this.secondValue = secondValue;
	}
	
	@Override
	public String toString()
	{
		return ("Name : "+getName()+" Value 1: "+getValue()+" Value 2: "+getSecondValue());
		
	}
	
}
