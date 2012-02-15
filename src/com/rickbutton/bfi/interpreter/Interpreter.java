package com.rickbutton.bfi.interpreter;

import com.rickbutton.bfi.inset.Instruction;

public interface Interpreter {

	public void interpret(Instruction[] inset, byte[] input);
	
}
