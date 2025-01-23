package com.solvd.subway.workers;

import com.solvd.subway.networkelements.Station;

public class StationWorker extends Worker {

	private Station station;

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
}
