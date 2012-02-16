package com.rickbutton.bfi.parser;

import java.io.File;
import java.io.IOException;

import com.rickbutton.bfi.parser.exception.ParseException;

public interface Parser {

	public Code parse(File file, String inputOverride) throws ParseException;
}
