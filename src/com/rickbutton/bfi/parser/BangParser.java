package com.rickbutton.bfi.parser;

public class BangParser implements Parser {

	@Override
	public Code parse(String s) {
		int bang = s.indexOf('!');
		if (bang == -1)
			new Code(s, "");
		String code = s.substring(0, bang);
		String input = s.substring(bang + 1);
		while (input.startsWith("\n"))
			input = input.substring(1);
		return new Code(code, input);
	}

}
