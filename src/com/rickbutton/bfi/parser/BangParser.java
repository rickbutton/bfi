package com.rickbutton.bfi.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.rickbutton.bfi.parser.exception.ParseException;

public class BangParser implements Parser {

	@Override
	public Code parse(File file, String inputOverride) throws ParseException {
		String code = "";
		String input = "";
		
		String s;
		try {
			s = readFileAsString(file);
		} catch (IOException e) {
			throw new ParseException(e);
		}
		int bang = s.indexOf('!');
		int ques = s.indexOf('?');
		if (bang != -1 && ques != -1)
			throw new ParseException("! and ? cannot both be present in file");
		if (inputOverride == null || inputOverride.trim().equals("")) {

			if (bang != -1) {
				code = s.substring(0, bang - 1);
				input = s.substring(bang + 1);
			} else if (ques != -1) {
				code = s.substring(0, ques - 1);
				byte[] b = { (byte)Integer.parseInt(s.substring(ques + 1)) };
				input = new String(b);
			}
		} else {
			if (inputOverride.indexOf('!') != -1) {
				code = s.substring(0, bang - 1);
				input = inputOverride.substring(1);
			}
			else if (inputOverride.indexOf('?') != -1) {
				code = s.substring(0, ques - 1);
				byte[] b = { (byte)Integer.parseInt(inputOverride.substring(1)) };
				input = new String(b);
			} else
				throw new ParseException("If you override the input, you must specify ! or ?");
		}
		return new Code(code, input);
	}
	
	private static String readFileAsString(File file) throws IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(file));
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
