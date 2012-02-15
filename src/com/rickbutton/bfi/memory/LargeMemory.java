package com.rickbutton.bfi.memory;

public class LargeMemory implements Memory<Long> {

	private long[] mem;
	
	public LargeMemory(int size) {
		this.mem = new long[size];
	}
	
	@Override
	public Long get(int loc) {
		loc = getTrueLoc(loc);
		return mem[loc];
	}

	@Override
	public void set(int loc, Long b) {
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
