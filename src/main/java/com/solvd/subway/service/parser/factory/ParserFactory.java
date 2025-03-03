package com.solvd.subway.service.parser.factory;

import com.solvd.subway.service.parser.Parser;

public interface ParserFactory {

	Parser createParser(String framework);
}