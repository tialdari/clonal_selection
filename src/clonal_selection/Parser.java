package clonal_selection;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//reads data from a file and puts them into an arrayList for further processing
public class Parser {
	  
		private String csvFileName;
		private int dataSize;
		
		public Parser() {
			csvFileName = "z5data.csv";
			dataSize = 0;
		}
		
		public Parser(String csvFileName) {
			this.csvFileName = csvFileName;
			dataSize = 0;
		}
		
	
		public int getDataSize() {
			return dataSize;
		}

		public void setDataSize(int dataSize) {
			this.dataSize = dataSize;
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
		public List<FrequencyRelation> createRelations(List<Integer> numbers, int size) {
			
			int lineLength = (int) Math.sqrt(size) - 1;
			int omitCounter = 0;
			char leter1 =  98;
			char leter2 =  97;
			String letter1 = String.valueOf(leter1);
			String letter2 = String.valueOf(leter2);


			List <FrequencyRelation> frequencyRelations = new ArrayList<FrequencyRelation>();
			
			for(Integer frequencyNumber : numbers) {
				while(numbers.indexOf(frequencyNumber) == omitCounter) {
					System.out.println("still in the loop");
					frequencyNumber = numbers.indexOf(frequencyNumber) + 1;
					continue;
				}
				frequencyRelations.add(new FrequencyRelation(letter1, letter2, frequencyNumber));
				frequencyRelations.add(new FrequencyRelation(letter2, letter1, frequencyNumber));
				
				System.out.print( frequencyNumber  + " ");
				leter1 ++;
				letter1 = String.valueOf(leter1);
				letter2 = String.valueOf(leter2);

				if(numbers.indexOf(frequencyNumber) == lineLength) {
					System.out.println("");
					omitCounter++;
					leter2++;
					leter1 = 0;
					lineLength = lineLength * 2 + 1;
				}
				
			}
			
			
			return new ArrayList<FrequencyRelation>();
			
		}

}
