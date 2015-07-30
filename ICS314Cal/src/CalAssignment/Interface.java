package CalAssignment;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Interface
 * Creates the javax user interface, takes user input.
 * First, repeatedly prompts user to select files for reading. Calls read method with
 * 	user selected filenames.
 * 	Calls AddComment class for sorting and comment adding once the user has finished
 * 	reading files.
 * Second prompts user to create new file.
 * Third creates event file using user input info.
 * @author TeamElara
 */
public class Interface {

	/* Signifies whether user has entered geographic positioning, 
	 * classification, or time zone info.
	 * Used both within this classes' createEvent method, and CalObj's write method. 
	 */ 
	private static boolean hasGeoInfo;
	private static boolean hasClassInfo;
	private static boolean hasTZInfo;
	
	/**
	 * main
	 * executes program
	 * @param args (this program does not use the command line)
	 */
	public static void main(String[] args) {
		CalObj calendar = new CalObj();
		Event event = new Event();
		StringBuilder builder = new StringBuilder();
		
		// Prompts user to to select file for reading.
		while (JOptionPane.showConfirmDialog(null, "Would you like to read an existing event file?"
				, "file", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
			
			JFileChooser reader = new JFileChooser();
			File f = new File("/Users");
			File path;
			String filename;
			
			reader.setCurrentDirectory(f);
			reader.setDialogTitle("Choose a file");
			
			//Calls read method with selected filename.
			if(reader.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				path = reader.getSelectedFile();
				filename = path.getName();
				calendar.read(filename);
			} 		
		}
		
		// If more than one event file has been read, AddComment class is called to sort by 
		// date and time, and add distance comments if appropriate.
		if(calendar.getEventArray().size() > 1) {
			AddComment ac = new AddComment();
			ac.addComment(calendar);
		}
		
		//displaying newly read events for user, sorted and with comments
		JOptionPane filesRead = new JOptionPane();
		for (int i = 0; i < calendar.getEventArray().size(); i++) {
			Event e = calendar.getEventArray().get(i);
			filesRead.setMessage(e.getEventSummary() + 
					e.getEventStartTime() +
					e.getEventEndTime() +
					e.getEventUid() +
					e.getEventGeoPos() + 
					e.getEventClass() +
					e.getEventComment());
			filesRead.setMessageType(JOptionPane.INFORMATION_MESSAGE);
			JDialog dialog = filesRead.createDialog(null, "Event " + (i+1));
			dialog.setVisible(true);
		}
				
		// ask information to setup base event
		String[] prompts = getEventPrompts();
		for(int i = 0; i < prompts.length; i++) {
			builder.append(prompts[i] + ", ");
		}
		
		// Prompts for optional GPS coordinates. Sets hasGeoInfo to true is user chose to this option.
		if(JOptionPane.showConfirmDialog(null, "Would you like to set a geographic position?",
				"Geographic Position", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			String[] optionalPrompts = getOptionalGeoPrompts();
			for(int i = 0; i < optionalPrompts.length; i++) {
				builder.append(optionalPrompts[i] + ", ");
			}
			setGeoInfo(true);
		}
		
		// Prompts for optional classification info. Sets hasClassInfo to true is user chose to this option.
		if(JOptionPane.showConfirmDialog(null, "Would you like to set an access classification?",
				"Classification", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			String[] optionalPrompts = getOptionalClassificationPrompts();
			for(int i = 0; i < optionalPrompts.length; i++) {
				builder.append(optionalPrompts[i] + ", ");
			}
			setClassInfo(true);
		}
		
		// Prompts for optional time zone info. Sets hasTZInfo to true is user chose to this option.
		if(JOptionPane.showConfirmDialog(null, "Would you like to set a timezone?", "Timezone",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			String[] optionalPrompts = getOptionalTimezonePrompts();
			for(int i = 0; i < optionalPrompts.length; i++) {
				builder.append(optionalPrompts[i] + ", ");
			}
			setTzInfo(true);
		}
		
		// final housekeeping: create event, add to calendar, write to calendar file
		event = createEvent(event, builder.toString());
		addToCalendar(calendar, event);
		calendar.write("calendar");
	}
	
	/**
	 * getEventPrompts
	 * gets user input to create the basic event
	 * @return an array of user input (answers)
	 */
	public static String[] getEventPrompts() {
		String title = "Creating your event";
		int type = JOptionPane.QUESTION_MESSAGE;
		return new String[] {
			JOptionPane.showInputDialog(null, "Name the event", title, type),
			JOptionPane.showInputDialog(null, "Set a start date (yyyymmdd)", title, type),
			JOptionPane.showInputDialog(null, "Set a start time (hhmm)", title, type),
			JOptionPane.showInputDialog(null, "Set an end date (yyyymmdd)", title, type),
			JOptionPane.showInputDialog(null, "Set an end time (hhmm)", title, type),
			JOptionPane.showInputDialog(null, "Enter your email address", title, type)
		};		
	}
	
	/**
	 * getOptionalGeoPrompts
	 * gets user input to add coordinate plotting info
	 * @return an array of user input (answers)
	 */
	public static String[] getOptionalGeoPrompts() {
		return new String[] {
			JOptionPane.showInputDialog(null, "Please enter the latitude in decimal degrees"),
			JOptionPane.showInputDialog(null, "Please enter the longitude in decimal degrees")
		};
	}
	
	/**
	 * getOptionalClassificationPrompts
	 * gets user input to add classification information
	 * @return an array of user input (answers)
	 */
	public static String[] getOptionalClassificationPrompts() {
		return new String[] {
			JOptionPane.showInputDialog(null, "Please enter the classification")
		};
	}
	
	/**
	 * getOptionalTimezonePrompts
	 * gets user input to add timezone information
	 * @return an array of user input (answers)
	 */
	public static String[] getOptionalTimezonePrompts() {
		String[] choices = {
			"",
			"Eastern",
			"Central",
			"Mountain",
			"Pacific",
			"Alaska",
			"Hawaii"	
		};
		return new String[] {
			(String) JOptionPane.showInputDialog(null, "Please select a timezone", "Timezone", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0])
		};
	}
	
	/**
	 * createEvent
	 * creates an Event instance with provided data/information
	 * @param event an Event
	 * @param info information about the event to be created
	 * @return a new Event instance
	 */
	public static Event createEvent(Event event, String info) {
		String[] descriptors = info.split(", ");
		int i = 0;
		event.setSummary(descriptors[i++]);
		//only works if timezone info is last in array.
		event.setStart(descriptors[i++], descriptors[i++], descriptors[descriptors.length-1]);
		event.setEnd(descriptors[i++], descriptors[i++], descriptors[descriptors.length-1]);
		event.setUID(descriptors[i++]);
		if(getGeoInfo()) {
			event.setGeographicPosition(Float.parseFloat(descriptors[i++]), Float.parseFloat(descriptors[i++]));
		}
		if(getClassInfo()) {
			event.setClassification(descriptors[i++].toUpperCase());
		}
		if(getTzInfo()) {
			event.setTimeZone(descriptors[i++]);
		}		
		return event;
	}
	
	/**
	 * addToCalendar
	 * add a fully realized event to a calendar object
	 * @param calendar a Calendar object
	 * @param event an Event object
	 */
	public static void addToCalendar(CalObj calendar, Event event) { calendar.addEvent(event); }
	// setters and getters for geographic info, classification, and timezone
	private static void setGeoInfo(boolean b) { hasGeoInfo = b; }
	public static boolean getGeoInfo() { return hasGeoInfo; }
	private static void setClassInfo(boolean b) { hasClassInfo = b; }
	public static boolean getClassInfo() { return hasClassInfo; }
	public static void setTzInfo(boolean b) { hasTZInfo = b; }
	public static boolean getTzInfo() { return hasTZInfo; }
}
