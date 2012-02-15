package com.rickbutton.bfi.memory;

public interface Memory<T> {

	public T get(int loc);
	public void set(int loc, T b);
	public int getSize();
	
	public void dec(int loc);
	public void inc(int loc);
}
