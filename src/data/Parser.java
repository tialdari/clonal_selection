package data;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

//reads data from a file and puts them into an arrayList for further processing
public class Parser {
	  
		private String csvFileName;
		private int dataSize;
		private HashMap<String, HashMap<String, Integer>> frequencyRelations;
		private List<String> cabinets;
		
		public Parser() {
			csvFileName = "z5data.csv";
			dataSize = 0;
			frequencyRelations = new HashMap<String, HashMap<String, Integer>>();
		}
		
		public Parser(String csvFileName) {
			this.csvFileName = csvFileName;
			dataSize = 0;
			frequencyRelations = new HashMap<String, HashMap<String, Integer>>();
		}
		
	
		public int getDataSize() {
			return dataSize;
		}

		public void setDataSize(int dataSize) {
			this.dataSize = dataSize;
		}

		
		public List<String> getCabinets() {
			return cabinets;
		}

		public void setCabinets(List<String> cabinets) {
			this.cabinets = cabinets;
		}

		
		public HashMap<String, HashMap<String, Integer>> getFrequencyRelations() {
			return frequencyRelations;
		}

		public void setFrequencyRelations(HashMap<String, HashMap<String, Integer>> frequencyRelations) {
			this.frequencyRelations = frequencyRelations;
		}

		//reads numbers from the file omitting "x" and puts them into an arrayList
		public List<Integer> read() {
			
			List<Integer> data = new ArrayList<Integer>();
	       
	        File myCSVfile = new File(csvFileName);
	        String line = "";
	        String CSVsplitter = ",";

	        try (BufferedReader br = new BufferedReader(new FileReader(myCSVfile))) {


	            while ((line = br.readLine()) != null) {

	                String[] lineTokens = line.split(CSVsplitter);
	                for (String singleToken : lineTokens) {
	                		dataSize++;
		                	if(singleToken.equals("x")) {
		                		continue;
		                	}		                	
		                data.add(Integer.parseInt(singleToken));
	                }
	            }

	        } catch (IOException e) {
	           e.printStackTrace();
	        }
	        return data;
	}
		
		//creates both way frequency relations between cabinets and puts them into an arrayList
		public HashMap<String, HashMap<String, Integer>> createRelations(List<Integer> numbers, int size) {
			
			
			int lineLength = (int) Math.sqrt(size) - 1;
			
			//creates an array of size of a single line from input matrix(number of different cabinets)
			int omitCounter = 0;
			int counter1 = 0;
			int counter2 = 0;
			char leter1 =  98;
			char leter2 =  97;
			String letter1 = String.valueOf(leter1);
			String letter2 = String.valueOf(leter2);
			
			
			Iterator<Integer> iterator = numbers.iterator();
			int frequency = iterator.next();
			
			
			//change it for 2D array!
			while(iterator.hasNext()) {
				
				while(counter1 < lineLength) {
					add(frequencyRelations, letter1, letter2, frequency);
					counter1++;
					leter1++;
					letter1 = String.valueOf(leter1);
					frequency = iterator.next();
				}
				
				lineLength--;
				counter1 = 0;
				leter1 = (char) (98 + 1 + omitCounter);
				letter1 = String.valueOf(leter1);
				
				while(counter2 <= omitCounter) {
					if(!iterator.hasNext()) return frequencyRelations;
					frequency = iterator.next();
					counter2++;
				}
				omitCounter++;
				counter2 = 0;
				leter2++;
				letter2 = String.valueOf(leter2);				
			}
			
			
			return frequencyRelations;
			
		}
		
		
		public void add(HashMap<String, HashMap<String, Integer>> frequencyRelations, String letter1, String letter2, int frequency) {
			
			if(frequencyRelations.containsKey(letter1)) {
	    			frequencyRelations.get(letter1).put(letter2, frequency);
			}else {
				//if not, we put a new key(the letter) first
				frequencyRelations.put(letter1, new HashMap<String, Integer>());
				frequencyRelations.get(letter1).put(letter2, frequency);
			}
	    
	
			//since the relations work both ways, we repeat the action for the second letter
			if(frequencyRelations.containsKey(letter2)) {
				frequencyRelations.get(letter2).put(letter1, frequency);
			}else {
	
				frequencyRelations.put(letter2, new HashMap<String, Integer>());
				frequencyRelations.get(letter2).put(letter1, frequency);
			}
		}
		
		public void createCabinets() {
			
			List<String> demoCabinets = new ArrayList<String>();
			char cabinetLetter = 97;
			
			for(int i = 0; i < Math.sqrt(getDataSize()); i++) {
				demoCabinets.add(String.valueOf(cabinetLetter)); 
				cabinetLetter++;
			}
			
			setCabinets(demoCabinets);
		}

}
