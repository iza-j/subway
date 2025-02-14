package com.solvd.subway.domain.networkelements;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.solvd.subway.domain.commuteresources.Passenger;
import com.solvd.subway.domain.workers.Worker;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "passengers", "lines", "workers"})
@JsonRootName(value = "subway")
public class Subway {

	@XmlElement
	private String name;
	@XmlElementWrapper(name = "passengers")
	@XmlElement(name = "passenger")
	private List<Passenger> passengers;
	@XmlElementWrapper(name = "lines")
	@XmlElement(name = "line")
	private List<Line> lines;
	@XmlElementWrapper(name = "workers")
	@XmlElement(name = "worker")
	private List<Worker> workers;

	@JsonGetter
	public String getName() {
		return name;
	}

	@JsonSetter
	public void setName(String name) {
		this.name = name;
	}

	@JsonGetter
	public List<Passenger> getPassengers() {
		return passengers;
	}

	@JsonSetter
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	@JsonGetter
	public List<Line> getLines() {
		return lines;
	}

	@JsonSetter
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	@JsonGetter
	public List<Worker> getWorkers() {
		return workers;
	}

	@JsonSetter
	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
}