package com.solvd.subway.service.parser;

import com.solvd.subway.domain.commuteresources.Passenger;
import com.solvd.subway.domain.workers.Worker;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JAXBParser {

	public <T> void marshal(T myObject) throws JAXBException {
		String fileName = "src/main/resources/" + myObject.getClass().getSimpleName().toLowerCase() + ".xml";
		JAXBContext context = JAXBContext.newInstance(myObject.getClass());
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(myObject, new File(fileName));
	}

	public Worker unmarshalWorker() throws JAXBException, IOException {
		String fileName = "src/main/resources/worker.xml";
		JAXBContext context = JAXBContext.newInstance(Worker.class);
		Worker result = (Worker) context.createUnmarshaller().unmarshal(new FileReader(fileName));
		return result;
	}

	public Passenger unmarshalPassenger() throws JAXBException, IOException {
		String fileName = "src/main/resources/passenger.xml";
		JAXBContext context = JAXBContext.newInstance(Passenger.class);
		Passenger result = (Passenger) context.createUnmarshaller().unmarshal(new FileReader(fileName));
		return result;
	}
}