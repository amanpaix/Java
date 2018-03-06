// TODO: comment this file

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	// TODO: comment this constructor
	private int width;
	private int height;
	private GCompound graph;
	private int space;
	private GCompound lines;
	private HashMap<NameSurferEntry , GCompound> dataLine;
	
	public NameSurferGraph(int width , int height) {
		addComponentListener(this);
		this.width = width;
		this.height = height;
		setBackground(Color.WHITE);
		lines = new GCompound();
		dataLine = new HashMap<NameSurferEntry, GCompound>();
		make(this.width, this.height);		
	}
	
	
	// TODO: Background of the Graph
	public void make(int width , int height) {
		// Graph Background Container
		graph = new GCompound();
		
		// Top Line ==>> Graph
		GLine topLine = new GLine(0 , GRAPH_MARGIN_SIZE , width , GRAPH_MARGIN_SIZE);
		graph.add(topLine);
		
		// Bottom Line ==>> Graph
		GLine bottomLine = new GLine(0 , height - GRAPH_MARGIN_SIZE , width , height - GRAPH_MARGIN_SIZE);
		graph.add(bottomLine);
		
		space = width / NUM_DECADES;
		for(int i = 0; i < NUM_DECADES; i++) {
			// Create Horizontal line ==>> Graph
			GLine decadeLine = new GLine(space * i , 0 , space * i , height);
			graph.add(decadeLine);
			
			// Adding Label in front of Horizontal line ==>> Graph
			GLabel year = new GLabel(Integer.toString(START_DECADE + 10 * i), DECADE_LABEL_MARGIN_SIZE + space * i, height - 5);
			graph.add(year);

		}
		add(graph);
	}
	
	// TODO: Clear all entries from the Graph
	public void clear() {
		for(GCompound g : dataLine.values()) {
			g.removeAll();
		}
		dataLine = new HashMap<NameSurferEntry, GCompound>();
	}
	
	// TODO: Add entry to the Graph
	public void addEntry(NameSurferEntry entry) {
		
		String label = "";
		GLabel nameLabel;
		int getRank1 = 0;
		int getRank2 = 0;
		double posy1 = 0;
		double posy2 = 0;
		GLine line;
		for(int i = 0; i < NUM_DECADES - 1; i++) {
			// get Rank Values
			getRank1 = entry.getRank(i);
			getRank2 = entry.getRank(i + 1);
			
			// When Rank is 0 : Special Case
			if(getRank1 == 0 && getRank2 == 0) {
				posy1 = height - GRAPH_MARGIN_SIZE;
				posy2 = height - GRAPH_MARGIN_SIZE;
			} else if(getRank1 == 0 && getRank2 != 0) {
				posy1 = height - GRAPH_MARGIN_SIZE;
				posy2 = (getRank2 * height / MAX_RANK) + GRAPH_MARGIN_SIZE;
			} else if(getRank1 == 0) {
				posy1 = height - GRAPH_MARGIN_SIZE;
			} else if(getRank2 == 0) {
				posy2 = height - GRAPH_MARGIN_SIZE;
			}  else {
				posy1 = (getRank1 * height / MAX_RANK) + GRAPH_MARGIN_SIZE;
				posy2 = (getRank2 * height / MAX_RANK) + GRAPH_MARGIN_SIZE;
			}
			
			// Add Line to the Graph
			line = new GLine(i * space, posy1  , space + space * i , posy2);
			
			lines.add(line);
			
			// Add Name Label ==>> e.g Sam 45
			label = entry.getName() + " " + entry.getRank(i);
			nameLabel = new GLabel(label);
			lines.add(nameLabel, i * space , posy1);
		}
		// Add Last Name Label ==>> e.g Sam 45
		label = entry.getName() + " " + entry.getRank(NUM_DECADES - 1);
		nameLabel = new GLabel(label);
		lines.add(nameLabel, (NUM_DECADES - 1) * space , posy2);
		add(lines);
		dataLine.put(entry, lines);
	}
	
	// TODO: comment this method
	public void update() {
		// TODO: implement this method
		graph.removeAll();
		this.width = getWidth();
		this.height = getHeight();
		make(this.width, this.height);
		for(GCompound g : dataLine.values()) {
			g.removeAll();
		}
		for(NameSurferEntry n : dataLine.keySet()) {
			addEntry(n);
		}
	}
	
	
	/* Implementation of the ComponentListener interface for updating when the window is resized */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
