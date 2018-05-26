package clonal_selection;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

//solution class that contains: 
//set of cabinets to create a possible cabinet arrangement from
//a possible cabinet arrangement,
//value of the arrangement (path),
//cloning factor needed to determine whether to include or exclude
//the solution in next populations
public class Solution {
	
	private HashMap<String, HashMap<String, Integer>> frequencyRelations;
	private double cloningFactor;
	private ArrayList<String> possibleSolution;
	private List<String> cabinets;
	private int path;
	
	public Solution() {
		frequencyRelations = new HashMap<String, HashMap<String, Integer>>();
		cloningFactor = 0.0;
		possibleSolution = new ArrayList<String>();
		cabinets = new ArrayList<String>();
		path = 0;
	
	}
	

	public Solution (HashMap<String, HashMap<String, Integer>> frequencyRelations, List<String> cabinets) {
		this.frequencyRelations = frequencyRelations;
		this.cabinets = cabinets;
		cloningFactor = 0;
		possibleSolution = new ArrayList<String>();
		path = 0;
	}
	
	

	public HashMap<String, HashMap<String, Integer>> getFrequencyRelations() {
		return frequencyRelations;
	}


	public void setFrequencyRelations(HashMap<String, HashMap<String, Integer>> frequencyRelations) {
		this.frequencyRelations = frequencyRelations;
	}


	public double getCloningFactor() {
		return cloningFactor;
	}

	public void setCloningFactor(double cloningFactor) {
		this.cloningFactor = cloningFactor;
	}
	

	public ArrayList<String> getPossibleSolution() {
		return possibleSolution;
	}


	public void setPossibleSolution(ArrayList<String> possibleSolution) {
		this.possibleSolution = possibleSolution;
	}
	

	public List<String> getCabinets() {
		return cabinets;
	}


	public void setCabinets(List<String> cabinets) {
		this.cabinets = cabinets;
	}


	public int getPath() {
		return path;
	}


	public void setPath(int path) {
		this.path = path;
	}
	
	


	//creates a random cabinet arrangement
	//abc and cba cases are counted as two or 1?
	//as the argument the method gets a copy of the original cabinet list
	//randomArrangement passed as the argument is an empty list
	public ArrayList<String> cabinetArrangement(List<String> cabinets, ArrayList<String> randomArrangement) {
		
		//if all letters are moved to the new list, leave the method
		if(cabinets.size() == 0) {
			setPossibleSolution(randomArrangement);
			return possibleSolution;
		}
		
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
	public int countPath(List<String> solution) {
		
		int path = 0;
		int distance = 1;
		int cabinet1Index = 0;
		int cabinet2Index = 1;
		String letter1;
		String letter2;
		int frequency;
		int currentPath = 0;

		//first we count the path between first cabinet and all the other, 
		//then the second and all the others minus the first one
		//then between the third one and all the others
		//and so on
		while(cabinet1Index <= solution.size() - 2) {
			
			letter1 = solution.get(cabinet1Index);
			letter2 = solution.get(cabinet2Index);
			//frequency is obtained from the hashmap that contains all the frequencies between pairs of cabinets
			//this hashmap is created in the parser class
			frequency = frequencyRelations.get(letter1).get(letter2).intValue();
			
			//current path is only to check if the calculations are correct
			currentPath = distance * frequency;
			path += distance * frequency;
		//	System.out.println(letter1 + letter2 + " current Path: " + currentPath);
			cabinet2Index++;
			distance++;
			
			//when the second index goes to the end of the line
			//we increment the first index and resent the second one
			if(cabinet2Index == solution.size()) {
				cabinet1Index++;
				cabinet2Index = cabinet1Index + 1;
				distance = 1;
			}
		}
		
		setPath(path);
		return path;
	}
	

	
	//mutates a solution by exchanging places of two cabinets
	//in order to get a better solution
	public ArrayList<String> mutate() {
		
		//mutation is done on the copy of the possible solution
		//from the given solution class
		ArrayList<String> mutation = new ArrayList<String>(possibleSolution);
		
		//we choose random indexes
		Random r = new Random();
		
		int index1 = r.nextInt(mutation.size() - 1);
		int index2 = r.nextInt(mutation.size() - 1);
		
		//and ensure they're not the same index
		while(index1 == index2) {
			index2 =  r.nextInt(mutation.size() - 1);
		}
		
		//switch the places
		String cabinet1 = mutation.get(index1);
		String cabinet2 = mutation.get(index2);
		String temporary = cabinet1;
		
		mutation.set(index1, cabinet2);
		mutation.set(index2, temporary);
		
		return mutation;

	}
	
	
}
