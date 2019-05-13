package Code;
import java.util.ArrayList;
import java.util.Collections;

import GUI.ChartData;

/**
 * 
 * @author dhruv
 * This class contains all the methods that do the calculations in the background. 
 * It handles the data and returns it in the correct form. 
 */

public class WorkerClass {
	
	/**
	 * This function is part of a set of overloaded functions that calls the respective number manipulation functions  
	 * @param attribute is the first attribute that must be used
	 * @param C contains an ArrayList of type Country containing all the data to be manipulated
	 * @param action is the user selected action
	 * @return a double value of the associated number action
	 */
	public static double actionSelection(int attribute, ArrayList<Country> C, int action )
	{
		double ans=0.00;
		switch(action)
		{
			case 1:
			{
				ans = meanValue(attribute,C);
				break;
			}
	
			case 2:
			{
				ans = modeValue(attribute,C);
				break;
			}
	
			case 3:
			{
				ans = medianValue(attribute,C);
				break;
			}
	
			case 4:
			{
				ans = maxValue(attribute,C);
				break;
			}
	
			case 5:
			{
				ans = minValue(attribute,C);
				break;
			}

		}
		return ans;
	}
	
	/**
	 * This function is part of a set of overloaded functions that calls the sorting functions  
	 * @param attribute is the first attribute that must be used
	 * @param C contains an ArrayList of type Country containing all the data to be manipulated
	 * @param action is the user selected action
	 * @param attribute2 is the second attribute to be used
	 * @param order specifies whether it is in ascending or descending order
	 * @return ArrayList of type Country containing sorted list
	 */
	public static ArrayList<Country> actionSelection(int attribute, ArrayList<Country> C, int action, int attribute2, int order)
	{
		//Sorting
		if(attribute==6) 
		{
			quickSort(attribute,C,0,C.size()-1,order);
			if(attribute2!=-1)
				secondSort(attribute,attribute2,C,order);
		}
		//Group by
		else
		{
			C = groupBy(attribute,C);
			if(attribute2!=-1)
				secondSort(attribute,attribute2,C,order);
		}
		return C;
		
	}
	
	/**
	 * This function is part of a set of overloaded functions that calls the Search functions  
	 * @param attribute is the first attribute that must be used
	 * @param C contains an ArrayList of type Country containing all the data to be manipulated
	 * @param action is the user selected action
	 * @param searchItem contains the value to be searched
	 * @return ArrayList of type Country containing a list of items found
	 */
	public static ArrayList<Country> actionSelection(int attribute, ArrayList<Country> C, int action, String searchItem)
	{
		Attributes a = new Attributes();
		//for double values
		if (C.get(0).getAttribute(attribute).s==(null))
		{
			a.num=Double.parseDouble(searchItem);
		}
		//for String values
		else
		{
			a.s=searchItem;
		}
		return(search(attribute,C,a));
	}
	
