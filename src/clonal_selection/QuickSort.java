package clonal_selection;

import java.util.ArrayList;
import stack_implementation.MyStack;
import stack_implementation.EmptyStackException;
import java.util.Random;

public class QuickSort { //
	
	private MyStack<IndexPair> stack;
	private int swapNumber;
	
	public QuickSort() {
		stack = new MyStack<IndexPair>();
		swapNumber = 0;
	}
	
	public QuickSort(MyStack<IndexPair> stack, int swapNumber) {
		stack = new MyStack<IndexPair>();
		swapNumber = 0;
	}
	
	
	public int getSwapNumber() {
		return swapNumber;
	}
	
	public void setSwapNumber(int swapNumber) {
		this.swapNumber = swapNumber;
	}


	public MyStack<IndexPair> getStack() {
		return stack;
	}

	public void setStack(MyStack<IndexPair> stack) {
		this.stack = stack;
	}

	public void swap(ArrayList<Integer> toBeSorted, int firstIndex, int secondIndex) { //metoda zamieniająca liczby na podanych indeksach miejscami
		
		swapNumber++;
		int temporary = toBeSorted.get(firstIndex);
		toBeSorted.set(firstIndex, toBeSorted.get(secondIndex));
		toBeSorted.set(secondIndex, temporary);
	}
	
	public int partition(ArrayList<Integer> toBeSorted, int start, int end, String pivotChoice) { 
		//metoda ustawiająca wszystkie liczb mniejsze od pivota na lewo i większe na prawo i zwracająca jego indeks
		
		
		int pivot;
		int temporary;
		
		//dla danego przedziału ArrayListy wyznaczamy pivota wg danego kryterium 
		//na początku przenosimy pivot na koniec listy (w przypadku wyboru pivota jako ostatni element dzieje się to z definicji
		
		if(pivotChoice == "last") {					
			pivot = toBeSorted.get(end);				
	 
		}else if(pivotChoice == "random") {
		
			Random rdm = new Random();
			int random = start - 1;
			while(random < start) {
				random = rdm.nextInt(end);
			}
			pivot = toBeSorted.get(random);
			
			temporary = toBeSorted.get(end);
			toBeSorted.set(end, pivot);
			toBeSorted.set(random, temporary);
			
		
		}else if(pivotChoice == "median"){
		
			pivot = pivotMedian(toBeSorted, start, end);
			
			temporary = toBeSorted.get(end);
			toBeSorted.set(end, pivot);
			toBeSorted.set(toBeSorted.indexOf(pivot), temporary);
		}else {
			pivot = 0;
		}
		
		
		//przechodzimy przez dany fragment listy ustawiając liczby mniejsze lub równe od pivota na lewo od niego
		
		int pivotIndex = start;
		
		for (int i = start; i < end; i ++) {
			
			if(toBeSorted.get(i) <= pivot) {
				swap(toBeSorted, i, pivotIndex);				
				pivotIndex++;
			}
		}
		
		swap(toBeSorted, pivotIndex, end);
		
		return pivotIndex;
	}
	
	public void sorting(ArrayList<Integer> toBeSorted, String pivotChoice) throws EmptyStackException {
		
		//na początku na wierzch stosu odkładamy parę indeksów początkowego i końcowego, 
		//później indeksy fragmentów na lewo i prawo of pivota itd.
		//zawsze zdejmujemy ostatnio odłożoną parę i dzielimy na kolejne fragmenty
		
		stack = new MyStack<IndexPair>();
		
		stack.push(new IndexPair(0, toBeSorted.size() - 1));
		stack.setMaxHeight(1);
		
		
		while(!stack.isEmpty()) {
			
			int start = stack.peek().getFirstIndex();
			int end = stack.peek().getSecondIndex();
			
			if(stack.size() > stack.getMaxHeight()) {
				stack.setMaxHeight(stack.size());
			}

			stack.pop();
			
			int pivotIndex = partition(toBeSorted, start, end, pivotChoice);
		
			if(start < pivotIndex - 1) {
				stack.push(new IndexPair(start, pivotIndex - 1));
			}
			
			if(end > pivotIndex + 1) {
				stack.push(new IndexPair(pivotIndex + 1, end));
			}
		}
		
	}
	
	public int pivotMedian(ArrayList<Integer> toBeSorted, int lowIndex, int highIndex) {		//wylicza pivota będącego medianą liczb: 
														//pierwszej, ostatniej i środkowej z danego przedziału ArrayListy	
		int pivotMedian = 0;
		int firstNumber = toBeSorted.get(lowIndex);
		int median = toBeSorted.get((int) Math.floor((lowIndex + highIndex) / 2));
		int lastNumber = toBeSorted.get(highIndex);
		
		double average = (firstNumber + lastNumber + median) / 3;					//średnia arytmetyczna wskazanych liczb
		
		int [] abs = new int[3];
		abs[0] = Math.abs((int)average - firstNumber);		//obliczanie wartości bezwzględnej różnicy pomiędzy każdą z liczb, 
		abs[1] = Math.abs((int)average - lastNumber);			//a średnią arytmetyczną
		
		abs[2] = Math.abs((int)average - median);
		
		
		int min = abs[0];
		
		for(int k = 0; k < 3; k++) {				//liczba, dla wyliczona wartość bezwzględna jest najmniejsza, będzie szukanych pivotem
			if(abs[k] < min) {					
			
			min = abs[k];
			}
		}
		
		if(min == abs[0]) {
			pivotMedian = firstNumber;
		}else if (min == abs[1]) {
			pivotMedian = lastNumber;
		}else if(min == abs[2]) {
			pivotMedian = median;
		}	
		return pivotMedian;
	}
	
	

	
}