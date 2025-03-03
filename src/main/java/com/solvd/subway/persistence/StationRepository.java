package com.solvd.subway.persistence;

import com.solvd.subway.domain.networkelements.Station;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StationRepository {

	List<Station> getAll();

	void create(@Param("station") Station station);

	Station getById(@Param("id") Integer id);
}