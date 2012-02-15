package com.rickbutton.bfi.parser;

public class BangParser implements Parser {

	@Override
	public Code parse(String s) {
		int bang = s.indexOf('!');
		int ques = s.indexOf('?');
		if (bang != -1 && ques != -1)
			throw new IllegalArgumentException("Both ! and ? found for input, you must choose one");
		String code = null;
		String input = "";
		if (bang != -1) {
			code = s.substring(0, bang - 1);
			input = s.substring(bang + 1);
		} else if (ques != -1) {
			code = s.substring(0, ques - 1);
			int i = Integer.parseInt(s.substring(ques + 1));
			byte[] b = new byte[1];
			b[0] = (byte)i;
			input = new String(b);
		} else {
			code = s;
		}
		return new Code(code, input);
	}

}
