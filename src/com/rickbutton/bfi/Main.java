package com.rickbutton.bfi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.rickbutton.bfi.compiler.BfCompiler;
import com.rickbutton.bfi.compiler.SimpleBfCompiler;
import com.rickbutton.bfi.inset.Instruction;
import com.rickbutton.bfi.interpreter.Interpreter;
import com.rickbutton.bfi.interpreter.SimpleInterpreter;
import com.rickbutton.bfi.memory.LargeMemory;
import com.rickbutton.bfi.memory.Memory;
import com.rickbutton.bfi.memory.SimpleWrappingMemory;
import com.rickbutton.bfi.parser.BangParser;
import com.rickbutton.bfi.parser.Code;
import com.rickbutton.bfi.parser.Parser;
import com.rickbutton.bfi.parser.exception.ParseException;

public class Main {

	public static void main(String[] args) {
		BfCompiler compiler = new SimpleBfCompiler();
		if (args.length == 0) {
			System.out.println("Usage: java -jar bfi.jar [filename] <input>");
			return;
		}
		File file = new File(args[0]);
		
		String inputOverride = null;
		if (args.length >= 2)
			inputOverride = args[1];
		
		Parser parser = new BangParser();
		
		Code code;
		try {
			code = parser.parse(file, inputOverride);
		} catch (ParseException e) {
			System.out.println("Parse error: " + e.getMessage());
			return;
		}
		
		
		Instruction[] inset = compiler.compile(code.getCode());
		
		Memory<Long> mem = new LargeMemory(3000);
		Interpreter<Long> i = new SimpleInterpreter(System.in, System.out, mem);
		i.interpret(inset, code.getInput().getBytes());
	}
	
	
}
