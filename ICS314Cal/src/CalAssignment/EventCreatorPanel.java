/**
 * Original Author of Template Code: alvinreyes
 * modified by @author Adam
 * http://examples.javacodegeeks.com/core-java/java-jpanel-example/
 */
package CalAssignment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class EventCreatorPanel extends JFrame implements ActionListener {
	private final int LENGTH = 20;
	
	private JLabel[] labels = { 
		new JLabel("Enter a name for the event: "),
		new JLabel("Select the starting date: "),
		new JLabel("Select the starting time: "),
		new JLabel("Select the ending date: "),
		new JLabel("Select the ending time: "),
		new JLabel("Enter an email address: "),
		new JLabel("Select the timezone: ")
	};
	
	private Object[] formfields = {
		new JTextField(LENGTH),
		new JTextField(LENGTH),
		new JTextField(LENGTH),
		new JTextField(LENGTH),
		new JTextField(LENGTH),
		new JTextField(LENGTH),
		new JComboBox<String>(new String[] {
			"", 
			"Eastern",
			"Central",
			"Mountain",
			"Pacific",
			"Alaska",
			"Hawaii"
		})	
	};
	
	private JButton 
		btn_save = new JButton("Save"), 
		btn_clear = new JButton("Clear"), 
		btn_close = new JButton("Close");
	
	public EventCreatorPanel() {
		super("Team Elara Calendar System");
		int row;
        
        // create a new panel with GridBagLayout manager
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST; // left align
        constraints.insets = new Insets(10, 10, 10, 10); // padding
         
        // add components to the panel (enumerate through elements)
        for(row = 0; row < labels.length; row++) {
        	JLabel label = labels[row];
        	constraints.gridx = 0;
        	constraints.gridy = row;
        	panel.add(label, constraints); // label
        	constraints.gridx = 1;
        	// form field
    		panel.add((Component) formfields[row], constraints);
        }
        
        // buttons at bottom of panel
        btn_save.addActionListener(this);
        btn_clear.addActionListener(this);
        btn_close.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(btn_save, constraints);
        constraints.gridx = 1;
        panel.add(btn_clear, constraints);
        constraints.gridx = 2;
        panel.add(btn_close, constraints);
         
        // set border for the panel, add whole panel to GUI instance/frame
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Create your event"));
        add(panel);
        pack();
        setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		int i, size = this.labels.length;
		String[] data = new String[size];
		
		// if user clicks "Save"
		if (e.getSource() == btn_save) {
			for(i = 0; i < size; i++) {
				if(formfields[i] instanceof JTextField) {
					data[i] = ((JTextField)this.formfields[i]).getText();
				}
				else {
					data[i] = ((JComboBox<String>)this.formfields[i]).getSelectedItem().toString();
				}
			}
			printContents(data);
		}
		// if user clicks "Clear"
		else if(e.getSource() == btn_clear) {
			for(i = 0; i < size; i++) {
				if(formfields[i] instanceof JTextField) {
					((JTextField) this.formfields[i]).setText(null);
				}
				else {
					((JComboBox<String>)this.formfields[i]).setSelectedIndex(0);
				}
			}
		}
		// close program
		else if(e.getSource() == btn_close) {
			System.exit(ABORT);
		}
		// fail
		else {
			System.out.println("FAIL PENDING");
		}
	}
	
	private void printContents(String[] data) {
		for(int i = 0; i < data.length; i++) {
			System.out.println(data[i].toString());
		}
		
	}

	public static void main(String[] args) {
		// set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EventCreatorPanel().setVisible(true);
			}
		});
	}
}
