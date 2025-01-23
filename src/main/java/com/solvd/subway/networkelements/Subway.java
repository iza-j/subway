package com.solvd.subway.networkelements;

import com.solvd.subway.commuteresources.*;
import com.solvd.subway.workers.*;

import java.util.ArrayList;

public class Subway {

	// commute resources
	private ArrayList<Discount> discounts;
	private ArrayList<Passenger> passengers;
	private ArrayList<TransitPass> transitPasses;
	// network elements
	private ArrayList<Line> lines;
	private ArrayList<RouteSection> routeSections;
	private ArrayList<Station> stations;
	private ArrayList<Zone> zones;
	// workers
	private ArrayList<Worker> workers;

	
	public ArrayList<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(ArrayList<Discount> discounts) {
		this.discounts = discounts;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public ArrayList<TransitPass> getTransitPasses() {
		return transitPasses;
	}

	public void setTransitPasses(ArrayList<TransitPass> transitPasses) {
		this.transitPasses = transitPasses;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}

	public ArrayList<RouteSection> getRouteSections() {
		return routeSections;
	}

	public void setRouteSections(ArrayList<RouteSection> routeSections) {
		this.routeSections = routeSections;
	}

	public ArrayList<Station> getStations() {
		return stations;
	}

	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}

	public ArrayList<Zone> getZones() {
		return zones;
	}

	public void setZones(ArrayList<Zone> zones) {
		this.zones = zones;
	}

	public ArrayList<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(ArrayList<Worker> workers) {
		this.workers = workers;
	}
}
