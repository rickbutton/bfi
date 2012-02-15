package com.rickbutton.bfi.memory;

public class SimpleMemory implements Memory {

	private byte[] mem;
	
	public SimpleMemory(int size) {
		this.mem = new byte[size];
	}
	
	@Override
	public byte get(int loc) {
		if (loc >= mem.length)
			throw new IllegalArgumentException("Location out of bounds: " + loc);
		return mem[loc];
	}

	@Override
	public void set(int loc, byte b) {
		if (loc >= mem.length)
			throw new IllegalArgumentException("Location out of bounds: " + loc);
		mem[loc] = b;
	}

	@Override
	public int getSize() {
		return mem.length;
	}

	@Override
	public void dec(int loc) {
		if (loc >= mem.length)
			throw new IllegalArgumentException("Location out of bounds: " + loc);
		mem[loc]--;
	}

	@Override
	public void inc(int loc) {
		if (loc >= mem.length)
			throw new IllegalArgumentException("Location out of bounds: " + loc);
		mem[loc]++;
	}

}
