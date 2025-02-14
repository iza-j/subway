package com.solvd.subway.service;

import com.solvd.subway.domain.networkelements.Zone;

import java.util.List;

public interface ZoneService {

	void create(Zone zone);

	Zone getById(String name);

	List<Zone> getAll();
}