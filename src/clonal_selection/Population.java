package clonal_selection;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

//class that creates a population of solutions
public class Population {
			
	private ArrayList<Solution> population;
	private HashMap<String, HashMap<String, Integer>> relations;
	
		
	public ArrayList<Solution> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<Solution> population) {
		this.population = population;
	}
	
	public Population(HashMap<String, HashMap<String, Integer>> relations ) {
		population = new ArrayList<Solution>();
		this.relations = relations;

		
	}

	public void createRandomPopulation(List<String> cabinetArrangement, int populationSize){
		
		int size = 0;
		
		Solution solution = new Solution(relations, cabinetArrangement);
		 solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());
		
		while(size < populationSize) {
			if(!population.contains(solution)) {
				size++;
				population.add(solution);
				//System.out.println(possibleSolution);
			}
			solution = new Solution(relations, cabinetArrangement);
			solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());
		}
		System.out.println("size: " + size);
		
	}
	
	//counts the average of results of all the solutions in a population
	public int countAveragePath() {
		
		int summedPath = 0;
		int averagePath = 0;

		for(Solution solution : population) {

			summedPath += solution.countPath(solution.getPossibleSolution());
		}
		averagePath = summedPath / population.size();
		
		return averagePath;
	}
		
	//counts the cloning factor for a solution in a population
	public double cloningFactor(int ownPath, int averagePath) {
		
		double factorNum = (double) averagePath / (double)ownPath;
		
		return factorNum;
	}
		
	//creates a new population selecting better solutions from a given population
	/*
	public static class EntryKeyComparator implements Comparator<ArrayList<String>, Integer>{
		
		public int compare(ArrayList<String> solution1,
					ArrayList<String> solution2) {     
			
			int difference = Integer.compare(solution1., solution2.getKey().size());

			if(difference != 0) return  difference;
			
			//if the stops number is the same, compare by distance
			else return Integer.compare(left.getValue(), right.getValue());
		}
	}
	*/
	
}
