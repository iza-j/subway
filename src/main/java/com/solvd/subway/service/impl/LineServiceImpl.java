package com.solvd.subway.service.impl;

import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.domain.networkelements.RouteSection;
import com.solvd.subway.persistence.LineRepository;
import com.solvd.subway.persistence.impl2.LineRepositoryImpl2;
import com.solvd.subway.service.LineService;
import com.solvd.subway.service.RouteSectionService;
import com.solvd.subway.service.StationService;
import com.solvd.subway.service.ZoneService;

import java.math.BigDecimal;
import java.util.List;

public class LineServiceImpl implements LineService {

	private final LineRepository lineRepository;

	public LineServiceImpl() {
		this.lineRepository = new LineRepositoryImpl2();
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

	@Override
	public void viewSections(String lineName) {
		LineService lineService = new LineServiceImpl();
		StationService stationService = new StationServiceImpl();
		RouteSectionService routeSectionService = new RouteSectionServiceImpl();
		ZoneService zoneService = new ZoneServiceImpl();
		System.out.println("Sections of line " + lineName + ":");

		lineRepository.getSections(lineName).forEach(i -> System.out.println(i.getId()));
		for (RouteSection section : lineRepository.getSections(lineName)) {
			System.out.print(new StringBuilder()
				.append(section.getDepartureStation() != null ? section.getDepartureStation().getName() : null)
				.append(" to ")
				.append(section.getDestinationStation() != null ? section.getDestinationStation().getName() : null)
				.append(". Time: ")
				.append(section.getMinutes())
				.append(" minutes. Base fare: ")
				.append(section.getZone() != null ? zoneService.getById(section.getZone().getName()) : null)
				.append(" pln.\n"));
		}
	}

	@Override
	public List<Line> getAll() {
		return lineRepository.getAll();
	}
}