package com.solvd.subway.service.impl;

import com.solvd.subway.domain.networkelements.Station;
import com.solvd.subway.persistence.StationRepository;
import com.solvd.subway.persistence.impl2.StationRepositoryImpl2;
import com.solvd.subway.service.StationService;

import java.util.List;

public class StationServiceImpl implements StationService {

	private final StationRepository stationRepository;

	public StationServiceImpl() {
		this.stationRepository = new StationRepositoryImpl2();
	}

	@Override
	public List<Station> getAll() {
		return stationRepository.getAll();
	}

	@Override
	public void create(Station s) {
		stationRepository.create(s);
		System.out.println("Station \'" + s.getName() + "\' added to the database! ID: " + s.getId());
	}

	@Override
	public Station getById(Integer id) {
		return stationRepository.getById(id);
	}
}