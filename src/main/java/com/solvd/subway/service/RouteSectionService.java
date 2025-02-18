package com.solvd.subway.service;

import com.solvd.subway.domain.networkelements.RouteSection;

import java.util.List;

public interface RouteSectionService {

	List<RouteSection> getAll();

	void create(RouteSection routeSection);

	void updateTime(Integer routeSectionId, Integer minutes);

	RouteSection getById(Integer id);
}