package clonal_selection;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;



public class Main {

		public static void main(String [] args) {
			
			System.out.println("Clonal selection algorytm");
			Parser parser = new Parser("test.csv");
			parser.createRelations(parser.read(), parser.getDataSize());
			HashMap<String, HashMap<String, Integer>> frequencyRelations = parser.getFrequencyRelations();
			
			
			/*

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
				*/
			
			parser.createCabinets();
			System.out.println("");

			/*
			Functions functions = new Functions();
			
			ArrayList<String> randomSetOut = new ArrayList<String>();
			ArrayList<String> random = functions.cabinetArrangement(new ArrayList<String>(parser.getCabinets()), randomSetOut);
			System.out.println(random);
			*/
			
			//System.out.println("path: " + functions.countPath(random, frequencyRelations));

			Population pop = new Population(frequencyRelations);
			pop.createPopulation(new ArrayList<String>(parser.getCabinets()), 20);
			//System.out.println(frequencyRelations)

			System.out.println("averagePath: " + pop.countAveragePath());
			
	
		}
		
}
