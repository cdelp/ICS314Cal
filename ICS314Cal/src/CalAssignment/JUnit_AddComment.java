/**
 * 
 */
package CalAssignment;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit_AddComment
 * testing class for AddComment
 * @author TeamElara
 */
public class JUnit_AddComment {

	/**
	 * testAddComment, reads events, then calls AddComment
	 */
	@Test
	public void testAddComment() {
		
		CalObj testCalgeo = new CalObj();
		testCalgeo.read("calBW1400geo.ics");
		testCalgeo.read("calCR1430geo.ics");
		testCalgeo.read("calSF2100geo.ics");
		testCalgeo.read("calCL1730geo.ics");
		testCalgeo.read("calSC0700geo.ics");		
		
		AddComment ac = new AddComment();
		ac.addComment(testCalgeo);
		
		Event event1 = testCalgeo.getEventArray().get(0);		
		Event event2 = testCalgeo.getEventArray().get(1);
		Event event3 = testCalgeo.getEventArray().get(2);
		Event event4 = testCalgeo.getEventArray().get(3);
		Event event5 = testCalgeo.getEventArray().get(4);
		
		//events should now be sorted by time
		assertEquals("SUMMARY:Surf Contest\r\n", event1.getEventSummary());
		assertEquals("SUMMARY:Back Waxing\r\n", event2.getEventSummary());
		assertEquals("SUMMARY:Corn Rows\r\n", event3.getEventSummary());
		assertEquals("SUMMARY:Class\r\n", event4.getEventSummary());
		assertEquals("SUMMARY:Street Fight\r\n", event5.getEventSummary());	
		assertEquals("COMMENT:Distance to your next event is: 0.79 miles, 1.28 km\r\n", event1.getEventComment());
		assertEquals("COMMENT:Distance to your next event is: 1.37 miles, 2.2 km\r\n", event2.getEventComment());
	
	}	
}
