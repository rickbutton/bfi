package com.rickbutton.bfi;

import java.io.BufferedReader;
import java.io.DataInputStream;
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

public class Main {

	public static void main(String[] args) {
		BfCompiler compiler = new SimpleBfCompiler();
		String fileName = args[0];
		String s;
		try {
			s = readFileAsString(fileName);
		} catch (IOException e) {
			System.out.println(fileName + " not found");
			return;
		}
		
		Parser parser = new BangParser();
		
		Code code = parser.parse(s);
		
		
		Instruction[] inset = compiler.compile(code.getCode());
		
		Memory<Long> mem = new LargeMemory(3000);
		Interpreter<Long> i = new SimpleInterpreter(System.in, System.out, mem);
		i.interpret(inset, code.getInput().getBytes());
	}
	
	private static String readFileAsString(String filePath) throws java.io.IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
}
