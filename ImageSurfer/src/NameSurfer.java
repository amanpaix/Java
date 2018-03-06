// TODO: comment this file

import acm.program.*;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	
	private JTextField nameTextField;
	private JButton graphButton;
	private JButton clearButton;
	private NameSurferDatabase database;
	private NameSurferGraph graph;

	public void init() {
		//        __________    _____     _____
		// Name  |__________|  |Graph|   |Clear|
		
		// For name
		JLabel nameLabel = new JLabel("Name");
		add(nameLabel , NORTH);
		
		// Set Layout
//		setLayout(new FlowLayout());
		
		// For Text Field
		nameTextField = new JTextField(TEXT_FIELD_WIDTH);
		add(nameTextField , NORTH);
		nameTextField.addActionListener(this);
		nameTextField.setActionCommand("Graph");
		
		// For Graph
		graphButton = new JButton("Graph");
		add(graphButton , NORTH);
		
		// For Clear
		clearButton = new JButton("Clear");
		add(clearButton , NORTH);
		
		database = new NameSurferDatabase(NAMES_DATA_FILE);
		graph = new NameSurferGraph(getWidth() , getHeight());
		add(graph, CENTER);
		addActionListeners();	
	}
	
	public void actionPerformed(ActionEvent event) {
		// convert “lisa”, “LISA” or “LiSa” ===>>> “Lisa”
		String name = nameTextField.getText();
		if(name.length() > 0) {
			name = name.toLowerCase();
			name = Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
		}
		
		// identify which button is clicked
		String command = event.getActionCommand();
		if(command.equals("Graph")) {
			if(name.length() > 0) {
				NameSurferEntry data = database.findEntry(name);
				if(data != null) {
					graph.addEntry(data);
				}
			}
		} else {
			graph.clear();
		}
	}
}
