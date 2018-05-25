package clonal_selection;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;


//contains functions to count results for the individuals of the given population
public class Functions {
	
	
	//creates a random cabinet arrangement
	//abc and cba cases are counted as two or 1?
	//as the argument the method gets a copy of the original cabinet list
	//randomArrangement passed as the argument is an empty list
	public List<String> cabinetArrangement(List<String> cabinets, List<String> randomArrangement) {
		
		if(cabinets.size() == 0) return randomArrangement;
		
		Random r = new Random();
		int random = r.nextInt(cabinets.size());
		
		randomArrangement.add(cabinets.get(random));
		cabinets.remove(random);
		cabinetArrangement(cabinets, randomArrangement);
		
		return randomArrangement;
		
	}
	
	
	
	
	//counts the overall road to cross between cabinets for the given solution
	public int countPath() {
		int path = 0;
		return path;
	}
	
	//counts the average of results of all the solutions
	public int countAveragePath(ArrayList<String> solutions) {
		int averagePath = 0;
		return averagePath;
	}
	
	//counts the mutation factor for a path of the given population (averagePath)
	//and the given individual (givenPath)
	public int mutationFactor(int averagePath, int givenPath) {
		
		int mutationFactor = averagePath / givenPath;
		return mutationFactor;
	}
}
