package com.rickbutton.bfi.interpreter;

import com.rickbutton.bfi.inset.Instruction;

public interface Interpreter<T> {

	public void interpret(Instruction[] inset, byte[] input);
	
}
