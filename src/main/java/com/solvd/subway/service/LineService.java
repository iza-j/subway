package com.solvd.subway.service;

import com.solvd.subway.domain.networkelements.Line;

import java.math.BigDecimal;
import java.util.List;

public interface LineService {

	BigDecimal getBaseFare(String name);

	void create(Line line);

	void delete(Line line);

	void viewSections(String lineName);

	List<Line> getAll();

	Line getByName(String name);
}