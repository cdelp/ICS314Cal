package CalAssignment;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit-GreatCircle 
 * tests GreatCircle class
 * @author TeamElara
 */
public class JUnit_GreatCircle {

	/**
	 * circleDistTest, tests both long and short distances
	 * HNL to LAX, and UH to Kapiolani Park
	 */
	@Test
	public void circleDistTest() {
		GreatCircle distTest = new GreatCircle();
		
		double UHlat = 21.298307;
		double UHlong = -157.816023;
		double KPlat = 21.267356;
		double KPlong = -157.819414;
		
		double HNLlat = 21.318681;
		double HNLlong = -157.922428;
		double LAXlat = 33.942536;
		double LAXlong = -118.408075;		
		
		//Honolulu to Los Angelos, http://www.gcmap.com/ HNL-LAX miles: 2556, km: 4113
		double milesFar = distTest.miles(HNLlat, HNLlong, LAXlat, LAXlong);
		double kmFar = distTest.km(HNLlat, HNLlong, LAXlat, LAXlong);		
		assertEquals((double)2556, milesFar, 10);
		assertEquals((double)4113, kmFar, 10);
		
		//UH Manoa to Kapiolani Park
		double milesNear = distTest.miles(UHlat, UHlong, KPlat, KPlong);
		double kmNear = distTest.km(UHlat, UHlong, KPlat, KPlong);
		assertEquals((double)2.1, milesNear, .1);
		assertEquals((double)3.3, kmNear, .1);
		
	}

}
