package com.solvd.subway.service.impl;

import com.solvd.subway.domain.networkelements.RouteSection;
import com.solvd.subway.persistence.RouteSectionRepository;
import com.solvd.subway.persistence.impl.RouteSectionRepositoryImpl;
import com.solvd.subway.service.RouteSectionService;

import java.util.List;

public class RouteSectionServiceImpl implements RouteSectionService {

	private final RouteSectionRepository routeSectionRepository;

	public RouteSectionServiceImpl() {
		this.routeSectionRepository = new RouteSectionRepositoryImpl();
	}

	public static void printDetails(RouteSection r) {
		System.out.println(r.getId() + " --- " + r.getDepartureStation().getName() + " --- " + r.getDestinationStation().getName() + " --- " + r.getMinutes() + " --- " + (r.getZone() != null ? r.getZone().getName() : null));
	}

	@Override
	public List<RouteSection> getAll() {
		return routeSectionRepository.getAll();
	}

	@Override
	public void create(RouteSection routeSection) {
		routeSectionRepository.create(routeSection);
		System.out.println("New route section created!");
	}

	@Override
	public void updateTime(Integer routeSectionId, Integer minutes) {
		routeSectionRepository.updateTime(routeSectionId, minutes);
		System.out.println("Time updated!");
	}
}