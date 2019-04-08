import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WorkerClass {
	
	public static void actionSelection(int attribute, ArrayList<Country> C)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Which of the following would you like to do?");
		System.out.println("1. Mean");
		System.out.println("2. Mode");
		System.out.println("3. Median");
		System.out.println("4. Max");
		System.out.println("5. Min");
		System.out.println("6. Sort");
		System.out.println("7. Group by");
		System.out.println("8. Search");
		System.out.print("Please enter you choice:");
		//attribute-=1;
		ArrayList<Country> temp = new ArrayList<Country>();
		int i;
		
		do
		{
			i=scan.nextInt();
			switch(i)
			{
				case 1:
				{
					System.out.println(meanValue(attribute,C));
					break;
				}
				
				case 2:
				{
					System.out.println(modeValue(attribute,C));
					break;
				}
				
				case 3:
				{
					System.out.println(medianValue(attribute,C));
					break;
				}
				
				case 4:
				{
					maxValue(attribute,C);
					break;
				}
				
				case 5:
				{
					minValue(attribute,C);
					break;
				}
				
				case 6:
				{
					System.out.println("Please enter second parameter to sort by based on the same number system as above");
					int attribute2 = scan.nextInt();
					System.out.println("Please enter");
					System.out.println("1. Descending");
					System.out.println("2. Ascending");
					int order = scan.nextInt();
					quickSort(attribute,C,0,C.size()-1,order);
					secondSort(attribute,attribute2,C,order);
					for(int j=0;j<C.size();j++)
						C.get(j).printer();
					break;
				}
				
				case 7:
				{
					temp = groupBy(attribute,C);
					for(int j=0;j<temp.size();j++)
						temp.get(j).printer();
					break;
				}
				
				case 8:
				{
					Attributes a = new Attributes();
					scan.nextLine();
					System.out.print("Please enter the item you wish to search for: ");
					if (C.get(0).getAttribute(attribute).s.equals(null))
					{
						double searchitem = scan.nextDouble();
						a.num=searchitem;
					}
					else
					{
						String searchitemtext = scan.nextLine();
						a.s=searchitemtext;
					}
					search(attribute,C,a);
					break;
				}
				
				default:
				{
					System.out.println("Please enter a valid number");
				}
			}
			
		}while(i>8);
		scan.close();
	}
	
	public static void attributeSelection(ArrayList<Country> C)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Which of the following would you like to choose?");
		System.out.println("1. Country");
		System.out.println("2. Year");
		System.out.println("3. Sex");
		System.out.println("4. Age");
		System.out.println("5. Suicides");
		System.out.println("6. Population");
		System.out.println("7. GDP per year");
		System.out.println("8. GDP per capita");
		System.out.println("9. Generation");
		System.out.print("Please enter you choice:");
		int i;
		
		do
		{
			i=scan.nextInt();
			if (i<=9)
				actionSelection(i, C);
			else
			{
				System.out.println("Please enter a valid number");
			}
			
		}while(i>9);
		
		scan.close();
	}
	
	public static double meanValue(int attribute, ArrayList<Country> C)
	{
		double mean=0.00;
		if(C.get(0).getAttribute(attribute).s.equals(null))
		{
			for(int i=0;i<C.size();i++)
			{
				mean+=C.get(i).getAttribute(attribute).num;
			}
			mean/=C.size();
		}
		else
			System.out.println("Invalid choice");
		return mean;
	}
	
	public static double modeValue(int attribute, ArrayList<Country> C)
	{
		double mode=0.00;
		double maxcount=0;
		if(C.get(0).getAttribute(attribute).s.equals(null))
		{
			for(int i=0;i<C.size();i++)
			{
				int count=0;
				for (int j=0;j<C.size();j++)
				{
					if(C.get(i).getAttribute(attribute).num==C.get(j).getAttribute(attribute).num)
					{
						count+=1;
					}
				}
				if (count>maxcount)
				{
					maxcount=count;
					mode=C.get(i).getAttribute(attribute).num;
				}
				
			}
		}
		else
			System.out.println("Invalid choice");
		
		return mode;
	}
	
	public static double medianValue(int attribute, ArrayList<Country> C)
	{
		double median=0.00;
		ArrayList<Country> Ctemp =new ArrayList<Country>();
		Ctemp=C;
		quickSort(attribute,Ctemp,0,Ctemp.size()-1,1);
		if(C.get(0).getAttribute(attribute).s.equals(null))
		{
			if (Ctemp.size()%2!=0)
			{
				median = Ctemp.get(Ctemp.size()/2).getAttribute(attribute).num;
			}
			else
			{
				median = (Ctemp.get(Ctemp.size()/2).getAttribute(attribute).num+Ctemp.get((Ctemp.size()/2)-1).getAttribute(attribute).num)/2;
			}			
		}
		else
			System.out.println("Invalid choice");
		return median;
	}
	
	public static double minValue(int attribute, ArrayList<Country> C)
	{
		double min=C.get(0).getAttribute(attribute).num;
		if(C.get(0).getAttribute(attribute).s.equals(null))
		{
			for(int i=0;i<C.size();i++)
			{
				if(C.get(i).getAttribute(attribute).num<min)
					min = C.get(i).getAttribute(attribute).num;
			}
		}
		else
			System.out.println("Invalid choice");
		return min;
	}
	
	public static double maxValue(int attribute, ArrayList<Country> C)
	{
		double max=C.get(0).getAttribute(attribute).num;
		if(C.get(0).getAttribute(attribute).s.equals(null))
		{
			for(int i=0;i<C.size();i++)
			{
				if(C.get(i).getAttribute(attribute).num>max)
					max = C.get(i).getAttribute(attribute).num;
			}
		}
		else
			System.out.println("Invalid choice");
		return max;
	}
	
	public static void quickSort(int attribute, ArrayList<Country> C, int begin, int end,int order)
	{
		if (begin<end)
		{
			int partitionindex = partition(attribute, C, begin, end,order);
			
			quickSort(attribute,C, begin, partitionindex-1,order);
			quickSort(attribute,C, partitionindex,end,order);
			
		}
		
	}
	
	private static int partition(int attribute, ArrayList<Country> c, int begin, int end, int order) 
	{
		Attributes pivot = c.get(end).getAttribute(attribute);
		int i = begin-1;
		
		if (order==1)
		{
			for(int j = begin; j<end;j++)
			{
				if(c.get(j).getAttribute(attribute).num>=(pivot.num))
				{
					i++;
					
					Collections.swap(c, i, j);
				}
			}
		}
		else if(order==2)
		{
			for(int j = begin; j<end;j++)
			{
				if(c.get(j).getAttribute(attribute).num<=(pivot.num))
				{
					i++;
					
					Collections.swap(c, i, j);
				}
			}
		}
		Collections.swap(c, i+1, end);
		return i+1;
	}
	
	private static void secondSort(int attribute, int attribute2, ArrayList<Country> C, int order) {
		// TODO Auto-generated method stub
		for(int i=0;i<C.size()-1;i++)
		{
			for(int j=i+1;j<C.size();j++)
			{
				if(C.get(i).getAttribute(attribute).s.equals(null))
				{
					
					if(C.get(i).getAttribute(attribute).num!=C.get(j).getAttribute(attribute).num)
					{
						if (i-j!=1)
						{
							System.out.println(i+" "+j);
							quickSort(attribute2,C,i,j-1,order);
							
						}
						i=j;
						break;
					}
				}
			}
		}
	}

	public static void search(int attribute, ArrayList<Country> C,Attributes a)
	{
		if(a.s.equals(null))
		{
			for(int i =0;i<C.size();i++)
			{
				if(C.get(i).getAttribute(attribute).num==a.num)
				{
					C.get(i).printer();
				}
			}
		}
		else
		{
			for(int i =0;i<C.size();i++)
			{
				if(C.get(i).getAttribute(attribute).s.equalsIgnoreCase(a.s))
				{
					C.get(i).printer();
				}
			}
		}
	}
	
	public static ArrayList<Country> groupBy(int attribute, ArrayList<Country> C)
	{
		for(int i=0;i<C.size();i++)
		{
			for(int j=0;j<i;j++)
			{
				if(j==i)
				{
					continue;
				}
				
				if(C.get(i).getAttribute(attribute).s.equals(null))
				{
					System.out.println("Here");
					if(C.get(i).getAttribute(attribute).num==C.get(j).getAttribute(attribute).num)
					{
						C.add(j+1,C.get(i));
						C.remove(i);
						break;
					}
				}
				else
				{
					if(C.get(i).getAttribute(attribute).s.equals(C.get(j).getAttribute(attribute).s))
					{
						C.add(j+1,C.get(i));
						C.remove(i+1);
						break;
					}
				}
			}
		}
		return C;
	}
}
