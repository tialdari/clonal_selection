package clonal_selection;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Main {

		public static void main(String [] args) {
			
			System.out.println("Clonal selection algorytm");
			Parser parser = new Parser("test.csv");
			parser.createRelations(parser.read(), parser.getDataSize());
			HashMap<String, HashMap<String, Integer>> frequencyRelations = parser.getFrequencyRelations();
			parser.createCabinets();
			System.out.println("");

			
			Functions functions = new Functions();
			
			List<String> randomSetOut = new ArrayList<String>();
			List<String> random = functions.cabinetArrangement(new ArrayList<String>(parser.getCabinets()), randomSetOut);
			System.out.println(random);
			
			System.out.println("path: " + functions.countPath(random, frequencyRelations));

			
	
		}
		
}
