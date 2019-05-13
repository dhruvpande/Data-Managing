package Code;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class WorkerClassTest {

	@Test
	void testMeanValue() {
		ArrayList<Country> c = new ArrayList<Country>();
		c.add(new Country());
		c.get(0).setSuicide(10);
		c.add(new Country());
		c.get(1).setSuicide(5);
		c.add(new Country());
		c.get(2).setSuicide(15);
		c.add(new Country());
		c.get(3).setSuicide(12);
		assertEquals(10.5, WorkerClass.meanValue(5, c));
	}

	@Test
	void testModeValue() {
		ArrayList<Country> c = new ArrayList<Country>();
		c.add(new Country());
		c.get(0).setSuicide(10);
		c.add(new Country());
		c.get(1).setSuicide(10);
		c.add(new Country());
		c.get(2).setSuicide(15);
		c.add(new Country());
		c.get(3).setSuicide(12);
		assertEquals(10, WorkerClass.modeValue(5, c));
	}

	@Test
	void testMedianValue() {
		ArrayList<Country> c = new ArrayList<Country>();
		c.add(new Country());
		c.get(0).setSuicide(10);
		c.add(new Country());
		c.get(1).setSuicide(5);
		c.add(new Country());
		c.get(2).setSuicide(15);
		assertEquals(10, WorkerClass.medianValue(5, c));
	}

	@Test
	void testMinValue() {
		ArrayList<Country> c = new ArrayList<Country>();
		c.add(new Country());
		c.get(0).setSuicide(10);
		c.add(new Country());
		c.get(1).setSuicide(5);
		c.add(new Country());
		c.get(2).setSuicide(15);
		c.add(new Country());
		c.get(3).setSuicide(12);
		assertEquals(5, WorkerClass.minValue(5, c));
	}

	@Test
	void testMaxValue() {
		ArrayList<Country> c = new ArrayList<Country>();
		c.add(new Country());
		c.get(0).setSuicide(10);
		c.add(new Country());
		c.get(1).setSuicide(5);
		c.add(new Country());
		c.get(2).setSuicide(15);
		c.add(new Country());
		c.get(3).setSuicide(12);
		assertEquals(15, WorkerClass.maxValue(5, c));
	}

	@Test
	void testQuickSort() {
		ArrayList<Country> c = new ArrayList<Country>();
		c.add(new Country());
		c.get(0).setSuicide(10);
		c.add(new Country());
		c.get(1).setSuicide(5);
		c.add(new Country());
		c.get(2).setSuicide(15);
		c.add(new Country());
		c.get(3).setSuicide(12);
		ArrayList<Country> temp = c;
		Collections.swap(temp, 0, 1);
		Collections.swap(temp, 2, 3);
		WorkerClass.quickSort(5, c,0,c.size()-1, 2);
		assertEquals(temp, c);
	}

	@Test
	void testSearch() {
		ArrayList<Country> c = new ArrayList<Country>();
		c.add(new Country());
		c.get(0).setGeneration("Gen X");
		c.add(new Country());
		c.get(1).setGeneration("Gen Z");
		c.add(new Country());
		c.get(2).setGeneration("Baby Boomer");
		c.add(new Country());
		c.get(3).setGeneration("Silent");
		Attributes a = new Attributes();
		a.s="Gen Z";
		ArrayList<Country> temp = new ArrayList<Country>();
		temp.add(c.get(1));
		assertEquals(temp, WorkerClass.search(9, c, a));
	}

	@Test
	void testGroupBy() {
		ArrayList<Country> c = new ArrayList<Country>();
		c.add(new Country());
		c.get(0).setGeneration("Gen X");
		c.add(new Country());
		c.get(1).setGeneration("Gen Z");
		c.add(new Country());
		c.get(2).setGeneration("Baby Boomer");
		c.add(new Country());
		c.get(3).setGeneration("Silent");
		c.add(new Country());
		c.get(4).setGeneration("Gen X");
		ArrayList<Country> temp = c;
		temp.add(1, c.get(4));
		temp.remove(5);
		WorkerClass.groupBy(9, c);
		assertEquals(temp,c);
	}

}
