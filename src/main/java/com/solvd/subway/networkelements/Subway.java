package com.solvd.subway.networkelements;

import com.solvd.subway.commuteresources.Passenger;
import com.solvd.subway.workers.Worker;

import java.util.List;

public class Subway {

	private String name;
	private List<Passenger> passengers;
	private List<Line> lines;
	private List<Worker> workers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
}