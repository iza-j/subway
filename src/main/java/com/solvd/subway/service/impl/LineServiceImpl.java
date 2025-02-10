package com.solvd.subway.service.impl;

import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.persistence.LineRepository;
import com.solvd.subway.persistence.impl.LineRepositoryImpl;
import com.solvd.subway.service.LineService;

import java.math.BigDecimal;

public class LineServiceImpl implements LineService {

	private final LineRepository lineRepository;

	public LineServiceImpl() {
		this.lineRepository = new LineRepositoryImpl();
	}

	@Override
	public BigDecimal getBaseFare(String name) {
		return lineRepository.getBaseFare(name);
	}

	@Override
	public void create(Line line) {
		lineRepository.create(line);
		System.out.println("Line " + line.getName() + " added to the database!");
	}

	@Override
	public void delete(Line line) {
		lineRepository.delete(line);
		System.out.println("Line " + line.getName() + " deleted from the database!");
	}
}