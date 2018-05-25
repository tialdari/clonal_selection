package clonal_selection;

import java.util.List;

public class Main {

		public static void main(String [] args) {
			
			System.out.println("Clonal selection algorytm");
			Parser parser = new Parser("test.csv");
			List<FrequencyRelation> frequencyRelations = parser.createRelations(parser.read(), parser.getDataSize());
			parser.createCabinets();
			System.out.println("");

			for(int i = 0; i < parser.getCabinets().length; i++) {
				System.out.print(parser.getCabinets()[i]);
			}
		}
}
