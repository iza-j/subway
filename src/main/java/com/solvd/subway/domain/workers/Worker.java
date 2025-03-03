package com.solvd.subway.domain.workers;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.domain.networkelements.Station;
import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "hourlyWage", "job", "line", "station"})
@JsonRootName(value = "worker")
public class Worker {

	@XmlElement
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private BigDecimal hourlyWage;
	@XmlElement
	private Job job;
	@XmlElement
	private Line line;
	@XmlElement
	private Station station;

	public static Builder builder() {
		return new Worker.Builder(new Worker());
	}

	@JsonGetter
	public Integer getId() {
		return id;
	}

	@JsonSetter
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonGetter
	public String getName() {
		return name;
	}

	@JsonSetter
	public void setName(String name) {
		this.name = name;
	}

	@JsonGetter
	public BigDecimal getHourlyWage() {
		return hourlyWage;
	}

	@JsonSetter
	public void setHourlyWage(BigDecimal hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	@JsonGetter
	public Job getJob() {
		return job;
	}

	@JsonSetter
	public void setJob(Job job) {
		this.job = job;
	}

	@JsonGetter
	public Line getLine() {
		return line;
	}

	@JsonSetter
	public void setLine(Line line) {
		this.line = line;
	}

	@JsonGetter
	public Station getStation() {
		return station;
	}

	@JsonSetter
	public void setStation(Station station) {
		this.station = station;
	}

	public static class Builder {

		private final Worker worker;

		public Builder(Worker worker) {
			this.worker = worker;
		}

		public Worker.Builder name(String name) {
			this.worker.name = name;
			return this;
		}

		public Worker.Builder hourlyWage(BigDecimal hourlyWage) {
			this.worker.hourlyWage = hourlyWage;
			return this;
		}

		public Worker.Builder job(Job job) {
			this.worker.job = job;
			return this;
		}

		public Worker.Builder line(Line line) {
			this.worker.line = line;
			return this;
		}

		public Worker.Builder station(Station station) {
			this.worker.station = station;
			return this;
		}

		public Worker build() {
			return worker;
		}
	}
}