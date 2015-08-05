package CalAssignment;

import java.util.Collections;
import java.util.Iterator;


/**
 * AddComment
 * Adds COMMENT field to event with distance to next event
 * 	when geographic positions are provided for both events.
 * @author TeamElara 
 */
public class AddComment {

	/**
	 * addComment, iterates through arraylist of events, calling GreatCircle
	 * when geographic positions are available for both events.
	 * @param cal, calendar object generated at time of reading
	 */
	public void addComment(CalObj cal) {
		
		GreatCircle gc = new GreatCircle();
		//sorting arraylist of events based on start time
		Collections.sort(cal.getEventArray());
	
		Iterator<Event> itr = cal.getEventArray().iterator();
		
		//counter used for skipping first event
		int eCount = 0;
		
		//iterates through list of events, adding great circle distance when appropriate
		while(itr.hasNext()) {
			
			itr.next();

			if (eCount > 0) {
				Event previous = cal.getEventArray().get(eCount - 1);
				Event current = cal.getEventArray().get(eCount);
								
				if(previous.getEventGeoPos() != null && current.getEventGeoPos() != null) {
					String[] geo1 = previous.getEventGeoPos().split("[:;]");
					String[] geo2 = current.getEventGeoPos().split("[:;]");
					
					double lat1 = Double.parseDouble(geo1[1]);
					double long1 = Double.parseDouble(geo1[2]);
					double lat2 = Double.parseDouble(geo2[1]);
					double long2 = Double.parseDouble(geo1[2]);
					
					//sets previous event's comment section with distance to current event
					previous.setComment(gc.miles(lat1, long1, lat2, long2), 
							gc.km(lat1, long1, lat2, long2));
					
					//testing output only
					System.out.println("Added to " + previous.getEventSummary() + previous.getEventStartTime() 
						+ previous.getEventComment());
				} else {
					previous.setComment("no distance available");
				}
			}

			eCount++;
		}
	}
}
