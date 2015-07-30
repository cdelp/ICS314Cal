package CalAssignment;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnit_CalObj {

	/**
	 * JUnit_CalObj
	 * Testing class for CalObj
	 * @author TeamElara 
	 */
	@Test
	public void testRead() {
		
		CalObj testCal = new CalObj();
		testCal.read("calBW1400geo.ics");
		testCal.read("calSF2100geo.ics");
		testCal.read("calCR1430geo.ics");
		
		Event event1 = testCal.getEventArray().get(0);		
		Event event2 = testCal.getEventArray().get(1);
		Event event3 = testCal.getEventArray().get(2);
		
		//Check read and sort on events
		assertEquals("SUMMARY:Back Waxing\r\n", event1.getEventSummary());
		assertEquals("SUMMARY:Corn Rows\r\n", event2.getEventSummary());
		assertEquals("SUMMARY:Street Fight\r\n", event3.getEventSummary());	
	}	
}

