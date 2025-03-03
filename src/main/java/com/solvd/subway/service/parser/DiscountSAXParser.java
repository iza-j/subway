package com.solvd.subway.service.parser;

import com.solvd.subway.domain.commuteresources.Discount;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class DiscountSAXParser implements Parser {

	@Override
	public Discount unmarshalDiscount(String fileName) {
		Discount discount = new Discount();

		try {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			DiscountSAXHandler handler = new DiscountSAXHandler();
			saxParser.parse(fileName, handler);
			discount = handler.getResult();

		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Man, that really sax........." + e);
		}
		return discount;
	}
}