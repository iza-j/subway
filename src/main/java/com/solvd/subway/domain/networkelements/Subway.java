package com.solvd.subway.domain.networkelements;

import com.solvd.subway.domain.commuteresources.Passenger;
import com.solvd.subway.domain.workers.Worker;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "passengers", "lines", "workers"})
public class Subway {

	@XmlAttribute
	private String name;

	@XmlElementWrapper
	private List<Passenger> passengers;

	@XmlElementWrapper
	private List<Line> lines;

	@XmlElementWrapper
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