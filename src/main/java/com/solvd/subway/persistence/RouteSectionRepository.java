package com.solvd.subway.persistence;

import com.solvd.subway.domain.networkelements.RouteSection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteSectionRepository {

	List<RouteSection> getAll();

	void create(RouteSection routeSection);

	void updateTime(@Param("routeSectionId") Integer routeSectionId, @Param("minutes") Integer minutes);

	RouteSection getById(Integer id);
}