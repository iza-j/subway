package com.solvd.subway.networkelements;

import java.util.ArrayList;

public class Line {

	private String name;
	private ArrayList<RouteSection> routeSections;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<RouteSection> getRouteSections() {
		return routeSections;
	}

	public void setRouteSections(ArrayList<RouteSection> routeSections) {
		this.routeSections = routeSections;
	}
}
