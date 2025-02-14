package com.solvd.subway.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.subway.domain.networkelements.Subway;
import com.solvd.subway.domain.workers.Worker;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JacksonParser {

	public <T> void marshal(T someObject) throws IOException {
		String fileName = "src/main/resources/json/" + someObject.getClass().getSimpleName().toLowerCase() + ".json";
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), someObject);
	}

	public Worker unmarshalWorker() throws IOException {
		String fileName = "src/main/resources/json/worker.json";
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new FileReader(fileName), Worker.class);
	}

	public Subway unmarshalSubway(String fileName) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new FileReader(fileName), Subway.class);
	}
}