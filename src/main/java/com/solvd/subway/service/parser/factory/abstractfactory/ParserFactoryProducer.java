package com.solvd.subway.service.parser.factory.abstractfactory;

import com.solvd.subway.service.parser.factory.JSONParserFactory;
import com.solvd.subway.service.parser.factory.ParserFactory;
import com.solvd.subway.service.parser.factory.XMLParserFactory;

public class ParserFactoryProducer {

	public static ParserFactory getFactory(String fileExtension) {
		ParserFactory factory;

		switch (fileExtension.toUpperCase()) {
			case "XML":
				factory = new XMLParserFactory();
				break;
			case "JSON":
				factory = new JSONParserFactory();
				break;
			default:
				throw new RuntimeException("Unable to find a parser factory for " + fileExtension);
		}

		return factory;
	}
}