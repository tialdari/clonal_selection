package stack_implementation;

import java.util.ArrayList;
import stack_implementation.EmptyStackException;

public class MyStack<T> implements IStack<T>{  //modified class name due to the existence of a Stack<T> class in java API already
	
	private ArrayList<T> stack;
	private int maxHeight;
	
	
	public MyStack() {
		stack = new ArrayList<T>();
		maxHeight = 0;
	}

	
	public ArrayList<T> getStack() {
		return stack;
	}


	public void setStack(ArrayList<T> stack) {
		this.stack = stack;
	}
	
	

	public int getMaxHeight() {
		return maxHeight;
	}


	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}


	public boolean isEmpty() {
		if(stack.size() == 0) return true;
		else return false;
	}
	
			
	public T peek() throws EmptyStackException{
		
		int lastIndex = stack.size() - 1;
		
		if(!isEmpty()) return stack.get(lastIndex);
		else throw new EmptyStackException();
	}
	
	public void pop() throws EmptyStackException{
		
		if(!isEmpty()) stack.remove(size() - 1);
		else throw new EmptyStackException();
	}
	
	public void push(T t) {
		stack.add(t);
		
	}
	
	public int size() {
		return stack.size();
	}
	
	
	
	
}