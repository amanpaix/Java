// TODO: comment this file

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {
	
	private String name;
	private ArrayList<Integer> ranks;
	
	// TODO: Initialize the name and ranks.
	public NameSurferEntry(String dataLine) {
		Scanner input = new Scanner(dataLine);
		name = input.next();
		ranks = new ArrayList<Integer>();
		while(input.hasNextInt()) {
			ranks.add(input.nextInt());
		}
		input.close();
	}

	// TODO: Returns the name
	public String getName() {
		return name;	
	}

	// TODO: comment this method
	public int getRank(int decadesSince1900) {
		if(decadesSince1900 < 0 && decadesSince1900 > NUM_DECADES) {
			return -1;
		}
		return ranks.get(decadesSince1900);
	}

	// TODO: Returns String in Format ==> Sam [58, 69, 99, 131, 168, 236, 278, 380, 467, 408, 466] 
	public String toString() {
		return name + " " + ranks.toString();
	}
}

