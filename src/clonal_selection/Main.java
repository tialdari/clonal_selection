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
			HashMap<String, HashMap<String, Integer>> frequencyRelations = parser.getFrequencyRelations();
			
			parser.createCabinets();
			System.out.println("");

			Population pop = new Population(frequencyRelations, parser.getCabinets());
			pop.createRandomPopulation(new ArrayList<String>(parser.getCabinets()), 720);
			double averagePath = pop.countAveragePath();
		//	System.out.println("Average path: " + averagePath);
			
			for(Solution sol : pop.getPopulation()) {
				pop.cloningFactor(sol, averagePath);
			}
			pop.sort();
			//pop.print();
			
			Population population1 = new Population(frequencyRelations, parser.getCabinets());
			population1.createSelectedPopulation(pop, 600);
			double averagePath1 = population1.countAveragePath();
			for(Solution sol : population1.getPopulation()) {
				population1.cloningFactor(sol, averagePath1);
			}
			population1.sort();

			for(Solution sol : population1.getPopulation()) {
				//System.out.println(sol.getPossibleSolution() + " " + sol.getCloningFactor());
			}
			System.out.println("size : " + population1.getPopulation().size());
			population1.print();

	
			
		}
		
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
