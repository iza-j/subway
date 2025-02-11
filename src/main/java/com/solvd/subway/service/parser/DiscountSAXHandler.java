package com.solvd.subway.service.parser;

import com.solvd.subway.domain.commuteresources.Discount;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;

public class DiscountSAXHandler extends DefaultHandler {

	private Discount discount;

	public Discount getResult() {
		return discount;
	}

	@Override
	public void startDocument() {
		discount = new Discount();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {

		if (localName.equalsIgnoreCase("name")) {
			discount.setName(attributes.getValue("name"));
		}

		if (localName.equalsIgnoreCase("reductionPercentage")) {
			BigDecimal reductionPercentage = BigDecimal.valueOf(Long.parseLong(attributes.getValue("reductionPercentage")));
			discount.setReductionPercentage(reductionPercentage);
		}
	}
}