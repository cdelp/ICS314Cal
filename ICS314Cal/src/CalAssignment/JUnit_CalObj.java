package CalAssignment;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnit_CalObj {

	/**
	 * JUnit_CalObj
	 * @author TeamElara
	 * Testing class for CalObj
	 */
	@Test
	public void testRead() {
		
		CalObj testCal = new CalObj();
		testCal.read("calendarBW.ics");
		testCal.read("calendarSF.ics");
		testCal.read("calendarCR.ics");
		
		Event event1 = testCal.getEventArray().get(0);
		Event event2 = testCal.getEventArray().get(1);
		Event event3 = testCal.getEventArray().get(2);
		
		assertEquals("SUMMARY:Back Waxing\r\n", event1.getEventSummary());
		assertEquals("SUMMARY:Street Fight\r\n", event2.getEventSummary());
		assertEquals("SUMMARY:Corn Rows\r\n", event3.getEventSummary());
	
	}

}