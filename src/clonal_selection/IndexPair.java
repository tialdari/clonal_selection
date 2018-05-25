package clonal_selection;


public class IndexPair {     // klasa, która przechowuje pary indeksów końców przedziału danego froagmentu listy
	
	private int firstIndex;
	private int secondIndex;
	
	
	public IndexPair(int firstIndex, int secondIndex) {
		this.firstIndex = firstIndex;
		this.secondIndex = secondIndex;
	}
	
	
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getSecondIndex() {
		return secondIndex;
	}
	public void setSecondIndex(int secondIndex) {
		this.secondIndex = secondIndex;
	}
	
	
}
