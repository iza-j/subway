package com.solvd.subway.service.parser.factory;

import com.solvd.subway.service.parser.DiscountSAXParser;
import com.solvd.subway.service.parser.JAXBParser;
import com.solvd.subway.service.parser.Parser;

public class XMLParserFactory implements ParserFactory {

	@Override
	public Parser createParser(String framework) {
		Parser parser;

		switch (framework.toUpperCase()) {
			case "JAXB":
				parser = new JAXBParser();
				break;
			case "SAX":
				parser = new DiscountSAXParser();
				break;
			default:
				throw new RuntimeException("Unable to find a parser for framework: " + framework);
		}

		return parser;
	}
}