package CalAssignment;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * CalObj, calendar object
 * Creates an arraylist of events.
 * Writes to files.
 * Reads files.
 * @author TeamElara
 */
public class CalObj {
	
	private String 
		version    = "VERSION:1.0\r\n", 
		prodid     = "PRODID://Elara/lofy/tanare/delp/314sum2015//\r\n",
		calBegin   = "BEGIN:VCALENDAR\r\n",
		calEnd     = "END:VCALENDAR\r\n",
		eventBegin = "BEGIN:VEVENT\r\n",
		eventEnd   = "END:VEVENT\r\n";
	private ArrayList<Event> eventArray = new ArrayList<>();
	
	//Adds event to CalObj event array list
	public void addEvent(Event e){
		getEventArray().add(e);
	}
	
	//Writes CalObj to .ics file
	//Takes filename in 
	public void write(String name){
		
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(".ics");
		
		try {
			File file = new File(builder.toString());
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(calBegin);
			bw.write(version);
			bw.write(prodid);
			
			for (int i = 0; i < getEventArray().size(); i++) {
				Event e = getEventArray().get(i);
				bw.write(eventBegin);
				bw.write( e.getEventUid() );
				bw.write( e.getEventStartTime() );
				bw.write( e.getEventEndTime() );
				bw.write( e.getEventSummary() );				
				if(e.getEventGeoPos() != null) {
					bw.write( e.getEventGeoPos() );
				}
				if(e.getEventClass() != null) {
					bw.write( e.getEventClass() );
				}	
				if(i < getEventArray().size() -1) {					
					bw.write( e.getEventComment() );					
				}		
				
				System.out.println(e.getEventComment());
	            bw.write(eventEnd);
	        }
			
			bw.write(calEnd);
			bw.close();
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * read
	 * reads .ics files
	 * @param name, filename
	 */
	public void read(String name){

		String line;
		String[] tokens;
		File file = new File(name); 
		ArrayList<String> stringArray = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			line = br.readLine();
			while ((line = br.readLine()) != null) {
				stringArray.add(line);
			}
			
			for (int i = 0; i < stringArray.size(); i++) {
				String s = stringArray.get(i);
				System.out.println(s);
				
				if (s.equals("BEGIN:VEVENT")){
					
					System.out.println("Started Reading in event\n");
					Event e = new Event();
					while(s.equals("END:VEVENT") == false){
						
						i++;
						s = stringArray.get(i);
						tokens = s.split("[:;=]+", 2);
						System.out.println("0: " +tokens[0] + " 1: " +tokens[1]);
						//Proccess Adding Event Pieces
						if(tokens[0].equals("UID")){
							e.setUID(tokens[1]);
							System.out.println(tokens[1]);
						}
						else if(tokens[0].equals("SUMMARY")){
							e.setSummary(tokens[1]);
							System.out.println(tokens[1]);
						}
						else if(tokens[0].equals("DTSTART")){
							e.setStart(tokens[1]);
							System.out.println(tokens[1]);
						}
						else if(tokens[0].equals("DTEND")){
							e.setEnd(tokens[1]);
							System.out.println(tokens[1]);
						}
						else if(tokens[0].equals("TZID")){
							e.setTimeZone(tokens[1]);
							System.out.println(tokens[1]);
							Interface.setTzInfo(true);
						}
						else if(tokens[0].equals("GEO")){
							e.setGeographicPosition(tokens[1]);
							System.out.println(tokens[1]);							
						}
						else if(tokens[0].equals("CLASS")){
							e.setClassification(tokens[1]);
							System.out.println(tokens[1]);
						}
						
						//System.out.println(s);
						
					}
					this.addEvent(e);
					System.out.println("\nEnded Reading in event\n");
				}								
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * getEventArray
	 * @return eventArray, containing all event info
	 */
	public ArrayList<Event> getEventArray() {
		return eventArray;
	}

	/**
	 * setEventArray
	 * @param eventArray
	 */
	public void setEventArray(ArrayList<Event> eventArray) {
		this.eventArray = eventArray;
	}
	
} 