package com.solvd.subway.service.parser;

import com.solvd.subway.domain.commuteresources.Discount;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface Parser {

	public Discount unmarshalDiscount(String fileName) throws JAXBException, IOException;
}