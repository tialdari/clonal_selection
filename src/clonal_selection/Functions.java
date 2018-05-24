package clonal_selection;

import java.util.List;
import java.util.ArrayList;


//contains functions to count results for the individuals of the given population
public class Functions {
	
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
