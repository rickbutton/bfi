package com.rickbutton.bfi.memory;

public class SimpleWrappingMemory implements Memory<Byte> {

	private byte[] mem;
	
	public SimpleWrappingMemory(int size) {
		this.mem = new byte[size];
	}
	
	@Override
	public Byte get(int loc) {
		loc = getTrueLoc(loc);
		return mem[loc];
	}

	@Override
	public void set(int loc, Byte b) {
		loc = getTrueLoc(loc);
		mem[loc] = b;
	}

	@Override
	public int getSize() {
		return mem.length;
	}

	@Override
	public void dec(int loc) {
		loc = getTrueLoc(loc);
		mem[loc]--;
	}

	@Override
	public void inc(int loc) {
		loc = getTrueLoc(loc);
		mem[loc]++;
	}
	
	private int getTrueLoc(int loc) {
		return loc;
	}

}
