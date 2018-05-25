package clonal_selection;

import java.util.List;
import java.util.ArrayList;

//class that creates a population of solutions
public class Population {
			
	
	
	public ArrayList<ArrayList<String>> createPopulation(List<String> cabinetArrangement, int populationSize){
		
		int size = 0;
		Functions functions = new Functions();
		
		ArrayList<ArrayList<String>> population = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> possibleSolution = functions.cabinetArrangement(new ArrayList<>(cabinetArrangement), new ArrayList<String>());
		
		while(size < populationSize) {
			if(!population.contains(possibleSolution)) {
				size++;
				population.add(possibleSolution);
				System.out.println(possibleSolution);
			}
			possibleSolution = functions.cabinetArrangement(new ArrayList<>(cabinetArrangement), new ArrayList<String>());
		}
		System.out.println("size: " + size);
		return population;
		
	}
	
	//public int averagePopulationPath(List<String> population) {
		
		
	//}
}
