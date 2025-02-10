package com.solvd.subway.persistence;

import com.solvd.subway.domain.networkelements.RouteSection;

import java.util.List;

public interface RouteSectionRepository {

	List<RouteSection> getAll();

	void create(RouteSection routeSection);

	void updateTime(Integer routeSectionId, Integer minutes);
}