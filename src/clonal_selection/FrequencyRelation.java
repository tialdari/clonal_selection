package clonal_selection;

public class FrequencyRelation {
	
	private String firstCabinet;
	private String secondCabinet;
	private int frequency;
	
	public FrequencyRelation() {
		firstCabinet = "a";
		secondCabinet = "b";
		frequency = 3;
	}
	
	public FrequencyRelation(String firstCabinet, String secondCabinet, int frequency) {
		this.firstCabinet = firstCabinet;
		this.secondCabinet = secondCabinet;
		this.frequency = frequency;
	}

	public String getFirstCabinet() {
		return firstCabinet;
	}

	public void setFirstCabinet(String firstCabinet) {
		this.firstCabinet = firstCabinet;
	}

	public String getSecondCabinet() {
		return secondCabinet;
	}

	public void setSecondCabinet(String secondCabinet) {
		this.secondCabinet = secondCabinet;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	
}
