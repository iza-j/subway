package com.solvd.subway.persistence;

import com.solvd.subway.domain.networkelements.Station;

import java.util.List;

public interface StationRepository {

	List<Station> getAll();

	void create(Station station);

	Station getById(Integer id);
}