package com.solvd.subway.persistence;

import com.solvd.subway.domain.networkelements.Zone;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneRepository {

	void create(Zone zone);

	Zone getById(@Param("name") String name);

	List<Zone> getAll();
}