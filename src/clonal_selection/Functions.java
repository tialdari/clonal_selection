package clonal_selection;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;



//contains functions to count results for the individuals of the given population
public class Functions {
	
	
	//creates a random cabinet arrangement
	//abc and cba cases are counted as two or 1?
	//as the argument the method gets a copy of the original cabinet list
	//randomArrangement passed as the argument is an empty list
	public List<String> cabinetArrangement(List<String> cabinets, List<String> randomArrangement) {
		
		//if all letters are moved to the new list, leave the method
		if(cabinets.size() == 0) return randomArrangement;
		
		Random r = new Random();
		int random = r.nextInt(cabinets.size());
		
		//when one randomly chosen letter is added to a new list, 
		randomArrangement.add(cabinets.get(random));
		//it's removed from the cabinet list(letters to choose from)
		cabinets.remove(random);
		//we call the method recursively, to choose another letter
		//from the decreased list
		cabinetArrangement(cabinets, randomArrangement);
		
		return randomArrangement;
		
	}
	

	//counts the overall road to cross between cabinets for the given solution
	public int countPath(List<String> solution, HashMap<String, HashMap<String, Integer>> frequencyRelations) {
		
		int path = 0;
		int distance = 1;
		int cabinet1Index = 0;
		int cabinet2Index = 1;
		String letter1;
		String letter2;
		int frequency;

		
		while(cabinet1Index < solution.size() - 2) {
			
			letter1 = solution.get(cabinet1Index);
			letter2 = solution.get(cabinet2Index);
			frequency = frequencyRelations.get(letter1).get(letter2).intValue();
			
			path += distance * frequency;
			cabinet2Index++;
			if(cabinet2Index == solution.size() - 1) {
				cabinet1Index++;
				cabinet2Index = cabinet1Index + 1;
			}
		}
		
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
