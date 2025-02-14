package com.solvd.subway.domain.networkelements;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "departureStation", "destinationStation", "minutes", "zone"})
@JsonRootName(value = "route_section")
public class RouteSection {

	@XmlElement
	private Integer id;
	@XmlElement
	private Station departureStation;
	@XmlElement
	private Station destinationStation;
	@XmlElement
	private Integer minutes;
	@XmlElement
	private Zone zone;

	@JsonGetter
	public Integer getId() {
		return id;
	}

	@JsonSetter
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonGetter
	public Station getDepartureStation() {
		return departureStation;
	}

	@JsonSetter
	public void setDepartureStation(Station departureStation) {
		this.departureStation = departureStation;
	}

	@JsonGetter
	public Station getDestinationStation() {
		return destinationStation;
	}

	@JsonSetter
	public void setDestinationStation(Station destinationStation) {
		this.destinationStation = destinationStation;
	}

	@JsonGetter
	public Integer getMinutes() {
		return minutes;
	}

	@JsonSetter
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	@JsonGetter
	public Zone getZone() {
		return zone;
	}

	@JsonSetter
	public void setZone(Zone zone) {
		this.zone = zone;
	}
}