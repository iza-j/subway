package com.solvd.subway.persistence;

import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.domain.networkelements.RouteSection;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface LineRepository {

	BigDecimal getBaseFare(String name);

	void create(@Param("line") Line line);

	void delete(Line line);

	List<RouteSection> getSections(@Param("lineName") String lineName);

	Line getByName(@Param("name") String name);

	List<Line> getAll();
}