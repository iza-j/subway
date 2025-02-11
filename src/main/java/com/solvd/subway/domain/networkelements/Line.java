package com.solvd.subway.domain.networkelements;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "routeSections"})
public class Line {

	@XmlElement
	private String name;

	@XmlElementWrapper
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