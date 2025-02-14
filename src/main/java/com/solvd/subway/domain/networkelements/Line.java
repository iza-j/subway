package com.solvd.subway.domain.networkelements;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "routeSections"})
@JsonRootName(value = "line")
public class Line {

	@XmlElement
	private String name;
	@XmlElementWrapper(name = "routeSections")
	@XmlElement(name = "routeSection")
	private List<RouteSection> routeSections;

	@JsonGetter
	public String getName() {
		return name;
	}

	@JsonSetter
	public void setName(String name) {
		this.name = name;
	}

	@JsonGetter
	public List<RouteSection> getRouteSections() {
		return routeSections;
	}

	@JsonSetter
	public void setRouteSections(List<RouteSection> routeSections) {
		this.routeSections = routeSections;
	}
}