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
	private double averagePath;
	
	public Population(HashMap<String, HashMap<String, Integer>> relations, List<String> cabinetArrangement) {
		population = new ArrayList<Solution>();
		this.relations = relations;
		this.cabinetArrangement = cabinetArrangement;
		averagePath = 0.0;
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
	

	public double getAveragePath() {
		return averagePath;
	}

	public void setAveragePath(double averagePath) {
		this.averagePath = averagePath;
	}

	//creates a random population
	//method is used at the very beginning of the programme 
	//and later to replace the space after inefficient solutions
	//that were discarded
	public void createRandomPopulation(List<String> cabinetArrangement, int populationSize){
		
		int size = 0;
		
		//list to check if the current solution
		//has been already added to the population 
		
		List<ArrayList<String>> added = new ArrayList<ArrayList<String>>();
		
		//create a new solution object
		Solution solution = new Solution(relations, cabinetArrangement);
		//assign a cabinet arrangement to it, which is a possible solution
		solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());

		while(size < populationSize) {
				
				//continue, if the solution is already in the population
				if(added.contains(solution.getPossibleSolution())) {
					solution = new Solution(relations, cabinetArrangement);
					solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());
					continue;
				}
				
				size++;
			
				//add solution to the population
				population.add(solution);
				//and to the control list
				added.add(solution.getPossibleSolution());
				
				solution = new Solution(relations, cabinetArrangement);
				solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());
				
				if(size == populationSize) {
					
					//count the average path when the population is already created
					countAveragePath();
					
					//count and assign a cloning factor for all the solutions
					cloningFactor();
				}

			}
	}
	
	//creates a new population selecting better solutions from a given population
	public void createSelectedPopulation(Population previousPopulation, int populationSize) {
		
		int currentSize = 0;
		int randomPopulationSize;
		
		//copy solutions from the previous population until the cloning factor is bigger than 1
		for(Solution solution : previousPopulation.getPopulation()) {
			if(solution.getCloningFactor() < 1.0) break;
			
			//add as many clones of the current solution
			//as indicates the cloning factor
			//eg. if cloning factor is 2 -> 3 copies of this solution will go to the next population
			for(int i = 0; i <= (int) solution.getCloningFactor(); i++) {
				population.add(solution);
			}
			currentSize++;
		}
		
		//count the number of random solutions that should be added
		if(currentSize <= populationSize) {
			 randomPopulationSize = populationSize - currentSize;
		}else return;
		
		//create random solutions and add them to the population 
		//until the wished size is reached
		createRandomPopulation(previousPopulation.getCabinetArrangement(), randomPopulationSize);
			
	}
	
	//counts the cloning factor for a solution in a population
		public void cloningFactor() {
			
			double factorNum = 0.0; 
			
			for(Solution sol : population) {
				factorNum = averagePath / (double) sol.getPath();
				sol.setCloningFactor(factorNum);
			}
		}
	
	
	//counts the average of results of all the solutions in a population
	public double countAveragePath() {
		
		int summedPath = 0;
		double averagePath = 0;
		
		for(Solution solution : population) {

			summedPath += solution.countPath(solution.getPossibleSolution());
		}
		averagePath = (double) summedPath / (double) population.size();
		
		setAveragePath(averagePath);
		return averagePath;
	}
		
	
		
	
	//enables sorting a population by a cloning factor
	public static class SolutionComparator implements Comparator<Solution>{
		
		public int compare(Solution solution1, Solution solution2) {     
			
		return  Double.compare(solution2.getCloningFactor(), solution1.getCloningFactor());

		}
	}
	
	//sorts a population by the cloning factor
		public void sort() {
			Collections.sort(population, new SolutionComparator());
		}

	//prints all the solutions of the population sorted
	public void print() {
		
		sort();
		for(Solution sol : population) {
			System.out.println(sol.getPossibleSolution() + " " + sol.getCloningFactor());
		}
	}
	
}