	/**
	 * This method returns the mean value of the selected attribute
	 * @param attribute contains the attribute to be used 
	 * @param C contains an ArrayList of type Country containing all the data to be manipulated
	 * @return double value that is the mean of the selected attribute over the entire data set
	 */
	public static double meanValue(int attribute, ArrayList<Country> C)
	{
		double mean=0.00;
		if(C.get(0).getAttribute(attribute).s==(null))
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
	
	/**
	 * This method returns the mode value of the selected attribute
	 * @param attribute contains the attribute to be used 
	 * @param C contains an ArrayList of type Country containing all the data to be manipulated
	 * @return double value that is the mode of the selected attribute over the entire data set
	 */
	public static double modeValue(int attribute, ArrayList<Country> C)
	{
		double mode=0.00;
		double maxcount=0;
		if(C.get(0).getAttribute(attribute).s==(null))
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
	
	/**
	 * This method returns the median value of the selected attribute
	 * @param attribute contains the attribute to be used 
	 * @param C contains an ArrayList of type Country containing all the data to be manipulated
	 * @return double value that is the median of the selected attribute over the entire data set
	 */
	public static double medianValue(int attribute, ArrayList<Country> C)
	{
		double median=0.00;
		ArrayList<Country> Ctemp =new ArrayList<Country>();
		Ctemp=C;
		quickSort(attribute,Ctemp,0,Ctemp.size()-1,1);
		if(C.get(0).getAttribute(attribute).s==(null))
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
	
	/**
	 * This method returns the minimum value of the selected attribute
	 * @param attribute contains the attribute to be used 
	 * @param C contains an ArrayList of type Country containing all the data to be manipulated
	 * @return double value that is the minimum of the selected attribute over the entire data set
	 */
	public static double minValue(int attribute, ArrayList<Country> C)
	{
		double min=C.get(0).getAttribute(attribute).num;
		if(C.get(0).getAttribute(attribute).s==(null))
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
	
	/**
	 * This method returns the maximum value of the selected attribute
	 * @param attribute contains the attribute to be used 
	 * @param C contains an ArrayList of type Country containing all the data to be manipulated
	 * @return double value that is the maximum of the selected attribute over the entire data set
	 */
	public static double maxValue(int attribute, ArrayList<Country> C)
	{
		double max=C.get(0).getAttribute(attribute).num;
		if(C.get(0).getAttribute(attribute).s==(null))
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
	
	/**
	 * This method sorts the data according to the Quick sort algorithm 
	 * @param attribute is the selected attribute according the data needs to be sorted
	 * @param C is the ArrayList of type Country that contains the data set to be sorted
	 * @param begin is the index of the beginning of the data set
	 * @param end is the index of the end of the data set
	 * @param order is order in which the data needs to be sorted, ascending or descending 
	 */
	public static void quickSort(int attribute, ArrayList<Country> C, int begin, int end,int order)
	{
		if (begin<end-1)
		{
			int partitionindex = partition(attribute, C, begin, end,order);
			
			quickSort(attribute,C, begin, partitionindex-1,order);
			if (partitionindex<end-1)
				quickSort(attribute,C, partitionindex,end,order);
			
		}
		
	}
	
	/**
	 * This a method returns the pivot index for the Quick sort algorithm and swaps the values into either side of the 
	 * pivot element 
	 * @param attribute is the selected attribute according the data needs to be sorted
	 * @param C is the ArrayList of type Country that contains the data set to be sorted
	 * @param begin is the index of the beginning of the data set
	 * @param end is the index of the end of the data set
	 * @param order is order in which the data needs to be sorted, ascending or descending 
	 * @return pivot index on which to divide the data
	 */
	private static int partition(int attribute, ArrayList<Country> c, int begin, int end, int order) 
	{
		Attributes pivot = c.get(end).getAttribute(attribute);
		int i = begin-1;
		//if the attribute to be pivoted is a number
		if (pivot.s==(null))
		{
			//1 is descending and 2 is ascending
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
		}
		//if the attribute to be pivoted is a String
		else
		{
			if (order==1)
			{
				for(int j = begin; j<end;j++)
				{
					if(c.get(j).getAttribute(attribute).s.compareTo(pivot.s)>=0)
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
					if(c.get(j).getAttribute(attribute).s.compareTo(pivot.s)<0)
					{
						i++;
						
						Collections.swap(c, i, j);
					}
				}
			}
			Collections.swap(c, i+1, end);
		}
		return i+1;
	}
	
	/**
	 * This method is used to sort the current data set again based on the second attribute after
	 * being sorted by the first attribute
	 * @param attribute is the selected attribute according the data needs to be sorted
	 * @param attribute2 is the second selected attribute on which to sort the data 
	 * @param C is the ArrayList of type Country that contains the data set to be sorted
	 * @param order is order in which the data needs to be sorted, ascending or descending
	 */
	private static void secondSort(int attribute, int attribute2, ArrayList<Country> C, int order) {
		/* fucntion searches the array till it reaches the first set of sequential elements that arent the same according to 
		attribute1 and the sorts that small list according to the second attribute
		*/
		for(int i=0;i<C.size()-1;i++)
		{
			for(int j=i+1;j<C.size();j++)
			{
				if(C.get(i).getAttribute(attribute).s==(null))
				{
					
					if((C.get(i).getAttribute(attribute).num!=C.get(j).getAttribute(attribute).num)||(j==C.size()-1))
					{
						if (i!=j)
						{
							quickSort(attribute2,C,i,j-1,order);
							
						}
						i=j-1;
						break;
					}
				}
				else
				{
					if((C.get(i).getAttribute(attribute).s.equals(C.get(j).getAttribute(attribute).s)==false)||(j==C.size()-1))
					{
						if (i!=j)
						{
							quickSort(attribute2,C,i,j-1,order);
							
						}
						i=j-1;
						break;
					}
				}
			}
		}
	}

	/**
	 * This method searches for all the objects that contain a specific value of the selected attribute
	 * @param attribute is the attribute which needs to be searched
	 * @param C is the ArrayList of type Country that contains the data set to be searched
	 * @param a is an object of type Attribute which contains the value to be searched 
	 * @return an ArrayList of type Country that counts the objects that contain the searched value
	 */
	public static ArrayList<Country> search(int attribute, ArrayList<Country> C,Attributes a)
	{
		ArrayList<Country> temp = new ArrayList<Country>();
		if(a.s==(null))
		{
			for(int i =0;i<C.size();i++)
			{
				if(C.get(i).getAttribute(attribute).num==a.num)
				{
					temp.add(C.get(i));
				}
			}
		}
		else
		{
			for(int i =0;i<C.size();i++)
			{
				if(C.get(i).getAttribute(attribute).s.equalsIgnoreCase(a.s))
				{
					temp.add(C.get(i));
				}
			}
		}
		return temp;
	}
	
	/**
	 * This method groups the data set according to the selected attribute
	 * @param attribute is the attribute to be grouped by
	 * @param C is the ArrayList of type Country that contains the data set to be grouped
	 * @return An ArrayList of type Country that contains the data set grouped according to the attribute
	 */
	public static ArrayList<Country> groupBy(int attribute, ArrayList<Country> C)
	{
		//finds elements in the array the same as the current element according to the attribute 
		//and inserts them immediately after said element
		for(int i=0;i<C.size();i++)
		{
			for(int j=0;j<i;j++)
			{
				if(j==i)
				{
					continue;
				}
				
				if(C.get(i).getAttribute(attribute).s==(null))
				{
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
	
	/**
	 * This method creates the data list to be used in the Bar Chart creation 
	 * @param C is the ArrayList of type Country that contains the data set to be converted
	 * @param attribute is the first attribute to be used as the X-axis of the graph
	 * @param attribute2 is the second attribute to be used as the Y-axis of the graph
	 * @return An ArrayList of type ChartData that contains the bar graph data in the correct format
	 */
	public static ArrayList<ChartData> barChartDataCreating(ArrayList<Country> C,int attribute, int attribute2)
	{
		//Similar to group by but adds all the data of the second attribute together
		ArrayList<ChartData> cd = new ArrayList<ChartData>();
		C = groupBy(attribute,C);
		int k=0;
		for(int i=0;i<C.size();i++)
		{
			cd.add(new ChartData(C.get(i).getAttribute(attribute).s,C.get(i).getAttribute(attribute2).num,0));
			if(i==C.size()-1) 
				continue;
			for(int j=i+1;j<C.size();j++)
			{	
				if(C.get(i).getAttribute(attribute).s.equals(C.get(j).getAttribute(attribute).s))
				{
					cd.get(k).setValue(cd.get(k).getValue()+C.get(j).getAttribute(attribute2).num);
				}
				else
				{
					i=j-1;
					break;
				}
			}
			k++;
		}
		return cd;
	}
	
	/**
	 * This method creates the data list to be used in the Line Chart creation 
	 * @param C is the ArrayList of type Country that contains the data set to be converted
	 * @param attribute is the first attribute to be used as the Series of the graph
	 * @param attribute2 is the second attribute to be used as the X-axis of the graph
	 * @param attribute3 is the third attribute to be used as the Y-axis of the graph
	 * @return An ArrayList of type ChartData that contains the line graph data in the correct format
	 */
	public static ArrayList<ChartData> lineChartDataCreating(ArrayList<Country> C, int attribute, int attribute2, int attribute3)
	{
		ArrayList<ChartData> cd = new ArrayList<ChartData>();
		C = groupBy(attribute,C);
		int k =0;
		boolean marker=false;
		for(int i=0;i<C.size();i++)
		{
			cd.add(new ChartData(C.get(i).getAttribute(attribute).s,C.get(i).getAttribute(attribute2).num,C.get(i).getAttribute(attribute3).num));
			if(i==C.size()-1) 
				continue;
			for(int j=i+1;j<C.size();j++)
			{	
				if(C.get(i).getAttribute(attribute).s.equals(C.get(j).getAttribute(attribute).s))
				{
					while(k<cd.size())
					{
						if (C.get(j).getAttribute(attribute2).num==(C.get(k).getAttribute(attribute2).num))
						{
							cd.get(k).setSecondValue(cd.get(k).getSecondValue()+C.get(j).getAttribute(attribute3).num);
							marker = true;
							break;
						}
						k++;
					}
					if(!marker)
					{
						cd.add(new ChartData(C.get(j).getAttribute(attribute).s,C.get(j).getAttribute(attribute2).num,C.get(j).getAttribute(attribute3).num));
					}
					
				}
				else
				{
					i=j-1;
					k=cd.size();
					break;
				}
				if(j==C.size()-1)
				{
					i=j;
				}
			}
		}
		return cd;
	}
}
