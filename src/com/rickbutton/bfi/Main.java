package com.rickbutton.bfi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.rickbutton.bfi.compiler.BfCompiler;
import com.rickbutton.bfi.compiler.SimpleBfCompiler;
import com.rickbutton.bfi.inset.Instruction;
import com.rickbutton.bfi.interpreter.Interpreter;
import com.rickbutton.bfi.interpreter.SimpleInterpreter;
import com.rickbutton.bfi.memory.Memory;
import com.rickbutton.bfi.memory.SimpleMemory;
import com.rickbutton.bfi.parser.BangParser;
import com.rickbutton.bfi.parser.Code;
import com.rickbutton.bfi.parser.Parser;

public class Main {

	public static void main(String[] args) {
		BfCompiler compiler = new SimpleBfCompiler();
		String fileName = args[0];
		String s = readFile(fileName);
		
		Parser parser = new BangParser();
		
		Code code = parser.parse(s);
		
		Instruction[] inset = compiler.compile(code.getCode());
		
		Memory mem = new SimpleMemory(64);
		Interpreter i = new SimpleInterpreter(System.in, System.out, mem);
		i.interpret(inset, code.getInput().getBytes());
	}
	
	private static String readFile(String fileName) {
		try{
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream = new FileInputStream(fileName);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String f = "";
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				f += strLine + "\n";
			}
			//Close the input stream
			in.close();
			return f;
	    }catch (Exception e){//Catch exception if any
	    	throw new IllegalArgumentException("File not found");
		}
	}
}
