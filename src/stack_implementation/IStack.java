package stack_implementation;

import stack_implementation.EmptyStackException;


public interface IStack<T> {
	
	public boolean isEmpty();
	public void pop() throws EmptyStackException;
	public int size();
	public void push(T t);
	
}
