package com.solvd.subway.service.impl;

import com.solvd.subway.domain.networkelements.Zone;
import com.solvd.subway.persistence.ZoneRepository;
import com.solvd.subway.persistence.impl2.ZoneRepositoryImpl2;
import com.solvd.subway.service.ZoneService;

import java.util.List;

public class ZoneServiceImpl implements ZoneService {

	private final ZoneRepository zoneRepository;

	public ZoneServiceImpl() {
		this.zoneRepository = new ZoneRepositoryImpl2();
	}

	@Override
	public void create(Zone zone) {
		zoneRepository.create(zone);
	}

	@Override
	public Zone getById(String name) {
		return zoneRepository.getById(name);
	}

	@Override
	public List<Zone> getAll() {
		return zoneRepository.getAll();
	}
}