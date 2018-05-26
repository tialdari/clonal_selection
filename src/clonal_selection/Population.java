package clonal_selection;

import java.util.List;

import java.util.Iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Comparator;


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
		 boolean ifContinue = true;
		List<ArrayList<String>> added = new ArrayList<ArrayList<String>>();
		
		Solution solution = new Solution(relations, cabinetArrangement);
		solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());
		//System.out.println(solution.getPossibleSolution());
		
		
			while(size < populationSize) {
				
				if(added.contains(solution.getPossibleSolution())) {
					//System.out.println("already here: " + solution.getPossibleSolution());
					solution = new Solution(relations, cabinetArrangement);
					solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());
					continue;
				}
				
				size++;
				population.add(solution);
				added.add(solution.getPossibleSolution());
			//	System.out.println(solution.getPossibleSolution());
				
				solution = new Solution(relations, cabinetArrangement);
				solution.cabinetArrangement(new ArrayList<>(solution.getCabinets()), new ArrayList<String>());
				//System.out.println(solution.getPossibleSolution());

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
	public double cloningFactor(Solution solution, int averagePath) {
		
		double factorNum = (double) averagePath / (double) solution.getPath();
		
		solution.setCloningFactor(factorNum);
		return factorNum;
	}
	
	
		
	//creates a new population selecting better solutions from a given population
	
	
	public void sort() {
		

		Collections.sort(population, new SolutionComparator());
		
		
	}
	
public static class SolutionComparator implements Comparator<Solution>{
		
		public int compare(Solution solution1, Solution solution2) {     
			
		return  Double.compare(solution1.getCloningFactor(), solution2.getCloningFactor());

		}
	}

	public void print() {
		
		for(Solution sol : population) {
			System.out.println(sol.getPossibleSolution() + " " + sol.getCloningFactor());
		}
	}
	
}
