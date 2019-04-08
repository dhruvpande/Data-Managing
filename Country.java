
/**
 * @author pandd4
 *
 */
public class Country {
	private String name;
	private int year;
	private String age;
	private String sex;
	private int suicide;
	private int population;
	private double GDP_per_year;
	private int GDP_per_capita;
	private String generation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getSuicide() {
		return suicide;
	}
	public void setSuicide(int suicide) {
		this.suicide = suicide;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public double getGDP_per_year() {
		return GDP_per_year;
	}
	public void setGDP_per_year(double gDP_per_year) {
		GDP_per_year = gDP_per_year;
	}
	public int getGDP_per_capita() {
		return GDP_per_capita;
	}
	public void setGDP_per_capita(int gDP_per_capita) {
		GDP_per_capita = gDP_per_capita;
	}
	public String getGeneration() {
		return generation;
	}
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	
	public int suicidePerPop(){
		return (this.suicide*100000/this.population);
	}
	
	public Attributes getAttribute(int attribute)
	{
		Attributes a =new Attributes();
		
		switch(attribute)
		{
			case 1:
			{
				a.s=getName();
				break;
			}
			case 2:
			{
				a.num=(getYear());				
				break;
			}
			case 3:
			{
				a.s=getSex();
				break;
			}
			case 4:
			{
				a.s=getAge();
				break;
			}
			case 5:
			{
				a.num=(getSuicide());
				break;
			}
			case 6:
			{
				a.num=getPopulation();
				break;
			}
			case 7:
			{
				a.num=(getGDP_per_year());
				break;
			}
			case 8:
			{
				a.num=(getGDP_per_capita());
				break;
			}
			case 9:
			{
				a.s=getGeneration();
				break;
			}
		}
		return a;
	}
	
	public void printer()
	{
		System.out.println("Name           :"+getName());
		System.out.println("Year           :"+getYear());
		System.out.println("Sex            :"+getSex());
		System.out.println("Age            :"+getAge());
		System.out.println("Suicide        :"+getSuicide());
		System.out.println("Population     :"+getPopulation());
		System.out.println("GDP per Year   :"+getGDP_per_year());
		System.out.println("GDP per Capita :"+getGDP_per_capita());
		System.out.println("Generation     :"+getGeneration());
		System.out.println("----------------------------------");
	}
}
