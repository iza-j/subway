package com.solvd.subway.persistence;

import com.solvd.subway.domain.networkelements.Zone;

import java.util.List;

public interface ZoneRepository {

	void create(Zone zone);

	Zone getById(String name);

	List<Zone> getAll();
}