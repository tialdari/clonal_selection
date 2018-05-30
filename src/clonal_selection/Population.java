
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
	private Solution bestSolution;
	
	public Population(HashMap<String, HashMap<String, Integer>> relations, List<String> cabinetArrangement) {
		population = new ArrayList<Solution>();
		this.relations = relations;
		this.cabinetArrangement = cabinetArrangement;
		averagePath = 0.0;
		bestSolution = null;
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

	
	public Solution getBestSolution() {
		
		bestSolution = population.get(0);
		
		return bestSolution;
	}

	public void setBestSolution(Solution bestSolution) {
		this.bestSolution = bestSolution;
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

		}
		
		if(size == populationSize) {
			//count the average path when the population is already created
			countAveragePath();
			
			//count and assign a cloning factor for all the solutions
			cloningFactor();
		}
	}
	
	//creates a new population selecting better solutions from a given population
	public void createSelectedPopulation(Population previousPopulation, int populationSize, double perc) {
		
		int intendedSize = (int)(populationSize * perc/100);
		System.out.println("intended size: " + intendedSize ) ;
		int currentSize = 0;
		int randomPopulationSize;
		Solution mutant;
		List<ArrayList<String>> added = new ArrayList<ArrayList<String>>();

		
		//copy solutions from the previous population until the cloning factor is bigger than 1
		for(Solution solution : previousPopulation.getPopulation()) {
			if(solution.getCloningFactor() < 1.0) break;
			
			//add as many clones of the current solution
			//as indicates the cloning factor
			//eg. if cloning factor is 2 -> 3 copies of this solution will go to the next population
			for(int i = 0; i <= (int) solution.getCloningFactor(); i++) {
				//if we get a complete population by cloning the solution from the previous population
				//leave the method
				if(currentSize == intendedSize) break;
				
				//we create a mutation of each clone
				//if mutation is less efficient or already exists in the population we discard it
				mutant = new Solution(solution.getFrequencyRelations(), solution.getCabinets());
				ArrayList<String> mutantSol = solution.mutate();
				mutant.setPossibleSolution(mutantSol);
				mutant.setPath(mutant.countPath(mutant.getPossibleSolution()));
				
				//System.out.println("M: " + mutantSol + " " + mutant.getPath() );
			//	System.out.println("S: " + solution.getPossibleSolution() + " " + solution.getPath() );


				if(mutant.getPath() >=  solution.getPath() || added.contains(mutant.getPossibleSolution()) ) {
					population.add(solution);
					added.add(solution.getPossibleSolution());
					//System.out.println("original");
				}else {
					population.add(mutant);
					added.add(mutant.getPossibleSolution());
					//System.out.println("mutant");
				}
			
				currentSize++;
			}
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
			
			sort();
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
		
		for(Solution sol : population) {
			System.out.println(sol.getPossibleSolution() + " " + sol.getPath());
		}
	}
	
	
}
