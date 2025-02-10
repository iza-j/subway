package com.solvd.subway.service;

import com.solvd.subway.domain.networkelements.Station;

import java.util.List;

public interface StationService {

	List<Station> getAll();

	void create(Station station);

	Station geById(Integer id);
}