package clonal_selection;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

//class that creates a population of solutions
public class Population {
			
	private ArrayList<ArrayList<String>> population;
	private Functions functions;
	private HashMap<String, HashMap<String, Integer>> relations;
	
	
	public Functions getFunctions() {
		return functions;
	}

	public void setFunctions(Functions functions) {
		this.functions = functions;
	}
		
	public ArrayList<ArrayList<String>> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<ArrayList<String>> population) {
		this.population = population;
	}

	public HashMap<String, HashMap<String, Integer>> getRelations() {
		return relations;
	}

	public void setFrequencyRelations(HashMap<String, HashMap<String, Integer>> relations) {
		this.relations = relations;
	}
	
	public Population(HashMap<String, HashMap<String, Integer>> relations ) {
		population = new ArrayList<ArrayList<String>>();
		this.relations = relations;
		functions = new Functions(relations);

		
	}

	public void createPopulation(List<String> cabinetArrangement, int populationSize){
		
		int size = 0;

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
		
	}
	
	//counts the average of results of all the solutions
	public int countAveragePath() {
		
		int summedPath = 0;
		int averagePath = 0;

		for(ArrayList<String> possibleSolution : population) {

			summedPath += functions.countPath(possibleSolution);
		}
		averagePath = summedPath / population.size();
		
		return averagePath;
	}
		
		
	
}
