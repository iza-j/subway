package com.solvd.subway.service.parser.factory;

import com.solvd.subway.service.parser.JacksonParser;
import com.solvd.subway.service.parser.Parser;

public class JSONParserFactory implements ParserFactory {

	@Override
	public Parser createParser(String framework) {
		Parser parser;

		switch (framework.toUpperCase()) {
			case "JACKSON":
				parser = new JacksonParser();
				break;
			default:
				throw new RuntimeException("Unable to find a parser for framework: " + framework);
		}

		return parser;
	}
}