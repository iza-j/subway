package com.solvd.subway.domain.workers;

import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.domain.networkelements.Station;

import java.math.BigDecimal;

public class Worker {

	private Integer id;
	private String name;
	private BigDecimal hourlyWage;
	private Job job;
	private Line line;
	private Station station;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(BigDecimal hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
}