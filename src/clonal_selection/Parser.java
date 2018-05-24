package clonal_selection;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//reads data from a file and puts them into data structures for further processing
public class Parser {
	

	  
		private String csvFileName;
		private List<Integer> fileData;
		
		public Parser() {
			csvFileName = "z5data.csv";
			fileData = new ArrayList<Integer>();
		}
		
		public Parser(String csvFileName) {
			this.csvFileName = csvFileName;
			fileData = new ArrayList<Integer>();
		}
		
		
	    public List<Integer> getFileData() {
			return fileData;
		}

		public void setFileData(List<Integer> fileData) {
			this.fileData = fileData;
		}

		public void read() {
	       
	        File myCSVfile = new File(csvFileName);
	        String line = "";
	        String CSVsplitter = ",";

	        try (BufferedReader br = new BufferedReader(new FileReader(myCSVfile))) {

	            while ((line = br.readLine()) != null) {

	                String[] lineTokens = line.split(CSVsplitter);
	                for (String singleToken : lineTokens) {
	                  fileData.add(Integer.parseInt(singleToken));
	                }
	         
	            }

	        } catch (IOException e) {
	           e.printStackTrace();
	        }
	}
		
		public createRelations() {
			
		}

}
