package clonal_selection;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


//class that creates a population of solutions
public class Population {
			
	private ArrayList<Solution> population;
	private HashMap<String, HashMap<String, Integer>> relations;
	private List<String> cabinetArrangement;
	
		
	
	
	public Population(HashMap<String, HashMap<String, Integer>> relations, List<String> cabinetArrangement) {
		population = new ArrayList<Solution>();
		this.relations = relations;
		this.cabinetArrangement = cabinetArrangement;
	}
	
	public ArrayList<Solution> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<Solution> population) {
		this.population = population;
	}
	


	public List<String> getCabinetArrangement() {
		return cabinetArrangement;
	}

	public void setCabinetArrangement(List<String> cabinetArrangement) {
		this.cabinetArrangement = cabinetArrangement;
	}

	public void createRandomPopulation(List<String> cabinetArrangement, int populationSize){
		
		int size = 0;
		List<ArrayList<String>> added = new ArrayList<ArrayList<String>>();
		
		Solution solution = new Solution(relations, cabinetArrangement);
		solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());

		while(size < populationSize) {
				
				if(added.contains(solution.getPossibleSolution())) {
					solution = new Solution(relations, cabinetArrangement);
					solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());
					continue;
				}
				
				size++;
				population.add(solution);
				//System.out.println(solution.getPossibleSolution() + " " + solution.getCloningFactor());

				added.add(solution.getPossibleSolution());
				
				solution = new Solution(relations, cabinetArrangement);
				solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());

			}
		

		System.out.println("size: " + size);
		
	}
	
	
	public void createSelectedPopulation(Population previousPopulation, int populationSize) {
		
		int currentSize = 0;
		int randomPopulationSize;
		
		for(Solution solution : previousPopulation.getPopulation()) {
			if(solution.getCloningFactor() < 1.0) break;
			population.add(solution);
		//	System.out.println(solution.getPossibleSolution() + " " + solution.getCloningFactor());

			currentSize++;
		}
		if(currentSize <= populationSize) {
			 randomPopulationSize = populationSize - currentSize;
		}else return;
		
		System.out.println("current size: " + currentSize);
		createRandomPopulation(previousPopulation.getCabinetArrangement(), randomPopulationSize);
		for(Solution sol : population) {
		//	System.out.println(sol.getPossibleSolution() + " " + sol.getCloningFactor());
		}
	//	System.out.println("size : " + population.size());
		
		
	}
	
	//counts the average of results of all the solutions in a population
	public double countAveragePath() {
		
		int summedPath = 0;
		double averagePath = 0;
		
		for(Solution solution : population) {

			summedPath += solution.countPath(solution.getPossibleSolution());
		}
		averagePath = (double) summedPath / (double) population.size();
		
		//System.out.println("average path: " + averagePath);
		return averagePath;
	}
		
	//counts the cloning factor for a solution in a population
	public double cloningFactor(Solution solution, double averagePath) {
		
		double factorNum = averagePath / (double) solution.getPath();
		
		solution.setCloningFactor(factorNum);
		return factorNum;
	}
	

	//creates a new population selecting better solutions from a given population
	
	//sorts a population by the cloning factor
	public void sort() {
		Collections.sort(population, new SolutionComparator());
	}
	
	//enables sorting a population by a cloning factor
	public static class SolutionComparator implements Comparator<Solution>{
		
		public int compare(Solution solution1, Solution solution2) {     
			
		return  Double.compare(solution2.getCloningFactor(), solution1.getCloningFactor());

		}
	}

	public void print() {
		
		for(Solution sol : population) {
			System.out.println(sol.getPossibleSolution() + " " + sol.getCloningFactor());
		}
	}
	
}
