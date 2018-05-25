package clonal_selection;

import java.util.List;
import java.util.ArrayList;

//class that creates a population of solutions
public class Population {
			
	
	
	public List<ArrayList<String>> createPopulation(ArrayList<String> possibleSolution, int populationSize){
		
		List<ArrayList<String>> population = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < populationSize; i ++) {
			if(!population.contains(possibleSolution)) {
				population.add(possibleSolution);
			}
		}
		
		return population;
		
	}
}
