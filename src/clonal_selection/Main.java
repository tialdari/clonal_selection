package clonal_selection;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import data.Parser;

import java.util.Iterator;

	

public class Main {

		public static void main(String [] args) {
			
			//input variables: 
			//-population size (30 - 1000)
			//-iteration num (eg. 10)
			//
			
			
			Parser parser = new Parser("test_1.csv");
			parser.createRelations(parser.read(), parser.getDataSize());
			parser.createCabinets();
			
			//create a variable to hold the frequency relations created in the parser
			HashMap<String, HashMap<String, Integer>> frequencyRelations = parser.getFrequencyRelations();

			System.out.println("");
			
			Scanner sc = new Scanner(System.in);
			int populationSize = 0;
			int iterationNum = 0;
			double percentage = 0;
			boolean ifContinue = true;
			String answer;
			
			while(populationSize < 30 || populationSize > 1000) {
				System.out.println("\nChoose size between 30 and 1000: ");
				populationSize = sc.nextInt();
			}
			
			//create the initial population (random solutions)
			Population randomPopulation = new Population(frequencyRelations, parser.getCabinets());
			randomPopulation.createRandomPopulation(new ArrayList<String>(parser.getCabinets()), populationSize);
			
			System.out.print("first population: \n" + "best solution: " + randomPopulation.getBestSolution().getPossibleSolution() +  " " + randomPopulation.getBestSolution().getPath() + "\n");
			
	
			
			while(iterationNum <= 0) {
				System.out.println("Choose an iteration num above 0: ");
				iterationNum = sc.nextInt();
			}
			
			while(percentage <= 0.0) {
				System.out.println("Choose an percentage of the previous population size that should be in the next one: ");
				percentage = sc.nextDouble();
			}
			
					
			Population selectedPopulation = randomPopulation;
			Population previousPopulation = randomPopulation;
			
			while(ifContinue) {
				
				for(int i = 0; i < iterationNum; i ++) {
					System.out.println("");
	
					//create a next population out of selected solution from the initial population
					//complemented by random solutions
					selectedPopulation = new Population(frequencyRelations, parser.getCabinets());
					selectedPopulation.createSelectedPopulation(previousPopulation, populationSize, percentage);
					selectedPopulation.sort();
					selectedPopulation.print();
					previousPopulation = selectedPopulation;
					
				}	
				System.out.println("Best solution: " + selectedPopulation.getBestSolution().getPossibleSolution() + " " + selectedPopulation.getBestSolution().getPath());
				System.out.println("Do you want to continue? yes -> 1, no -> 0");
				answer = sc.next();
				
				while(true) {
					if(answer.equals("0")) {
						ifContinue = false;
						break;
					}else if(answer.equals("1")) {
						System.out.println("Next " + iterationNum + " iterations");
						ifContinue = true;
						break;
					}else {
						System.out.println("No such option, choose 1 or 0");
						answer = sc.next();
					}
				}
			}
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
