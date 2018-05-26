package clonal_selection;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

import data.Parser;

import java.util.Iterator;

	

public class Main {

		public static void main(String [] args) {
			
			System.out.println("Clonal selection algorytm");
			
			Parser parser = new Parser("test.csv");
			parser.createRelations(parser.read(), parser.getDataSize());
			parser.createCabinets();
			
			//create a variable to hold the frequency relations created in the parser
			HashMap<String, HashMap<String, Integer>> frequencyRelations = parser.getFrequencyRelations();

			System.out.println("");

			//create the initial population (random solutions)
			Population randomPopulation = new Population(frequencyRelations, parser.getCabinets());
			randomPopulation.createRandomPopulation(new ArrayList<String>(parser.getCabinets()), 720);
			System.out.print("first population: \n");
			randomPopulation.print();
			
			System.out.println("");
			
			//create a next population out of selected solution from the initial population
			//complemented by random solutions
			Population selectedPopulation = new Population(frequencyRelations, parser.getCabinets());
			selectedPopulation.createSelectedPopulation(randomPopulation, 720);
			System.out.print("second population: \n");
			selectedPopulation.print();
			
		
		
		}
		
		//a method for printing frequency relations between cabinets
		//basically a nested hashMap representation of the input matrix
		public void printFrequencyRelations(	HashMap<String, HashMap<String, Integer>> frequencyRelations) {
			

			Iterator<Entry<String, HashMap<String, Integer>>> iter = frequencyRelations.entrySet().iterator();
			Map.Entry<String, HashMap<String, Integer>> relations;
			
	        System.out.println("");

	        int number = 0;
	        
			while(iter.hasNext()) {
				number++;
				if(number == 7) {
					System.out.println("");
				}
		        relations = (Map.Entry<String, HashMap<String, Integer>>)iter.next();
				
		        
				  Iterator<Entry<String, Integer>> innerIterator = frequencyRelations.get(relations.getKey()).entrySet().iterator(); 
				  Map.Entry<String, Integer> relations2;
				  
				  while(innerIterator.hasNext()) {
					  relations2 = innerIterator.next();

					  System.out.println(relations.getKey() + "-" + relations2.getKey() + " = " + relations2.getValue());
				  }
			}
			
		}
		
}
