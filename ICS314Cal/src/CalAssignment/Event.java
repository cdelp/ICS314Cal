package CalAssignment;


/**
 * Event
 * class Event, sets all event fields by building strings
 * 	and appending required info. 
 * Contains compareTo method, sorting events based on start times.
 * @author TeamElara
 */
public class Event implements Comparable<Event> {
	private String eventSummary, 
		eventStartTime, 
		eventEndTime,
		eventUid, 
		eventTimezone, 
		eventGeoPos, 
		eventClass,
		eventComment;
	
	public void setSummary(String summary){
		eventSummary = new StringBuilder()
			.append("SUMMARY:")
			.append(summary)
			.append("\r\n")
		.toString();
	}
	
	/**
	 * setStart, sets start time, including time zone if provided.
	 * Uses tzInfo boolean from Interface to determine whether user
	 * has provided time zone information, and thus which items to append.
	 * @param date, user set date
	 * @param time, user set time
	 * @param tz, user set time zone
	 */
	public void setStart(String date, String time, String tz){
		if(Interface.getTzInfo()) {
			eventStartTime = new StringBuilder()
			.append("DTSTART;")
			.append("TZID=")
			.append(tz)
			.append(":")
			.append(date)
			.append("T")
			.append(time)
			.append("00")
			.append("\r\n")
		.toString();
		} else { 
			eventStartTime = new StringBuilder()
			.append("DTSTART:")
			.append(date)
			.append("T")
			.append(time)
			.append("00")
			.append("\r\n")
		.toString();
		}		
	}
	
	public void setStart( String date ){
		if(date.contains("TZID")) {
			eventStartTime = new StringBuilder()
					.append("DTSTART;")
					.append(date)
					.append("\r\n")
			.toString();
		} else {
			eventStartTime = new StringBuilder()
					.append("DTSTART:")
					.append(date)
					.append("\r\n")
			.toString();

		}
	}
	
	/**
	 * setEnd, sets end time, including time zone if provided.
	 * Uses tzInfo boolean from Interface to determine whether user
	 * has provided time zone information, and thus which items to append.
	 * @param date, user set date
	 * @param time, user set time
	 * @param tz, user set time zone
	 */
	public void setEnd(String date, String time, String tz){
		if(Interface.getTzInfo()) {
			eventEndTime = new StringBuilder()
			.append("DTEND;")
			.append("TZID=")
			.append(tz)
			.append(":")
			.append(date)
			.append("T")
			.append(time)
			.append("00")
			.append("\r\n")
		.toString();
		} else { 
			eventEndTime = new StringBuilder()
			.append("DTEND:")
			.append(date)
			.append("T")
			.append(time)
			.append("00")
			.append("\r\n")
		.toString();
		}		
	}
	
	public void setEnd( String date ){
		if(date.contains("TZID")) {
		eventEndTime = new StringBuilder()
				.append("DTEND;")
				.append(date)
				.append("\r\n")
			.toString();
		} else {
			eventEndTime = new StringBuilder()
					.append("DTEND:")
					.append(date)
					.append("\r\n")
				.toString();
		}
	}
	
	public void setUID(String uid){
		eventUid = new StringBuilder()
			.append("UID:")
			.append(uid)
			.append("\r\n")
		.toString();
	}
	
	public void setTimeZone(String tz){
		eventTimezone = new StringBuilder()
			.append("TZID=")
			.append(tz)
			.append(":")
			.append("\r\n")
		.toString();
			
	}
	
	public void setGeographicPosition(Float lat, Float lon){
		eventGeoPos = new StringBuilder()
			.append("GEO:")
			.append(lat)
			.append(";")
			.append(lon)
			.append("\r\n")
		.toString();
	}
	
	public void setGeographicPosition(String geo){
		eventGeoPos = new StringBuilder()
				.append("GEO:")
				.append(geo)
				.append("\r\n")
			.toString();
	}

	public void setClassification(String cla ){
		eventClass = new StringBuilder()
			.append("CLASS:")
			.append(cla)
			.append("\r\n")
		.toString();
	}
	
	public void setComment(double miles, double km) {
		eventComment = new StringBuilder()
		  	.append("COMMENT:")
		  	.append("Distance to your next event is: ")
		  	.append(miles)
		  	.append(" miles, ")
		  	.append(km)
		  	.append(" km")
		  	.append("\r\n")
		  .toString();		 
	}
	
	/* getters for all private variables */
	public String getEventSummary() { return eventSummary; }
	public String getEventStartTime() { return eventStartTime; }
	public String getEventEndTime() { return eventEndTime; }
	public String getEventUid() { return eventUid; }
	public String getEventTimezone() { return eventTimezone; }
	public String getEventGeoPos() { return eventGeoPos; }
	public String getEventClass() { return eventClass; }
	public String getEventComment() { return eventComment; }

	
	@Override
	public int compareTo(Event e) {
		String[] date1 = getEventStartTime().split("[:]");
		String[] date2 = e.getEventStartTime().split("[:]");
		
		return date1[1].compareTo(date2[1]);
	}

}
