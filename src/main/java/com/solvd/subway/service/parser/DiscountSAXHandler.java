package com.solvd.subway.service.parser;

import com.solvd.subway.domain.commuteresources.Discount;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;

public class DiscountSAXHandler extends DefaultHandler {

	private Discount discount;
	private StringBuilder elementValue;

	public Discount getResult() {
		return discount;
	}

	@Override
	public void startDocument() {
		discount = new Discount();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		switch (qName) {
			case "name":
				elementValue = new StringBuilder();
				break;
			case "reductionPercentage":
				elementValue = new StringBuilder();
				break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws IllegalStateException {
		switch (qName) {
			case "name":
				discount.setName(elementValue.toString());
				break;
			case "reductionPercentage":
				discount.setReductionPercentage(BigDecimal.valueOf(Long.parseLong(elementValue.toString())));
				break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) { // throws SAXException
		if (elementValue == null) {
			elementValue = new StringBuilder();
		} else {
			elementValue.append(ch, start, length);
		}
	}
}