package com.rickbutton.bfi.memory;

public interface Memory {

	public byte get(int loc);
	public void set(int loc, byte b);
	public int getSize();
	
	public void dec(int loc);
	public void inc(int loc);
}
