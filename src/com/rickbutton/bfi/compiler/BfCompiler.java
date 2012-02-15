package com.rickbutton.bfi.compiler;

import com.rickbutton.bfi.inset.Instruction;

public interface BfCompiler {

	public Instruction[] compile(String code);
}
