package com.solvd.subway.service.parser;

import com.solvd.subway.domain.commuteresources.Discount;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class DiscountSAXParser {

	public static Discount parseDiscount() {
		Discount discount = new Discount();

		try {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			DiscountSAXHandler handler = new DiscountSAXHandler();
			saxParser.parse("src/main/resources/xml/discount.xml", handler);
			discount = handler.getResult();

		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Man, that really sax........." + e);
		}
		return discount;
	}
}