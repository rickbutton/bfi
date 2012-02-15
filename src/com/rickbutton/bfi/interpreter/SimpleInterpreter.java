package com.rickbutton.bfi.interpreter;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import com.rickbutton.bfi.inset.Instruction;
import com.rickbutton.bfi.memory.Memory;

public class SimpleInterpreter implements Interpreter<Long> {

	private InputStream in;
	private PrintStream out;
	private Memory<Long> mem;
	
	
	public SimpleInterpreter(InputStream in, PrintStream out, Memory<Long> mem) {
		this.in = in;
		this.out = out;
		this.mem = mem;
	}
	
	@Override
	public void interpret(Instruction[] inset, byte[] input) {
		Map<Integer, Integer> jumpMap = buildJumpMap(inset);
		Stack<Byte> inputStack = new Stack<Byte>();
		for (byte b : input)
			inputStack.push(b);
		/*for (Entry<Integer, Integer> e : jumpMap.entrySet())
			System.out.println(e.getKey() + ":" + e.getValue());
		*/
		int p = 0;
		
		for (int i = 0; i < inset.length; i++) {
			Instruction ins = inset[i];
			switch (ins) {
				case DEC_POINTER:
					p--;
					break;
				case DEC_VALUE:
					mem.dec(p);
					break;
				case INC_POINTER:
					p++;
					break;
				case INC_VALUE:
					mem.inc(p);
					break;
				case INPUT:
					if (!inputStack.isEmpty())
						mem.set(p, inputStack.pop().longValue());
					else
						mem.set(p, -1L);
					break;
				case JUMP_BACKWARD:
					if (mem.get(p) != 0)
						i = getKeyByValue(jumpMap, i);
					break;
				case JUMP_FORWARD:
					if (mem.get(p) == 0L)
						i = jumpMap.get(i);
					break;
				case OUTPUT:
					out.print((char)mem.get(p).longValue());
					break;
				case DEBUG:
					out.println("Pointer: " + p);
					out.println("Value: " + mem.get(p));
				
			}
			
			
			
		}
	}
	
	private Map<Integer, Integer> buildJumpMap(Instruction[] inset) {
		Map<Integer, Integer> jumpMap = new HashMap<Integer, Integer>();
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < inset.length; i++) {
			Instruction ist = inset[i];
			if (ist == Instruction.JUMP_FORWARD)
				s.push(i);
			else {
				if (ist == Instruction.JUMP_BACKWARD)
					if (s.isEmpty())
						throw new IllegalArgumentException("Extra ] at " + i);
					else
						jumpMap.put(s.pop(), i);
			}
		}
		if (!s.isEmpty())
			throw new IllegalArgumentException("Extra [");
		return jumpMap;
	}
	
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

}
