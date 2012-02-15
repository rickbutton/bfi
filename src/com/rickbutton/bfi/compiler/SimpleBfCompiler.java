package com.rickbutton.bfi.compiler;

import java.util.ArrayList;
import java.util.List;

import com.rickbutton.bfi.inset.Instruction;

public class SimpleBfCompiler implements BfCompiler {

	@Override
	public Instruction[] compile(String code) {
		List<Instruction> list = new ArrayList<Instruction>();
		for (int i = 0; i < code.length(); i++) {
			char c = code.charAt(i);
			Instruction in = Instruction.getForChar(c);
			if (in != null)
				list.add(in);
		}
		return toArray(list);
	}
	
	private Instruction[] toArray(List<Instruction> list) {
		Instruction[] inset = new Instruction[list.size()];
		list.toArray(inset);
		return inset;
	}

}
