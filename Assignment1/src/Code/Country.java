package Code;

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
	
	@Override
	public String toString()
	{
		String res="";
		
		res+=("Name           :"+getName()+"\n");
		res+=("Year           :"+getYear()+"\n");
		res+=("Sex            :"+getSex()+"\n");
		res+=("Age            :"+getAge()+"\n");
		res+=("Suicide        :"+getSuicide()+"\n");
		res+=("Population     :"+getPopulation()+"\n");
		res+=("GDP per Year   :"+getGDP_per_year()+"\n");
		res+=("GDP per Capita :"+getGDP_per_capita()+"\n");
		res+=("Generation     :"+getGeneration()+"\n");
		res+=("----------------------------------\n");
		return res;
	}
}
