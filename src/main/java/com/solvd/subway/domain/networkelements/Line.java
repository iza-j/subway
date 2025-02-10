package com.solvd.subway.domain.networkelements;

import java.util.List;

public class Line {

	private String name;
	private List<RouteSection> routeSections;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RouteSection> getRouteSections() {
		return routeSections;
	}

	public void setRouteSections(List<RouteSection> routeSections) {
		this.routeSections = routeSections;
	}
}