package com.solvd.subway.domain.networkelements;

public class RouteSection {

	private Integer id;
	private Station departureStation;
	private Station destinationStation;
	private Integer minutes;
	private Zone zone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Station getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(Station departureStation) {
		this.departureStation = departureStation;
	}

	public Station getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(Station destinationStation) {
		this.destinationStation = destinationStation;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}
}