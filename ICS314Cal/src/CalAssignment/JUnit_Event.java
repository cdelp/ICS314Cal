package CalAssignment;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit_Event
 * Testing class for Event
 * @author TeamElara 
 */
public class JUnit_Event {

	/**
	 * testing setStart called with time zone info during file writing
	 */
	@Test
	public void setStartTZtest() {
		Event testEvent = new Event();
		//testing with time zone
		Interface.setTzInfo(true);
		testEvent.setStart("20150710", "1500", "Pacific");
		assertEquals("DTSTART;TZID=Pacific:20150710T150000\r\n" , testEvent.getEventStartTime());		
	}
	
	/**
	 * testing setEnd called with time zone info during file writing
	 */
	@Test
	public void setEndTZtest() {
		Event testEvent = new Event();
		//testing with time zone
		Interface.setTzInfo(true);
		testEvent.setEnd("20150710", "1500", "Pacific");
		assertEquals("DTEND;TZID=Pacific:20150710T150000\r\n" , testEvent.getEventEndTime());		
	}
	
	/**
	 * testing setStart called without time zone info during file writing
	 */
	@Test
	public void setStartNoTZtest() {
		Event testEvent2 = new Event();
		//testing without time zone
		Interface.setTzInfo(false);
		testEvent2.setStart("20150710", "1500", null);
		assertEquals("DTSTART:20150710T150000\r\n" , testEvent2.getEventStartTime());		
	}
	
	/**
	 * testing setEnd called without time zone info during file writing
	 */
	@Test
	public void setEndNoTZtest() {
		Event testEvent2 = new Event();
		//testing without time zone
		Interface.setTzInfo(false);
		testEvent2.setEnd("20150710", "1500", null);
		assertEquals("DTEND:20150710T150000\r\n" , testEvent2.getEventEndTime());		
	}
	
	/**
	 * Testing setStart called during file reading
	 */
	@Test
	public void setStartTZReadTest() {
		Event testEvent3 = new Event();
		//testing with time zone		
		testEvent3.setStart("TZID=Pacific:20150710T150000");
		assertEquals("DTSTART;TZID=Pacific:20150710T150000\r\n" , testEvent3.getEventStartTime());		
	}
	
	/**
	 * Testing setEnd called during file reading
	 */
	@Test
	public void setEndTZReadTest() {
		Event testEvent3 = new Event();
		//testing with time zone		
		testEvent3.setEnd("TZID=Pacific:20150710T150000");
		assertEquals("DTEND;TZID=Pacific:20150710T150000\r\n" , testEvent3.getEventEndTime());		
	}
	
	/**
	 * Testing setStart called during file reading
	 */
	@Test
	public void setStartNoTZReadTest() {
		Event testEvent4 = new Event();
		//testing without time zone		
		testEvent4.setStart("20150710T150000");
		assertEquals("DTSTART:20150710T150000\r\n" , testEvent4.getEventStartTime());		
	}
	
	/**
	 * Testing setEnd called during file reading
	 */
	@Test
	public void setEndNoTZReadTest() {
		Event testEvent4 = new Event();
		//testing without time zone		
		testEvent4.setEnd("20150710T150000");
		assertEquals("DTEND:20150710T150000\r\n" , testEvent4.getEventEndTime());		
	}

}
