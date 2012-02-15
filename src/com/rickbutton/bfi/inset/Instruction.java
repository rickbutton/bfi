package com.rickbutton.bfi.inset;

public enum Instruction {

	INC_POINTER('>'),
	DEC_POINTER('<'),
	INC_VALUE('+'),
	DEC_VALUE('-'),
	OUTPUT('.'),
	INPUT(','),
	JUMP_FORWARD('['),
	JUMP_BACKWARD(']'),
	DEBUG('@');
	
	private char instruction;
	
	Instruction(char instruction) {
		this.instruction = instruction;
	}
	
	public char getInstruction() {
		return instruction;
	}
	
	public static Instruction getForChar(char c) {
		for (Instruction i : values())
			if (i.getInstruction() == c)
				return i;
		return null;
	}
}
