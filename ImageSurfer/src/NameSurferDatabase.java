// TODO: comment this file

import java.io.*;
import java.util.*;

public class NameSurferDatabase implements NameSurferConstants {
	private HashMap<String, NameSurferEntry> database;
	
	// TODO: Initialize the HashMap database
	public NameSurferDatabase(String filename) {
		database = new HashMap<String, NameSurferEntry>();
		
		try {
			Scanner input = new Scanner(new File(filename));
			while(input.hasNextLine()) {
				String words = input.nextLine();
				NameSurferEntry data = new NameSurferEntry(words);
				database.put(data.getName(), data);
			}
		} catch(FileNotFoundException ex) {
			System.out.println("file not found " + ex);
		} 		
	}
	
	// TODO: Returns the NameSurferEntry Object.
	public NameSurferEntry findEntry(String name) {
		return database.get(name);
	}
}

